package br.com.zup.propostacartao.http.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.propostacartao.http.client.request.AvisoViagemClientRequest;
import br.com.zup.propostacartao.http.client.request.BloqueioCartaoRequest;
import br.com.zup.propostacartao.http.client.request.CarteiraDigitalClientRequest;
import br.com.zup.propostacartao.http.client.response.AvisoViagemClientResponse;
import br.com.zup.propostacartao.http.client.response.BloqueioCartaoResponse;
import br.com.zup.propostacartao.http.client.response.CartaoResponse;
import br.com.zup.propostacartao.http.client.response.CarteiraDigitalClientResponse;

@FeignClient(name = "cartaoClient", url = "${contas.client.host}")
public interface CartaoClient {

	@GetMapping("/api/cartoes")
    CartaoResponse consultar(@RequestParam Long idProposta);
	
	@PostMapping("/api/cartoes/{id}/bloqueios")
    BloqueioCartaoResponse bloquear(@PathVariable String id, 
    		@RequestBody BloqueioCartaoRequest request);

    @PostMapping("/api/cartoes/{id}/avisos")
    AvisoViagemClientResponse notificar(@PathVariable String id, 
    		@RequestBody AvisoViagemClientRequest request);
    
    @PostMapping("/api/cartoes/{id}/carteiras")
    CarteiraDigitalClientResponse associarCarteiraDigital(@PathVariable String id, 
    		@RequestBody CarteiraDigitalClientRequest request);
	
}
