package com.example.activiti.controller.execution;

import com.example.activiti.model.ProcessRequest;
import com.example.activiti.processes.getdata.mapper.ProcessMapper;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessExecutionController {

    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final ProcessMapper processMapper;

    @PostMapping("/start/{processKey}")
    public String startProcess(@PathVariable String processKey, @RequestBody ProcessRequest request) {
        try {
            boolean isDeployed = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey(processKey)
                    .latestVersion()
                    .singleResult() != null;

            if (!isDeployed) {
                return "The process with key '" + processKey + "' is not deployed.";
            }

            runtimeService.startProcessInstanceByKey(processKey, processMapper.mapToProcessVariables(request));
            return "Process '" + processKey + "' started successfully.";
        } catch (Exception e) {
            return "Error starting the process: " + e.getMessage();
        }
    }
}

