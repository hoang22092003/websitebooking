package com.websitebooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/promote/administrator/**").hasRole("ADMINISTRATOR") // Chỉ ADMINISTRATOR mới truy cập
                        .requestMatchers("/auth/promote/admin/**").hasAnyRole("ADMIN", "ADMINISTRATOR") // ADMIN hoặc cao hơn
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
