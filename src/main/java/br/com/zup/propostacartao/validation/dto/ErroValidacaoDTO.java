package br.com.zup.propostacartao.validation.dto;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacaoDTO {

	private List<String> errosGlobais = new ArrayList<>();
	private List<CampoErroValidacaoDTO> camposComErro = new ArrayList<>();
	
	public void adicionarErrosGlobais(String mensagem) {
		errosGlobais.add(mensagem);
	}
	
	public void adicionarCamposComErro(String campo, String mensagem) {
		CampoErroValidacaoDTO campoComErro = new CampoErroValidacaoDTO(campo, mensagem);
		
		camposComErro.add(campoComErro);
	}

	public List<String> getErrosGlobais() {
		return errosGlobais;
	}

	public List<CampoErroValidacaoDTO> getCamposComErro() {
		return camposComErro;
	}
	
	public int getNumeroDeErros() {
		return camposComErro.size();
	}
	
}
