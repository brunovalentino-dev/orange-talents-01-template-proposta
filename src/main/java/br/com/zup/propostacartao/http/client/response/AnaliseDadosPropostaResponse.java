package br.com.zup.propostacartao.http.client.response;

public class AnaliseDadosPropostaResponse {

	private String resultadoSolicitacao;
	private String documento;
	private String nome;
	private Long idProposta;

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
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
