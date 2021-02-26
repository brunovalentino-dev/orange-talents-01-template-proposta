package br.com.zup.propostacartao.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.propostacartao.http.client.CartaoClient;
import br.com.zup.propostacartao.http.client.request.CarteiraDigitalClientRequest;
import br.com.zup.propostacartao.model.Cartao;
import br.com.zup.propostacartao.model.CarteiraDigital;
import br.com.zup.propostacartao.repository.CartaoRepository;
import br.com.zup.propostacartao.repository.CarteiraDigitalRepository;
import br.com.zup.propostacartao.request.CarteiraDigitalRequest;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping(value = "/api/cartoes")
public class CarteiraDigitalController {

	private CartaoRepository cartaoRepository;
	private CarteiraDigitalRepository carteiraDigitalRepository;
	private CartaoClient cartaoClient;
	private Tracer tracer;
	
	public CarteiraDigitalController(CartaoRepository cartaoRepository, CarteiraDigitalRepository carteiraDigitalRepository,
			CartaoClient cartaoClient, Tracer tracer) {
		this.cartaoRepository = cartaoRepository;
		this.carteiraDigitalRepository = carteiraDigitalRepository;
		this.cartaoClient = cartaoClient;
		this.tracer = tracer;
	}

	@PostMapping("/{id}/carteiras")
    public ResponseEntity<?> associarCarteiraDigital(@PathVariable Long id, 
    		@Valid @RequestBody CarteiraDigitalRequest request, UriComponentsBuilder uriBuilder) {
		Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.cartao.action", "Associar cartão à uma carteira digital");
		
        Optional<Cartao> cartaoEncontrado = cartaoRepository.findById(id);
        
        if (!cartaoEncontrado.isPresent()) {
        	return ResponseEntity.notFound().build();
		}

        Cartao cartao = cartaoEncontrado.get();

        Map<String, String> erros = new HashMap<>();

        Optional<CarteiraDigital> carteiraDigitalEncontrada =
        		carteiraDigitalRepository.findByTipoCarteiraAndCartao(request.getTipoCarteira(), cartao);
        
        if(carteiraDigitalEncontrada.isPresent()){
        	erros.put("Erro registrado", "O cartão informado já foi cadastrado para esta carteira!");
        	
        	return ResponseEntity.badRequest().body(erros);
        }
        
        CarteiraDigitalClientRequest carteiraDigitalClientRequest = 
        		new CarteiraDigitalClientRequest(request);
        
        try {
            cartaoClient.associarCarteiraDigital(cartao.getNumero(), carteiraDigitalClientRequest);
        }
        catch (FeignException.UnprocessableEntity exception){
        	erros.put("Erro registrado", "Não foi possível realizar esta operação.");
            
            return ResponseEntity.unprocessableEntity().body(erros);
        }
        
        CarteiraDigital carteiraDigital = 
        		new CarteiraDigital(cartao, request.getEmail(), request.getTipoCarteira());         		
        		
        carteiraDigitalRepository.save(carteiraDigital);
        
        URI location = uriBuilder.path("/api/cartoes/{id}/carteiras/{id}")
        						 .build(cartao.getId(), carteiraDigital.getId());

        return ResponseEntity.created(location).build();
    }
	
}
