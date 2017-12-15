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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class ClientApplication {
    private final static Logger _logger = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String[] args) {
        _logger.info("Starting Client application.");
        ITelemetrySdk sdk = new TelemetrySdk("localhost", 8080);
        sendTelemetries(sdk);
        Iterable<Telemetry> matchingTelemetries = matchVessels(sdk);
        writeToFile(matchingTelemetries);
        _logger.info("Client application ended.");
    }

    private static void sendTelemetries(ITelemetrySdk sdk) {
        _logger.info("Reading telemetries from resource file.");
        InputStream telemetriesStream = ClientApplication.class.getClassLoader().getResourceAsStream("telemetries.csv");
        ITelemetryReader telemetriesStreamReader = new CsvTelemetryReader(telemetriesStream);
        Iterable<Telemetry> telemetries = telemetriesStreamReader.read();
        _logger.info("Sending telemetries to Server.");
        telemetries.forEach(telemetry -> sdk.addTelemetry(new AddTelemetryRequest(telemetry)));
    }

    private static Iterable<Telemetry> matchVessels(ITelemetrySdk sdk) {
        _logger.info("Reading vessels from resource file.");
        ArrayList<Telemetry> matchedTelemetries = new ArrayList<>();
        InputStream vesselsStream = ClientApplication.class.getClassLoader().getResourceAsStream("vessels.csv");
        IVesselReader vesselReader = new CsvVesselReader(vesselsStream);
        Iterable<Vessel> vessels = vesselReader.read();
        _logger.info("Matching vessels to telemetries.");
        vessels.forEach(vessel -> {
            _logger.trace("Matching vessel {}.", vessel.getVesselMmsi());
            MatchVesselResponse response = sdk.matchVessel(new MatchVesselRequest(vessel));
            matchedTelemetries.addAll(response.getTelemetries());
        });
        return matchedTelemetries;
    }

    private static void writeToFile(Iterable<Telemetry> matchingTelemetries) {
        File matchedTelemetriesFileName = new File("matchedTelemetries.csv");
        _logger.info("Writing matched telemetries to file {}.", matchedTelemetriesFileName.getPath());
        ITelemetryWriter telemetryWriter = new CsvTelemetryWriter(matchedTelemetriesFileName);
        telemetryWriter.write(matchingTelemetries);
    }
}
