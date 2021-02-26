package br.com.zup.propostacartao.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

	@NotBlank
	@Column(nullable = false)
	private String logradouro;
	
	@NotBlank
	@Column(nullable = false)
	private String cep;
	
	@NotBlank
	@Column(nullable = false)
	private String numero;
	
	@NotBlank
	@Column(nullable = false)
	private String complemento;
	
	@Deprecated
	public Endereco() {}

	public Endereco(@NotBlank String logradouro, @NotBlank String cep, @NotBlank String numero,
			@NotBlank String complemento) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCep() {
		return cep;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", cep=" + cep + ", numero=" + numero + ", complemento="
				+ complemento + "]";
	}
	
}
