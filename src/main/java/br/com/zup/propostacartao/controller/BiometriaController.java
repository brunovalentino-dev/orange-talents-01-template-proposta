package br.com.zup.propostacartao.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.propostacartao.model.Biometria;
import br.com.zup.propostacartao.model.Cartao;
import br.com.zup.propostacartao.repository.BiometriaRepository;
import br.com.zup.propostacartao.repository.CartaoRepository;
import br.com.zup.propostacartao.request.BiometriaRequest;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping(value = "/api/cartoes")
public class BiometriaController {

	private CartaoRepository cartaoRepository;
	private BiometriaRepository biometriaRepository;
	private Tracer tracer;

	public BiometriaController(CartaoRepository cartaoRepository, 
			BiometriaRepository biometriaRepository, Tracer tracer) {
		this.cartaoRepository = cartaoRepository;
		this.biometriaRepository = biometriaRepository;
		this.tracer = tracer;
	}

	@PostMapping(value = "/{id}/biometrias")
	public ResponseEntity<?> cadastrarBiometria(@PathVariable Long id, @Valid @RequestBody BiometriaRequest request, 
			UriComponentsBuilder uriBuilder) {
		Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.cartao.action", "Cadastro de biometria para um cartão");
		
		Optional<Cartao> cartaoEncontrado = cartaoRepository.findById(id);
		
		if (!cartaoEncontrado.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = cartaoEncontrado.get();
		
        Biometria biometria = request.toEntity(cartao);
        
        biometriaRepository.save(biometria);

        URI location = uriBuilder.path("/api/cartoes/{id}/biometrias/{id}")
        						 .buildAndExpand(cartao.getId(), biometria.getId()).toUri();
        
        return ResponseEntity.created(location).build();
	}
	
}
