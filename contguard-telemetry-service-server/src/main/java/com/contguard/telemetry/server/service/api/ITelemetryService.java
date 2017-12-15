package com.contguard.telemetry.server.service.api;

import com.contguard.telemetry.contract.*;

public interface ITelemetryService {
    AddTelemetryResponse add(AddTelemetryRequest addTelemetryRequest);
    MatchVesselResponse match(MatchVesselRequest request);
}
