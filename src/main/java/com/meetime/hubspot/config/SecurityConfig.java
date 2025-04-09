package com.meetime.hubspot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/oauth/**").permitAll() // Permite acesso aos
																								// endpoints OAuth
				.anyRequest().authenticated() 
				.and().formLogin().disable(); //Desativa o formulario padrão de login

		return http.build();
	}
}