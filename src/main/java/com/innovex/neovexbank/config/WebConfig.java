package com.innovex.neovexbank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Usa "*" temporalmente si sigue fallando
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
