package br.com.zup.propostacartao.databuilder.model;

import br.com.zup.propostacartao.model.Endereco;

public class EnderecoBuilder {

	private String logradouro;
    private String cep;
    private String numero;
    private String complemento;

    public EnderecoBuilder comLogradouro(String logradouro) {
    	this.logradouro = logradouro;
    	return this;
    }
    
    public EnderecoBuilder comCep(String cep) {
    	this.cep = cep;
    	return this;
    }
    
    public EnderecoBuilder comNumero(String numero) {
    	this.numero = numero;
    	return this;
    }
    
    public EnderecoBuilder comComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public Endereco build() {
        return new Endereco(logradouro, cep, numero, complemento);
    }
	
}
