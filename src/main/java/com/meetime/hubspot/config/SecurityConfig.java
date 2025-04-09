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
            .csrf().disable() // Desativa CSRF para testes com Postman e Webhooks
            .authorizeHttpRequests()
                .requestMatchers("/oauth/**", "/contacts/**", "/webhooks/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin().disable(); // Desativa formulário padrão

        return http.build();
    }
}
