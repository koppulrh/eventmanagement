// SecurityConfig.java
package com.example.eventmanagement.config;

import com.example.eventmanagement.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/register", "/api/user/login").permitAll()  // Public endpoints
                        .requestMatchers("/api/public/**").permitAll() // Public endpoints
                        .requestMatchers("/api/event/**").authenticated()  // Event-related endpoints require authentication
                        .requestMatchers("/api/ticket/**").authenticated() // Ticket-related endpoints require authentication
                        .requestMatchers("/api/attendee/**").authenticated() // Attendee-related endpoints require authentication
                        .requestMatchers("/api/user/**").authenticated() // User-related endpoints require authentication
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ Add this PasswordEncoder bean definition
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
