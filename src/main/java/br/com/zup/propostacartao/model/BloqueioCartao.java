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
public class BloqueioCartao {

	@Id
	@SequenceGenerator(name = "bloqueio", sequenceName = "bloqueio_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bloqueio")
	private Long id;
	
	@NotNull
    @ManyToOne
    private Cartao cartao;

    @NotNull
    private LocalDateTime dataHoraBloqueio;

    @NotBlank
    @Column(nullable = false)
    private String ip;

    @NotBlank
    @Column(nullable = false)
    private String userAgent;

    @Deprecated
    public BloqueioCartao() {}

    public BloqueioCartao(@NotNull Cartao cartao, @NotBlank String ip, @NotBlank String userAgent) {
        this.cartao = cartao;
        this.ip = ip;
        this.userAgent = userAgent;
        this.dataHoraBloqueio = LocalDateTime.now();
    }

	@Override
	public String toString() {
		return "BloqueioCartao [id=" + id + ", cartao=" + cartao + ", dataHoraBloqueio=" + dataHoraBloqueio + ", ip="
				+ ip + ", userAgent=" + userAgent + "]";
	}
    
}
