package br.com.zup.propostacartao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CarteiraDigital {

	@Id
	@SequenceGenerator(name = "carteira", sequenceName = "carteira_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carteira")
	private Long id;
	
	@NotNull
	@ManyToOne
	private Cartao cartao;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCarteiraDigital tipoCarteira;

    @Deprecated
    public CarteiraDigital() {}

	public CarteiraDigital(@NotNull Cartao cartao, @NotBlank String email, TipoCarteiraDigital tipoCarteira) {
		this.cartao = cartao;
		this.email = email;
		this.tipoCarteira = tipoCarteira;
	}

	public Long getId() {
		return id;
	}
	
}
