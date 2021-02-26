package br.com.zup.propostacartao.request;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.propostacartao.model.Proposta;
import br.com.zup.propostacartao.validation.annotation.CpfCnpj;
import br.com.zup.propostacartao.validation.annotation.ValorUnico;

public class PropostaRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	@ValorUnico(campo = "email", dominio = Proposta.class)
	private String email;
	
	@NotNull
	@Valid
	@Embedded
	private EnderecoRequest endereco;

	@NotBlank
	@ValorUnico(campo = "documento", dominio = Proposta.class)	
	@CpfCnpj
	private String documento;
	
	@NotNull
	@Positive	
	private BigDecimal salario;

	public PropostaRequest(@NotBlank String nome, @NotBlank @Email String email,
			@NotNull @Valid EnderecoRequest endereco, @NotBlank String documento,
			@NotNull @Positive BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.documento = documento;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public EnderecoRequest getEndereco() {
		return endereco;
	}

	public String getDocumento() {
		return documento;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public Proposta toEntity() {
		return new Proposta(nome, email, endereco.toEntity(), documento, salario);
	}
	
}
