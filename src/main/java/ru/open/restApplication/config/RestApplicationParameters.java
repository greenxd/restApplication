package ru.open.restApplication.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties("common")
@Getter
public class RestApplicationParameters {
    final private String uploadPath = "uploads/avatar";
}
