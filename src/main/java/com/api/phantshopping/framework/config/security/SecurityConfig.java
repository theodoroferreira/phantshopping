package com.api.phantshopping.framework.config.security;

import com.api.phantshopping.framework.config.cors.WebConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig
{

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, WebConfig webConfig) throws Exception
    {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/users/create-user").permitAll()
                                .requestMatchers(HttpMethod.POST, "/error").permitAll()
                                .anyRequest().authenticated()
                )
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(new ArrayList<>()
                    {{
                        add("http://localhost:3000");
                    }});
                    corsConfiguration.setAllowedMethods(new ArrayList<>()
                    {{
                        add("GET");
                        add("POST");
                        add("PUT");
                        add("DELETE");
                        add("OPTIONS");
                    }});
                    corsConfiguration.setAllowedHeaders(new ArrayList<>()
                    {{
                        add("Authorization");
                        add("Content-Type");
                        add("Accept");
                    }});
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                })).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}