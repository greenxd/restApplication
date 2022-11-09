package ru.open.restApplication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("common")
@Getter
@Setter
public class RestApplicationParameters {
    final private String uploadPath = "uploads/avatar";
}
