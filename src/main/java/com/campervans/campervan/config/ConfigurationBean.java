package com.campervans.campervan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class ConfigurationBean {
    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new io.sentry.spring.SentryExceptionResolver();
    }
}
