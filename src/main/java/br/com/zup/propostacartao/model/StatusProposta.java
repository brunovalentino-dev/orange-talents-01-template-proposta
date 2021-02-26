package br.com.zup.propostacartao.model;

public enum StatusProposta {

	CRIADA, 
	NAO_ELEGIVEL, 
	ELEGIVEL,
	COM_CARTAO;
	
	public static StatusProposta atualizarStatus(String status) {
		switch (status) {
		case "COM_RESTRICAO":
			return NAO_ELEGIVEL;
		case "SEM_RESTRICAO":
			return ELEGIVEL;
		case "COM_CARTAO":
			return COM_CARTAO;
		default:
			return CRIADA;
		}	
	}
	
}
