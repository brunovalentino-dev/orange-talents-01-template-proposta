package br.com.zup.propostacartao.databuilder.model;

import java.math.BigDecimal;

import br.com.zup.propostacartao.model.Endereco;
import br.com.zup.propostacartao.model.Proposta;

public class PropostaBuilder {

    private String documento;
    private String nome;
    private String email;
    private BigDecimal salario;
    private Endereco endereco;

    public PropostaBuilder comDocumento(String documento) {
    	this.documento = documento;
    	return this;
    }
    
    public PropostaBuilder comNome(String nome) {
    	this.nome = nome;
    	return this;
    }
    
    public PropostaBuilder comEmail(String email) {
    	this.email = email;
    	return this;
    }
    
    public PropostaBuilder comSalario(BigDecimal salario) {
    	this.salario = salario;
    	return this;
    }
    
    public PropostaBuilder comEndereco(Endereco endereco) {
    	this.endereco = endereco;
    	return this;
    }
    
    public Proposta build() {
    	return new Proposta(nome, email, endereco, documento, salario);
    }
	
}
