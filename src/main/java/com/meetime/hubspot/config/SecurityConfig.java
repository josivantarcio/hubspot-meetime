package com.meetime.hubspot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Desativa proteção CSRF para testes locais
            .authorizeHttpRequests()
                .requestMatchers("/oauth/**", "/contacts/**", "/webhooks/**").permitAll() // Permite acesso público a webhooks
                .anyRequest().authenticated() // Exige autenticação para outros endpoints
            .and()
            .formLogin().disable(); // Desativa o formulário padrão de login

        return http.build();
    }
}