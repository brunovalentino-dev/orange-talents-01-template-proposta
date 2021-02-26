package br.com.zup.propostacartao.http.client.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zup.propostacartao.model.Cartao;
import br.com.zup.propostacartao.model.Proposta;

public class CartaoResponse {

	private String id;
	private String titular;
	private LocalDateTime emitidoEm;
	private Long idProposta;
	private BigDecimal limite;

	public String getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Cartao toEntity(Proposta proposta) {			
		return new Cartao(id, titular, emitidoEm, limite, proposta);
	}
	
	@Override
	public String toString() {
		return "CartaoResponse [id=" + id + ", titular=" + titular + ", emitidoEm=" + emitidoEm + ", idProposta="
				+ idProposta + ", limite=" + limite + "]";
	}
	
}
