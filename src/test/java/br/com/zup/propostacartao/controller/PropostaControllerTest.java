package br.com.zup.propostacartao.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.propostacartao.databuilder.request.EnderecoRequestBuilder;
import br.com.zup.propostacartao.databuilder.request.PropostaRequestBuilder;
import br.com.zup.propostacartao.request.PropostaRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PropostaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void deveriaRetornarBadRequestAoInformarDadosInvalidos() throws Exception {
		URI uri = new URI("/api/propostas");
		
		PropostaRequest propostaRequest = new PropostaRequestBuilder()
				.comDocumento(null)
				.comNome(null)
				.comEmail(null)
				.comSalario(null)
				.comEndereco(new EnderecoRequestBuilder()
						.comLogradouro(null)
						.comNumero(null)
						.comCep(null)
						.comComplemento(null)
						.build())
				.build();
		
		String content = objectMapper.writeValueAsString(propostaRequest);
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();	
		
		System.out.println(content);
	}
	
}
