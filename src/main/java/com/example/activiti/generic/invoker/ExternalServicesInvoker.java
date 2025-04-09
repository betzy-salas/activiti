package com.example.activiti.generic.invoker;

import com.example.activiti.generic.model.ExternalServicesResponse;
import com.example.activiti.generic.util.TemplatesResolver;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class ExternalServicesInvoker {
    private final RestTemplate restTemplate = new RestTemplate();

    public ExternalServicesResponse<String> invoke(String url, String method, Map<String, String> headersMap, String bodyTemplate, Map<String, Object> variables) {
        String resolvedUrl = TemplatesResolver.resolve(url, variables);
        String resolvedBody = TemplatesResolver.resolve(bodyTemplate, variables);

        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach((k, v) -> headers.set(k, TemplatesResolver.resolve(v, variables)));

        HttpEntity<String> entity = new HttpEntity<>(resolvedBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                resolvedUrl,
                HttpMethod.valueOf(method.toUpperCase()),
                entity,
                String.class);

        String responseBody = response.getStatusCode() == HttpStatus.NO_CONTENT ? null : response.getBody();
        return new ExternalServicesResponse<>(responseBody);

    }
}
