package com.contguard.telemetry.contract;

import java.util.ArrayList;

public class MatchVesselResponse {
    private ArrayList<Telemetry> telemetries;

    public MatchVesselResponse() {
        telemetries = new ArrayList<>();
    }

    public void add(Telemetry telemetry) {
        telemetries.add(telemetry);
    }

    public ArrayList<Telemetry> getTelemetries() {
        return telemetries;
    }
}
