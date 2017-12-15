package com.contguard.telemetry.contract;

public class AddTelemetryRequest {
    private Telemetry telemetry;

    public AddTelemetryRequest() {

    }

    public AddTelemetryRequest(Telemetry telemetry) {
        setTelemetry(telemetry);
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
}
