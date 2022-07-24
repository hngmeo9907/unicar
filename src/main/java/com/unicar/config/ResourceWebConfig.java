package com.unicar.config;

import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ResourceWebConfig implements WebMvcConfigurer {
    final Environment env;

    public ResourceWebConfig(Environment env) {
        this.env = env;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        String location = env.getProperty("app.file.storage.mapping");

        registry.addResourceHandler("/uploads/**").addResourceLocations(location);
    }
}
