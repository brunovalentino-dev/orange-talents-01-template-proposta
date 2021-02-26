package br.com.zup.propostacartao.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {

	@Id
	@SequenceGenerator(name = "cartao", sequenceName = "cartao_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartao")
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
    private String numero;	

    @NotBlank
    @Column(nullable = false)
    private String titular;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotNull
    private BigDecimal limite;

    @OneToOne(cascade = CascadeType.ALL)
    private Proposta proposta;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;
       
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Biometria> biometrias = new ArrayList<>();
    
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BloqueioCartao> bloqueios = new ArrayList<>();    

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvisoViagem> avisos = new ArrayList<>();  
    
    @Deprecated
    public Cartao() {}

	public Cartao(@NotBlank String numero, @NotBlank String titular, @NotNull LocalDateTime emitidoEm,
			@NotNull BigDecimal limite, Proposta proposta) {
		this.numero = numero;
		this.titular = titular;
		this.emitidoEm = emitidoEm;
		this.limite = limite;
		this.proposta = proposta;
		this.statusCartao = StatusCartao.DESBLOQUEADO;
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getTitular() {
		return titular;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public StatusCartao getStatusCartao() {
		return statusCartao;
	}

	public void bloquear() {
		this.statusCartao = StatusCartao.BLOQUEADO;
	}
	
	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numero=" + numero + ", titular=" + titular + ", emitidoEm=" + emitidoEm
				+ ", limite=" + limite + ", proposta=" + proposta + ", statusCartao=" + statusCartao + "]";
	}

}