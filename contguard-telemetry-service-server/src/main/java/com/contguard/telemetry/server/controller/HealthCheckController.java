package com.contguard.telemetry.server.controller;

import com.contguard.telemetry.contract.HealthCheckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController extends BaseController {
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    @Value("${app.name}")
    private String _name;

    @Value("${app.version}")
    private String _version;

    @GetMapping
    public HealthCheckResponse healthCheck() {
        _logger.info("Called healthcheck");
        HealthCheckResponse response = new HealthCheckResponse();
        response.setVersion(_version);
        response.setApplication(_name);
        return response;
    }
}