package br.com.zup.propostacartao.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.propostacartao.http.client.AnaliseDadosPropostaClient;
import br.com.zup.propostacartao.http.client.request.AnaliseDadosPropostaRequest;
import br.com.zup.propostacartao.http.client.response.AnaliseDadosPropostaResponse;
import br.com.zup.propostacartao.model.Proposta;
import br.com.zup.propostacartao.repository.PropostaRepository;
import br.com.zup.propostacartao.request.PropostaRequest;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping(value = "/api/propostas")
public class PropostaController {
	
	private PropostaRepository propostaRepository;	
	private AnaliseDadosPropostaClient analiseDadosProposta;	
	private Tracer tracer;
	
	public PropostaController(PropostaRepository propostaRepository, 
			AnaliseDadosPropostaClient analiseDadosProposta, Tracer tracer) {
		this.propostaRepository = propostaRepository;
		this.analiseDadosProposta = analiseDadosProposta;
		this.tracer = tracer;
	}

	@PostMapping
	public ResponseEntity<?> cadastrarProposta(@Valid @RequestBody PropostaRequest request,
			UriComponentsBuilder uriBuilder) {
		Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.proposta.action", "Cadastro de proposta");
		
		Proposta proposta = request.toEntity();
		
		if (propostaRepository.existsByDocumento(proposta.getDocumento())) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		propostaRepository.save(proposta);
		
		AnaliseDadosPropostaRequest analiseDadosRequest = new AnaliseDadosPropostaRequest(proposta);		
		
		try {
			AnaliseDadosPropostaResponse analiseDadosResponse = analiseDadosProposta.consultar(analiseDadosRequest);
			proposta.atualizarStatus(analiseDadosResponse.getResultadoSolicitacao());
		}
		catch (FeignException.UnprocessableEntity exception) {
			proposta.atualizarStatus("COM_RESTRICAO");
			return ResponseEntity.unprocessableEntity().build();
		}
		finally {
			propostaRepository.save(proposta);
		}
		
		URI location = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarProposta(@PathVariable Long id) {
		Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.proposta.action", "Buscar proposta pelo ID");
		
		Optional<Proposta> propostaEncontrada = propostaRepository.findById(id);
		
		if(propostaEncontrada.isEmpty()){
			return ResponseEntity.notFound().build();
		}
    
		return ResponseEntity.ok(propostaEncontrada.get().toString());
	}
	
}
