package br.com.zup.propostacartao.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.propostacartao.model.TipoCarteiraDigital;

public class CarteiraDigitalRequest {

	@NotBlank
    private String email;

    @NotNull
    private TipoCarteiraDigital tipoCarteira;

    public CarteiraDigitalRequest(@NotBlank String email, @NotBlank TipoCarteiraDigital tipoCarteira) {
        this.email = email;
		this.tipoCarteira = tipoCarteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteiraDigital getTipoCarteira() {
		return tipoCarteira;
	}
	
}
