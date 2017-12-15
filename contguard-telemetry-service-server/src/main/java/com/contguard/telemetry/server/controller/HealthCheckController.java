package com.contguard.telemetry.server.controller;

import com.contguard.telemetry.contract.HealthCheckResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController extends BaseController {

    @Value("${app.name}")
    private String _name;

    @Value("${app.version}")
    private String _version;

    @GetMapping
    public HealthCheckResponse healthCheck() {
        HealthCheckResponse response = new HealthCheckResponse();
        response.setVersion(_version);
        response.setApplication(_name);
        return response;
    }
}