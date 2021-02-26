package br.com.zup.propostacartao.job;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.zup.propostacartao.http.client.CartaoClient;
import br.com.zup.propostacartao.http.client.response.CartaoResponse;
import br.com.zup.propostacartao.model.Cartao;
import br.com.zup.propostacartao.model.Proposta;
import br.com.zup.propostacartao.model.StatusProposta;
import br.com.zup.propostacartao.repository.CartaoRepository;
import br.com.zup.propostacartao.repository.PropostaRepository;

@Component
public class AssociarCartaoPropostaJob {

	private PropostaRepository propostaRepository;
	private CartaoRepository cartaoRepository;
	private CartaoClient cartaoClient;
	private TransactionTemplate transactionManager;
	
    public AssociarCartaoPropostaJob(PropostaRepository propostaRepository, CartaoRepository cartaoRepository,
			CartaoClient cartaoClient, TransactionTemplate transactionManager) {
		this.propostaRepository = propostaRepository;
		this.cartaoRepository = cartaoRepository;
		this.cartaoClient = cartaoClient;
		this.transactionManager = transactionManager;
	}

	@Scheduled(fixedDelayString = "${consulta.cartao.fixedDelayString}")
    public void execute() {
        boolean temRegistrosPendentes = true;
        
        while (temRegistrosPendentes) {
        	temRegistrosPendentes = transactionManager.execute((transactionStatus -> {
        		List<Proposta> propostasElegiveis = 
        				propostaRepository.findTop5ByStatusPropostaOrderByIdAsc(StatusProposta.ELEGIVEL);
        		
        		if (propostasElegiveis.isEmpty()) {
        			return false;
        		}

        		propostasElegiveis.forEach((proposta -> {
        			CartaoResponse response = consultarCartaoPelaProposta(proposta);

        			if (response != null) {
        				proposta.atualizarStatus("COM_CARTAO");
        				propostaRepository.save(proposta); 
        				        			
        				Cartao cartao = response.toEntity(proposta);                	        				                		
        				cartaoRepository.save(cartao);
                	}
                })); 

                return true;
            }));
        }
    }

    private CartaoResponse consultarCartaoPelaProposta(Proposta proposta) {
        try {
            CartaoResponse response = cartaoClient.consultar(proposta.getId());
            return response;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}
