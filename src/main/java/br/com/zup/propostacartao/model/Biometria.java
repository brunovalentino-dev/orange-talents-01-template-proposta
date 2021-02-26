package br.com.zup.propostacartao.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {

	@Id
	@SequenceGenerator(name = "biometria", sequenceName = "biometria_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "biometria")
	private Long id;
	
	@NotNull
    @ManyToOne
    private Cartao cartao;

    @NotBlank
    @Column(nullable = false)
    private String fingerprint;
    
    @NotNull
    private LocalDate dataCadastro;

    @Deprecated
    public Biometria() {}
    
	public Biometria(@NotNull Cartao cartao, @NotBlank String fingerprint) {
		this.cartao = cartao;
		this.fingerprint = fingerprint;
		this.dataCadastro = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	@Override
	public String toString() {
		return "Biometria [id=" + id + ", cartao=" + cartao + ", fingerprint=" + fingerprint + ", dataCadastro="
				+ dataCadastro + "]";
	}
	
}
