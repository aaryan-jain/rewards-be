package com.rewards.api;

import com.rewards.api.auth.JwtAuthEntryPoint;
import com.rewards.api.auth.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("/login", "/token", "/hello", "/users").permitAll() // Exclude login and token endpoints from authentication
                .anyRequest().authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
