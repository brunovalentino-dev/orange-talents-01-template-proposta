package br.com.zup.propostacartao.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.hibernate.LockOptions;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import br.com.zup.propostacartao.model.Proposta;
import br.com.zup.propostacartao.model.StatusProposta;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {

	boolean existsByDocumento(String documento);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE) // For update
    @QueryHints({
    	@QueryHint(name = "javax.persistence.lock.timeout", 
    			   value = (LockOptions.SKIP_LOCKED + "")) // Skip locked
    })
    List<Proposta> findTop5ByStatusPropostaOrderByIdAsc(StatusProposta statusProposta);

}
