package com.contguard.telemetry.server.test;

import com.contguard.telemetry.contract.*;
import com.contguard.telemetry.sdk.ITelemetrySdk;
import com.contguard.telemetry.sdk.TelemetrySdk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TelemetryTest {
    @LocalServerPort
    private int _randomServerPort;
    private ITelemetrySdk _sdk;
    private final DateTimeFormatter _formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss a");

    @PostConstruct
    private void initSdk() {
        _sdk = new TelemetrySdk("localhost", _randomServerPort);
    }

    @Test
    public void matchTest() {
        // Add Telemetry
        Telemetry telemetry = new Telemetry();
        telemetry.setReceived(LocalDateTime.parse("7/12/2017 10:18:00 AM", _formatter));
        telemetry.setLastLocation("7/12/2017 10:18:00 AM");
        telemetry.setReason("Tracking");
        telemetry.setSpeedKph("0");
        telemetry.setMileageKm("0");
        telemetry.setHeading("0");
        telemetry.setGps("4 / On / Valid");
        telemetry.setInstalled("Off");
        telemetry.setButton("On");
        telemetry.setDoor("Opened");
        telemetry.setMainPower("64% / 3.92v");
        telemetry.setLight(null);
        telemetry.setTemperatureC("24.0c");
        telemetry.setAddress("Sindelfingen (Customer Daimler Passengers: Daimler Car Factory (Bela-Barenyi-Str. 1, Germany))");
        telemetry.setLongitude(8.9928);
        telemetry.setLatitude(48.7037);
        telemetry.setAltitudeM("444");
        telemetry.setName("591016");
        AddTelemetryRequest addTelemetryRequest = new AddTelemetryRequest();
        addTelemetryRequest.setTelemetry(telemetry);
        AddTelemetryResponse addTelemetryResponse = _sdk.addTelemetry(addTelemetryRequest);
        assertThat(addTelemetryResponse.getId()).isNotNull();
        assertThat(addTelemetryResponse.getId()).isNotBlank();

        // Match Vessel
        Vessel vessel = new Vessel();
        vessel.setVesselMmsi(264162572);
        vessel.setTime(LocalDateTime.parse("7/12/2017 10:18:00 AM", _formatter));
        vessel.setLongitude(8.9928);
        vessel.setLatitude(48.7037);
        MatchVesselRequest matchVesselRequest = new MatchVesselRequest();
        matchVesselRequest.setVessel(vessel);
        MatchVesselResponse matchVesselResponse = _sdk.matchVessel(matchVesselRequest);
        ArrayList<Telemetry> matchingTelemetries = matchVesselResponse.getTelemetries();
        assertThat(matchingTelemetries).isNotNull();
        assertThat(matchingTelemetries.isEmpty()).isFalse();
    }
}
