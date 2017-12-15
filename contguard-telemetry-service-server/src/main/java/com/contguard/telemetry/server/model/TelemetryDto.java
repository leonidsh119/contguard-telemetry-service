package com.contguard.telemetry.server.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Telemetry")
public class TelemetryDto {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
    protected String id;

    @Column
    @Convert(converter = DateConverter.class)
    private LocalDateTime received;

    @Column
    private String lastLocation;

    @Column
    private String reason;

    @Column
    private String speedKph;

    @Column
    private String mileageKm;

    @Column
    private String heading;

    @Column
    private String gps;

    @Column
    private String installed;

    @Column
    private String button;

    @Column
    private String door;

    @Column
    private String mainPower;

    @Column
    private String light;

    @Column
    private String temperatureC;

    @Column
    private String address;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Column
    private String altitudeM;

    @Column
    private String name;

    @Column
    private Boolean isValidForMatch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Boolean getValidForMatch() {
        return isValidForMatch;
    }

    public void setValidForMatch(Boolean validForMatch) {
        isValidForMatch = validForMatch;
    }
}
