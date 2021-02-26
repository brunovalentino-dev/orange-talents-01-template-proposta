package br.com.zup.propostacartao.request;

import javax.validation.constraints.NotBlank;

import br.com.zup.propostacartao.model.Biometria;
import br.com.zup.propostacartao.model.Cartao;
import br.com.zup.propostacartao.validation.annotation.FormatoBase64;
import br.com.zup.propostacartao.validation.annotation.ValorUnico;

public class BiometriaRequest {

	@NotBlank
	@ValorUnico(campo = "fingerprint", dominio = Biometria.class)
	@FormatoBase64
	private String fingerprint;

	public String getFingerprint() {
		return fingerprint;
	}

	public Biometria toEntity(Cartao cartao) {
		return new Biometria(cartao, fingerprint);
	}
	
}
