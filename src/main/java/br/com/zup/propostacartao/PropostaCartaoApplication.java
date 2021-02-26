package br.com.zup.propostacartao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableWebSecurity
public class PropostaCartaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaCartaoApplication.class, args);
	}

}
