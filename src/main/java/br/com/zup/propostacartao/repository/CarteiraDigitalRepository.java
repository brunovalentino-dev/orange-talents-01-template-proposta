package br.com.zup.propostacartao.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.propostacartao.model.Cartao;
import br.com.zup.propostacartao.model.CarteiraDigital;
import br.com.zup.propostacartao.model.TipoCarteiraDigital;

public interface CarteiraDigitalRepository extends CrudRepository<CarteiraDigital, Long>{

	Optional<CarteiraDigital> findByTipoCarteiraAndCartao(TipoCarteiraDigital tipoCarteira, Cartao cartao);

}
