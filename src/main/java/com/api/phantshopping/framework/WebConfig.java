package com.api.phantshopping.framework;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        // Configuração global para permitir CORS em todas as rotas
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://54.158.233.226") // URLs permitidas (ajuste conforme necessário)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite credenciais (cookies, tokens)
    }
}
