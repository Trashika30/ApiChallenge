package com.example.apichallenge.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfiguration {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        org.springframework.web.cors.CorsConfiguration c
                = new org.springframework.web.cors.CorsConfiguration();

        c.addAllowedOrigin("*");
        c.addAllowedHeader("*");
        c.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource s
                = new UrlBasedCorsConfigurationSource();

        s.registerCorsConfiguration("/**", c);

        return s;
    }
}
