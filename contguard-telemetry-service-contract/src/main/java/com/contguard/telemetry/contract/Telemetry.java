package com.contguard.telemetry.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.contguard.telemetry.contract.serialization.ParseDeserializer;

import java.time.LocalDateTime;

public class Telemetry {
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    private LocalDateTime received;
    private String lastLocation;
    private String reason;
    private String speedKph;
    private String mileageKm;
    private String heading;
    private String gps;
    private String installed;
    private String button;
    private String door;
    private String mainPower;
    private String light;
    private String temperatureC;
    private String address;
    private Double longitude;
    private Double latitude;
    private String altitudeM;
    private String name;
    private int vesselMmsi;

    public LocalDateTime getReceived() {
        return received;
    }

    public void setReceived(LocalDateTime received) {
        this.received = received;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSpeedKph() {
        return speedKph;
    }

    public void setSpeedKph(String speedKph) {
        this.speedKph = speedKph;
    }

    public String getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(String mileageKm) {
        this.mileageKm = mileageKm;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getInstalled() {
        return installed;
    }

    public void setInstalled(String installed) {
        this.installed = installed;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getMainPower() {
        return mainPower;
    }

    public void setMainPower(String mainPower) {
        this.mainPower = mainPower;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(String temperatureC) {
        this.temperatureC = temperatureC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAltitudeM() {
        return altitudeM;
    }

    public void setAltitudeM(String altitudeM) {
        this.altitudeM = altitudeM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVesselMmsi() {
        return vesselMmsi;
    }

    public void setVesselMmsi(int vesselMmsi) {
        this.vesselMmsi = vesselMmsi;
    }
}
