package com.example.activiti.generic.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "external-services")
public class ExternalServicesConfig {

    private Map<String, ServicesDefinition> services;

    @Data
    public static class ServicesDefinition {
        private String name;
        private String url;
        private String method;
        private Map<String, String> headers;
        private String bodyTemplate;
        private Map<String, String> responseMapping;
    }

    public ServicesDefinition getByServiceName(String name) {
        return services.get(name);
    }

    @PostConstruct
    public void logServices() {
        System.out.println("🔍 Instance: " + this + " | Hash: " + System.identityHashCode(this));

        if (services == null || services.isEmpty()) {
            System.out.println("❌ Services were not loaded from YAML");
        } else {
            System.out.println("✅ Loaded services:");
            services.forEach((key, val) -> {
                System.out.printf("🔹 %s → %s (%s)%n", key, val.getUrl(), val.getMethod());
            });
        }
    }
}

