package com.jwt.springjwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class GlobalCorsConfiguration {
@Bean
public WebMvcConfigurer corsConfiguer(){
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedMethods("GET","POST","PUT")
                    .allowedHeaders("*")
                    .allowedOrigins("http://localhost:8081");
        }
    };
}
}