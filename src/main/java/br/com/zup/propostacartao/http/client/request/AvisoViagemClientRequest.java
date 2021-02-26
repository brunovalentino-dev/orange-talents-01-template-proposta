package br.com.zup.propostacartao.http.client.request;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.propostacartao.request.AvisoViagemRequest;

public class AvisoViagemClientRequest {

	@NotBlank
    private String destino;

    @Future
    @NotNull
    private LocalDate validadeViagem;

    public AvisoViagemClientRequest(AvisoViagemRequest request) {
        this.destino = request.getDestino();
        this.validadeViagem = request.getValidadeViagem();
    }
    
    public String getDestino() {
		return destino;
	}
    
    public LocalDate getValidadeViagem() {
		return validadeViagem;
	}
	
}
