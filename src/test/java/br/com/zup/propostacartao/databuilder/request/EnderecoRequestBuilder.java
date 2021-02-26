package br.com.zup.propostacartao.databuilder.request;

import br.com.zup.propostacartao.request.EnderecoRequest;

public class EnderecoRequestBuilder {

	private String logradouro;
    private String cep;
    private String numero;
    private String complemento;

    public EnderecoRequestBuilder comLogradouro(String logradouro) {
    	this.logradouro = logradouro;
    	return this;
    }
    
    public EnderecoRequestBuilder comCep(String cep) {
    	this.cep = cep;
    	return this;
    }
    
    public EnderecoRequestBuilder comNumero(String numero) {
    	this.numero = numero;
    	return this;
    }
    
    public EnderecoRequestBuilder comComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public EnderecoRequest build() {
        return new EnderecoRequest(logradouro, cep, numero, complemento);
    }
	
}
