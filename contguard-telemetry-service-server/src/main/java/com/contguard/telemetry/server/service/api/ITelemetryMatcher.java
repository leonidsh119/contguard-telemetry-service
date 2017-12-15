package com.contguard.telemetry.server.service.api;

import com.contguard.telemetry.contract.Vessel;
import com.contguard.telemetry.server.model.TelemetryDto;

public interface ITelemetryMatcher {
    Iterable<TelemetryDto> match(Vessel vessel);
}
