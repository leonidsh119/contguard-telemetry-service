package com.contguard.telemetry.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.contguard.telemetry.contract.serialization.ParseDeserializer;

import java.time.LocalDateTime;

public class Vessel {
    private int vesselMmsi;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    private LocalDateTime time;
    private Double longitude;
    private Double latitude;

    public int getVesselMmsi() {
        return vesselMmsi;
    }

    public void setVesselMmsi(int vesselMmsi) {
        this.vesselMmsi = vesselMmsi;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
