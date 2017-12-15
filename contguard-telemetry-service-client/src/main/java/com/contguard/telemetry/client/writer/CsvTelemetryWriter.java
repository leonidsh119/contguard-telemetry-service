package com.contguard.telemetry.client.writer;

import com.contguard.telemetry.client.writer.api.ITelemetryWriter;
import com.contguard.telemetry.contract.Telemetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class CsvTelemetryWriter extends CsvWriterBase implements ITelemetryWriter {
    private final Logger _logger = LoggerFactory.getLogger(getClass());
    private final String _caption = "Received,Last location,Reason,Speed (kph),Mileage (km),Heading,GPS,Installed,Button,Door,Main power,Light,Temperature,Address,Longitude,Latitude,Altitude (m),Name,Vessel (MMSI)";
    private final File _outputFile;

    public CsvTelemetryWriter(File outputFile) {
        super();
        _outputFile = outputFile;
    }

    @Override
    public void write(Iterable<Telemetry> telemetries) {
        _logger.debug("writing telemetries");
        if(_outputFile.exists()) {
            _logger.debug("deleting old output file");
            _outputFile.delete();
        }
        try {
            open(_outputFile);
            writeLine(_caption);
            telemetries.forEach(telemetry -> {
                try {
                    writeLine(toCsv(telemetry));
                } catch (IOException e) {
                    _logger.error("caught exception", e);
                }
            });
            close();
        } catch (IOException ioe) {
            _logger.error("caught exception", ioe);
        }
        _logger.debug("writing telemetries done.");
    }

    private String toCsv(Telemetry telemetry) {
        String line = "";
        line = appendColumn(line, telemetry.getReceived().toString());
        line = appendColumn(line, telemetry.getLastLocation());
        line = appendColumn(line, telemetry.getReason());
        line = appendColumn(line, telemetry.getSpeedKph());
        line = appendColumn(line, telemetry.getMileageKm());
        line = appendColumn(line, telemetry.getHeading());
        line = appendColumn(line, telemetry.getGps());
        line = appendColumn(line, telemetry.getInstalled());
        line = appendColumn(line, telemetry.getButton());
        line = appendColumn(line, telemetry.getDoor());
        line = appendColumn(line, telemetry.getMainPower());
        line = appendColumn(line, telemetry.getLight());
        line = appendColumn(line, telemetry.getTemperatureC());
        line = appendColumn(line, telemetry.getAddress());
        line = appendColumn(line, telemetry.getLongitude().toString());
        line = appendColumn(line, telemetry.getLatitude().toString());
        line = appendColumn(line, telemetry.getAltitudeM());
        line = appendColumn(line, telemetry.getName());
        line = appendColumn(line, String.format("%d", telemetry.getVesselMmsi()));
        return line;
    }

    private String appendColumn(String line, String value) {
        String separator = line.isEmpty() ? "" : ",";
        String wrapper = (value != null && value.contains(",")) ? "\"" : "";
        return String.format("%s%s%s%s%s", line, separator, wrapper, value, wrapper);
    }
}
