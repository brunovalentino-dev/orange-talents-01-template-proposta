package br.com.zup.propostacartao.request;

import javax.validation.constraints.NotBlank;

import br.com.zup.propostacartao.model.Endereco;

public class EnderecoRequest {

	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String complemento;

	public EnderecoRequest(@NotBlank String logradouro, @NotBlank String cep, @NotBlank String numero,
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
	
	public Endereco toEntity() {
		return new Endereco(logradouro, cep, numero, complemento);
	}
	
}
