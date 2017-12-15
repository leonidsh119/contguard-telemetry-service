package com.contguard.telemetry.contract;

public class MatchVesselRequest {
    private Vessel vessel;

    public MatchVesselRequest() {

    }

    public MatchVesselRequest(Vessel vessel) {
        setVessel(vessel);
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }
}
