package br.com.zup.propostacartao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.zup.propostacartao.databuilder.model.EnderecoBuilder;
import br.com.zup.propostacartao.databuilder.model.PropostaBuilder;
import br.com.zup.propostacartao.model.Proposta;
import br.com.zup.propostacartao.model.StatusProposta;

@DataJpaTest
@ActiveProfiles("test")
public class PropostaRepositoryTest {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Test
	public void deveriaEncontrarUmDocumentoExistente() {
		String documento = "269.326.988-89";
		
		boolean temDocumento = propostaRepository.existsByDocumento(documento);
		
		assertTrue(temDocumento);
	}
	
	@Test
	public void naoDeveriaEncontrarUmDocumentoNulo() {
		String documento = null;
		
		boolean temDocumento = propostaRepository.existsByDocumento(documento);
		
		assertFalse(temDocumento);
	}
	
	@Test
	public void naoDeveriaEncontrarUmDocumentoInexistente() {
		String documento = "999.999.999-99";
		
		boolean temDocumento = propostaRepository.existsByDocumento(documento);
		
		assertFalse(temDocumento);
	}
	
	@Test
	public void deveriaListarAs5PrimeirasPropostasElegiveis() {		
		Proposta primeiraProposta = new PropostaBuilder()
				.comDocumento("269.326.988-89")
				.comNome("Nixon Valentino Kuhn")
				.comEmail("nixon@email.com")
				.comSalario(new BigDecimal(13000.00))
				.comEndereco(new EnderecoBuilder()
						.comLogradouro("Rua dos Cactos")
						.comNumero("123")
						.comCep("12345-678")
						.comComplemento("Bairro Aparecida, São José dos Cachorros/MG")
						.build())
				.build();
		
		Proposta ultimaProposta = new PropostaBuilder()
				.comDocumento("698.956.111-76")
				.comNome("Funerea Skylab")
				.comEmail("funerea@email.com")
				.comSalario(new BigDecimal(8000.00))
				.comEndereco(new EnderecoBuilder()
						.comLogradouro("Rua dos Porcos")
						.comNumero("98")
						.comCep("70070-070")
						.comComplemento("Bairro Vila Samartano, São José dos Cachorros/MG")
						.build())
				.build();
		
		List<Proposta> propostasElegiveis = 
				propostaRepository.findTop5ByStatusPropostaOrderByIdAsc(StatusProposta.ELEGIVEL);
		
		assertNotNull(propostasElegiveis);
		
		assertEquals(primeiraProposta.getDocumento(), propostasElegiveis.get(0).getDocumento());
		assertEquals(primeiraProposta.getEmail(), propostasElegiveis.get(0).getEmail());
		assertEquals(ultimaProposta.getDocumento(), propostasElegiveis.get(propostasElegiveis.size() - 1).getDocumento());
		assertEquals(ultimaProposta.getEmail(), propostasElegiveis.get(propostasElegiveis.size() - 1).getEmail());
	}

}
