package br.com.zup.propostacartao.databuilder.request;

import java.math.BigDecimal;

import br.com.zup.propostacartao.request.EnderecoRequest;
import br.com.zup.propostacartao.request.PropostaRequest;

public class PropostaRequestBuilder {

	private String documento;
    private String nome;
    private String email;
    private BigDecimal salario;
    private EnderecoRequest enderecoRequest;

    public PropostaRequestBuilder comDocumento(String documento) {
    	this.documento = documento;
    	return this;
    }
    
    public PropostaRequestBuilder comNome(String nome) {
    	this.nome = nome;
    	return this;
    }
    
    public PropostaRequestBuilder comEmail(String email) {
    	this.email = email;
    	return this;
    }
    
    public PropostaRequestBuilder comSalario(BigDecimal salario) {
    	this.salario = salario;
    	return this;
    }
    
    public PropostaRequestBuilder comEndereco(EnderecoRequest enderecoRequest) {
    	this.enderecoRequest = enderecoRequest;
    	return this;
    }
    
    public PropostaRequest build() {
    	return new PropostaRequest(nome, email, enderecoRequest, documento, salario);
    }
	
}
