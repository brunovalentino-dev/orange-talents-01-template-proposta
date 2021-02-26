package br.com.zup.propostacartao.request;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvisoViagemRequest {

	@NotBlank
    private String destino;

    @Future
    @NotNull
    private LocalDate validadeViagem;
       
    public String getDestino() {
		return destino;
	}
    
    public LocalDate getValidadeViagem() {
		return validadeViagem;
	}
	
}
