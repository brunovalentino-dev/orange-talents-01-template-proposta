package br.com.zup.propostacartao.validation.dto;

public class CampoErroValidacaoDTO {

	private String campo;
	private String mensagem;
	
	public CampoErroValidacaoDTO() {}

	public CampoErroValidacaoDTO(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
