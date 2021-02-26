package br.com.zup.propostacartao.http.client.request;

import br.com.zup.propostacartao.model.Proposta;

public class AnaliseDadosPropostaRequest {
	
	private String documento;
	private String nome;
	private Long idProposta;
	
	public AnaliseDadosPropostaRequest(Proposta proposta) {			
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
}
