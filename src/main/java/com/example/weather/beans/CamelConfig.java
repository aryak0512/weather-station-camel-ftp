package com.example.weather.beans;

import org.apache.camel.spi.IdempotentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository.memoryIdempotentRepository;

@Component
public class CamelConfig {

    @Bean
    public IdempotentRepository idempotentRepository() {
        return memoryIdempotentRepository();
    }

}
