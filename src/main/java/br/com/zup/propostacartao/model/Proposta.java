package br.com.zup.propostacartao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.propostacartao.util.DadosUtil;

@Entity
public class Proposta {

	@Id
	@SequenceGenerator(name = "proposta", sequenceName = "proposta_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proposta")
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	@Email
	private String email;
	
	@NotNull
	@Embedded	
	private Endereco endereco;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String documento;
	
	@NotNull	
	@Positive	
	private BigDecimal salario;
	
	@NotNull
	@Enumerated(EnumType.STRING)	
	private StatusProposta statusProposta;
	
	@Deprecated
	public Proposta() {}

	public Proposta(@NotBlank String nome, @Email @NotBlank String email, @NotNull Endereco endereco,
			@NotBlank String documento, @Positive @NotNull BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.documento = documento;
		this.salario = salario;
		this.statusProposta = StatusProposta.CRIADA;
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getDocumento() {
		return documento;
	}

	public void atualizarStatus(String statusProposta) {	
		this.statusProposta = StatusProposta.atualizarStatus(statusProposta);
	}
	
	@Override
	public String toString() {
		String documentoProtegido = this.ofuscarDocumento(documento);
		
		return "Proposta [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco.toString() + ", documento="
				+ documentoProtegido + ", salario=" + salario + ", statusProposta=" + statusProposta + "]";
	}
	
	private String ofuscarDocumento(String documento) {				
		try {
			documento = DadosUtil.maskString(documento, 3, documento.length(), '*');
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return documento;
	}
	
}
