package com.API.ToDoAPP.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = Paths.get(System.getProperty("user.dir"), "uploads").toUri().toString();
        registry.addResourceHandler("/uploads/**").addResourceLocations(uploadPath);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://to-do-appapi.vercel.app") // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS" ,"PATCH")
                .allowedHeaders("*")
                .exposedHeaders("Authorization") // Allow client to read Authorization headers
                .allowCredentials(true); // Enable cookies or authorization headers
    }
}