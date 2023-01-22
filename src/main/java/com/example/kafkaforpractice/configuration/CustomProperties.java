package com.example.kafkaforpractice.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "example")
@Getter @Setter
public class CustomProperties {
    private String property;
}
