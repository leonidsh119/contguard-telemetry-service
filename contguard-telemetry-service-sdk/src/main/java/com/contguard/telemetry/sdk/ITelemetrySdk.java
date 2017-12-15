package com.contguard.telemetry.sdk;

import com.contguard.telemetry.contract.*;

public interface ITelemetrySdk {
    AddTelemetryResponse addTelemetry(AddTelemetryRequest request);
    MatchVesselResponse matchVessel(MatchVesselRequest matchVesselRequest);
    boolean healthCheck();
}
