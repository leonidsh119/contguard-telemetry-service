package com.contguard.telemetry.server.controller;

import com.contguard.telemetry.server.service.api.ITelemetryService;
import com.contguard.telemetry.contract.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telemetries")
public class TelemetryController extends BaseController {
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ITelemetryService _service;

    @PostMapping
    AddTelemetryResponse addTelemetry(@RequestBody AddTelemetryRequest request) {
        _logger.debug("Adding new telemetry.");
        return _service.add(request);
    }

    @PutMapping
    MatchVesselResponse matchVessel(@RequestBody MatchVesselRequest request) {
        _logger.debug("Matching telemetry to vessel {}", request.getVessel().getVesselMmsi());
        return _service.match(request);
    }
}
