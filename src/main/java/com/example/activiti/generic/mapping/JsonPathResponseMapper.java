package com.example.activiti.generic.mapping;

import com.example.activiti.generic.context.ExecutionContextAdapter;
import com.jayway.jsonpath.JsonPath;

import java.util.Map;

public class JsonPathResponseMapper implements ResponseMapper {

    private final Map<String, String> responseMapping;

    public JsonPathResponseMapper(Map<String, String> responseMapping) {
        this.responseMapping = responseMapping;
    }

    @Override
    public void map(Object responseBody, Map<String, Object> output, ExecutionContextAdapter context) {
        String activityId = context.getActivityId();

        if (!(responseBody instanceof String json)) {
            System.out.printf("⚠️ [%s] Response is not a valid String. Setting null for all variables.%n", activityId);
            responseMapping.keySet().forEach(k -> output.put(k, null));
            return;
        }

        if (json.isBlank()) {
            System.out.printf("⚠️ [%s] Response body is empty. Setting null for all variables.%n", activityId);
            responseMapping.keySet().forEach(k -> output.put(k, null));
            return;
        }

        responseMapping.forEach((key, jsonPath) -> {
            try {
                Object value = JsonPath.read(json, jsonPath);
                output.put(key, value);
                System.out.printf("✅ [%s] Mapped variable '%s' with value: %s%n", activityId, key, value);
            } catch (Exception e) {
                System.out.printf("⚠️ [%s] Could not extract '%s'. Setting null. Reason: %s%n", activityId, key, e.getMessage());
                output.put(key, null);
            }
        });
    }
}
