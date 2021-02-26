package br.com.zup.propostacartao.http.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.propostacartao.http.client.request.AnaliseDadosPropostaRequest;
import br.com.zup.propostacartao.http.client.response.AnaliseDadosPropostaResponse;

@FeignClient(name = "analiseDadosPropostaClient", url = "${analise.client.host}")
public interface AnaliseDadosPropostaClient {

	@PostMapping(value = "/api/solicitacao")
	AnaliseDadosPropostaResponse consultar(@Valid @RequestBody AnaliseDadosPropostaRequest request); 
	
}
