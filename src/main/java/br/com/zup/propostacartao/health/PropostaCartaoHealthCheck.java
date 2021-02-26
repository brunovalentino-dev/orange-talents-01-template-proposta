package br.com.zup.propostacartao.health;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class PropostaCartaoHealthCheck implements HealthIndicator {

	@Override
	public Health health() {
		Map<String, Object> healthDetails = new HashMap<>();
		healthDetails.put("versão", "0.0.1-SNAPSHOT");
		healthDetails.put("descrição", "Proposta cartão API");
		healthDetails.put("endereço", "127.0.0.1");
        
        return Health.status(Status.UP).withDetails(healthDetails).build();
	}
	
}
