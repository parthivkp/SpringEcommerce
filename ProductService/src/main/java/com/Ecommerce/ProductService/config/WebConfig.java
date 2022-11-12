package com.Ecommerce.ProductService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
                .parameterName("MediaType")
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("XML",MediaType.APPLICATION_XML)
                .mediaType("JSON",MediaType.APPLICATION_JSON);
    }
}
