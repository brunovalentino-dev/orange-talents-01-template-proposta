package br.com.zup.propostacartao.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.propostacartao.http.client.CartaoClient;
import br.com.zup.propostacartao.http.client.request.BloqueioCartaoRequest;
import br.com.zup.propostacartao.model.BloqueioCartao;
import br.com.zup.propostacartao.model.Cartao;
import br.com.zup.propostacartao.repository.BloqueioCartaoRepository;
import br.com.zup.propostacartao.repository.CartaoRepository;
import br.com.zup.propostacartao.util.HttpServletRequestUtil;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping(value = "/api/cartoes")
public class BloqueioCartaoController {

	private CartaoRepository cartaoRepository;
	private CartaoClient cartaoClient;
	private BloqueioCartaoRepository bloqueioCartaoRepository;
	private Tracer tracer;
	
	public BloqueioCartaoController(CartaoRepository cartaoRepository, CartaoClient cartaoClient,
			BloqueioCartaoRepository bloqueioCartaoRepository, Tracer tracer) {
		this.cartaoRepository = cartaoRepository;
		this.cartaoClient = cartaoClient;
		this.bloqueioCartaoRepository = bloqueioCartaoRepository;
		this.tracer = tracer;
	}

    @PostMapping("/{id}/bloqueios")
    public ResponseEntity<?> bloquearCartao(@PathVariable Long id, HttpServletRequest request) {
    	Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.cartao.action", "Bloqueio de cartão");
    	
        Optional<Cartao> cartaoEncontrado = cartaoRepository.findById(id);

        if(!cartaoEncontrado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = cartaoEncontrado.get();
        
        BloqueioCartaoRequest bloqueioCartaoRequest = new BloqueioCartaoRequest("${app.name}");
        
        try {
            cartaoClient.bloquear(cartao.getNumero(), bloqueioCartaoRequest);
        }
        catch (FeignException exception) {
            Map<String, String> erros = new HashMap<>();
            erros.put("Erro registrado", "O cartão informado já foi bloqueado!");
            
            return ResponseEntity.unprocessableEntity().body(erros);
        }

        cartao.bloquear();
        
        Map<String, String> headers = HttpServletRequestUtil.getHeadersInfo(request);
        
        BloqueioCartao bloqueio = new BloqueioCartao(cartao, headers.get("IP"), headers.get("USER-AGENT"));
        
        bloqueioCartaoRepository.save(bloqueio);

        return ResponseEntity.ok().build();
    }
	
}
