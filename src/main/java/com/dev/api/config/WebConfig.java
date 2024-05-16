package com.dev.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mapeia todas as URLs
                .allowedOrigins("http://localhost:5500") // Permite solicitações vindas deste endereço
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Permitir os métodos HTTP especificados
    }
}
