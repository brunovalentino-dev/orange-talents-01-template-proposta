package br.com.zup.propostacartao.model;

import java.time.LocalDateTime;

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
public class AvisoViagem {

	@Id
	@SequenceGenerator(name = "avisoViagem", sequenceName = "aviso_viagem_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avisoViagem")
	private Long id;
	
	@NotNull
    @ManyToOne
    private Cartao cartao;
	
    @NotNull
    private LocalDateTime dataHoraAviso;
	
	@NotBlank
    @Column(nullable = false)
    private String ip;

    @NotBlank
    @Column(nullable = false)
    private String userAgent;

    @Deprecated
    public AvisoViagem() {}
    
	public AvisoViagem(@NotNull Cartao cartao, @NotBlank String ip, @NotBlank String userAgent) {
		this.cartao = cartao;
		this.ip = ip;
		this.userAgent = userAgent;
		this.dataHoraAviso = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getDataHoraAviso() {
		return dataHoraAviso;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	@Override
	public String toString() {
		return "AvisoViagem [id=" + id + ", cartao=" + cartao + ", dataHoraAviso=" + dataHoraAviso + ", ip=" + ip
				+ ", userAgent=" + userAgent + "]";
	}

}