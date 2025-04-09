package com.meetime.hubspot.config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    /**
     * Configuração de segurança da aplicação
     * - Define as permissões de acesso aos endpoints
     * - Desativa o CSRF para facilitar testes locais
     * - Permite acesso público a certos endpoints e exige autenticação para outros
     * - Desativa o formulário padrão de login
     * @param http Objeto do Spring Security para configuração de segurança
     * @return SecurityFilterChain 
     * @throws Exception 
     */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests()
	            .requestMatchers("/oauth/**", "/contacts/**", "/webhooks/**").permitAll()
	            .anyRequest().authenticated()
	        .and()
	        .formLogin().disable(); // Desativa o formulário padrão de login

	    return http.build();
	}
}