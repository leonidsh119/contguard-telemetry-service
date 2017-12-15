package com.contguard.telemetry.client;

import com.contguard.telemetry.client.reader.CsvTelemetryReader;
import com.contguard.telemetry.client.reader.CsvVesselReader;
import com.contguard.telemetry.client.reader.api.ITelemetryReader;
import com.contguard.telemetry.client.reader.api.IVesselReader;
import com.contguard.telemetry.client.writer.CsvTelemetryWriter;
import com.contguard.telemetry.client.writer.api.ITelemetryWriter;
import com.contguard.telemetry.contract.*;
import com.contguard.telemetry.sdk.ITelemetrySdk;
import com.contguard.telemetry.sdk.TelemetrySdk;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class ClientApplication {
    public static void main(String[] args) {
        ITelemetrySdk sdk = new TelemetrySdk("localhost", 8080);
        sendTelemetries(sdk);
        Iterable<Telemetry> matchingTelemetries = matchVessels(sdk);
        writeToFile(matchingTelemetries);
    }

    private static void sendTelemetries(ITelemetrySdk sdk) {
        InputStream telemetriesStream = ClientApplication.class.getClassLoader().getResourceAsStream("telemetries.csv");
        ITelemetryReader telemetriesStreamReader = new CsvTelemetryReader(telemetriesStream);
        Iterable<Telemetry> telemetries = telemetriesStreamReader.read();
        telemetries.forEach(telemetry -> sdk.addTelemetry(new AddTelemetryRequest(telemetry)));
    }

    private static Iterable<Telemetry> matchVessels(ITelemetrySdk sdk) {
        ArrayList<Telemetry> matchedTelemetries = new ArrayList<>();
        InputStream vesselsStream = ClientApplication.class.getClassLoader().getResourceAsStream("vessels.csv");
        IVesselReader vesselReader = new CsvVesselReader(vesselsStream);
        Iterable<Vessel> vessels = vesselReader.read();
        vessels.forEach(vessel -> {
            MatchVesselResponse response = sdk.matchVessel(new MatchVesselRequest(vessel));
            matchedTelemetries.addAll(response.getTelemetries());
        });
        return matchedTelemetries;
    }

    private static void writeToFile(Iterable<Telemetry> matchingTelemetries) {
        File matchedTelemetriesFileName = new File("matchedTelemetries.csv");
        ITelemetryWriter telemetryWriter = new CsvTelemetryWriter(matchedTelemetriesFileName);
        telemetryWriter.write(matchingTelemetries);
    }
}
