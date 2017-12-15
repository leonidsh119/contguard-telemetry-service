package com.contguard.telemetry.contract;

public class AddTelemetryResponse {
    private String id;

    public AddTelemetryResponse() {

    }

    public AddTelemetryResponse(String id) {
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
