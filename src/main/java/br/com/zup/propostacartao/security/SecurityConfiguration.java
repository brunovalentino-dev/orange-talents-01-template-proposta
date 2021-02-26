package br.com.zup.propostacartao.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@Profile("dev")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        	.antMatchers("/actuator/**").permitAll()
        	.antMatchers(HttpMethod.GET,"/propostas/**").hasAuthority("SCOPE_read")
        	.antMatchers(HttpMethod.GET,"/cartoes/**").hasAuthority("SCOPE_read")
        	.antMatchers(HttpMethod.POST,"/propostas/**").hasAuthority("SCOPE_write")
        	.antMatchers(HttpMethod.POST,"/cartoes/**").hasAuthority("SCOPE_write")
        	.anyRequest().authenticated()
        	.and()
        	.cors().disable()
        	.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
	
}
