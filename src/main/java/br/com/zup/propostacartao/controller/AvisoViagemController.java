package br.com.zup.propostacartao.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.propostacartao.http.client.CartaoClient;
import br.com.zup.propostacartao.http.client.request.AvisoViagemClientRequest;
import br.com.zup.propostacartao.model.AvisoViagem;
import br.com.zup.propostacartao.model.Cartao;
import br.com.zup.propostacartao.repository.AvisoViagemRepository;
import br.com.zup.propostacartao.repository.CartaoRepository;
import br.com.zup.propostacartao.request.AvisoViagemRequest;
import br.com.zup.propostacartao.util.HttpServletRequestUtil;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping(value = "/api/cartoes")
public class AvisoViagemController {

	private CartaoRepository cartaoRepository;
	private CartaoClient cartaoClient;
	private AvisoViagemRepository avisoViagemRepository;
	private Tracer tracer;

    public AvisoViagemController(CartaoRepository cartaoRepository, CartaoClient cartaoClient,
			AvisoViagemRepository avisoViagemRepository, Tracer tracer) {
		this.cartaoRepository = cartaoRepository;
		this.cartaoClient = cartaoClient;
		this.avisoViagemRepository = avisoViagemRepository;
		this.tracer = tracer;
	}

	@PostMapping("/{id}/avisos")
    public ResponseEntity<?> notificarAvisoViagem(@PathVariable Long id, @Valid @RequestBody AvisoViagemRequest request,  
    		HttpServletRequest httpServletRequest) {
		Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.cartao.action", "Notificar aviso de viagem");
		
        Optional<Cartao> cartaoEncontrado = cartaoRepository.findById(id);

        if(!cartaoEncontrado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = cartaoEncontrado.get();
        
        AvisoViagemClientRequest avisoViagemClientRequest = new AvisoViagemClientRequest(request); 
        
        try {
            cartaoClient.notificar(cartao.getNumero(), avisoViagemClientRequest);
        }
        catch (FeignException exception) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("Erro registrado", "Aviso de viagem já notificado para este cartão!");
            
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        Map<String, String> headers = HttpServletRequestUtil.getHeadersInfo(httpServletRequest);
        
        AvisoViagem avisoViagem = new AvisoViagem(cartao, headers.get("IP"), headers.get("USER-AGENT"));
        
        avisoViagemRepository.save(avisoViagem);

        return ResponseEntity.ok().build();
    }

}
