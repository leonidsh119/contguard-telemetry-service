package com.contguard.telemetry.client.reader;

import com.contguard.telemetry.client.reader.api.ITelemetryReader;
import com.contguard.telemetry.contract.Telemetry;

import java.io.InputStream;
import java.util.LinkedList;

public class CsvTelemetryReader extends CsvReaderBase implements ITelemetryReader {
    public CsvTelemetryReader(InputStream csvStream) {
        super(csvStream);
    }

    @Override
    public Iterable<Telemetry> read() {
        Iterable<String[]> lines = parseCsv(true);
        LinkedList<Telemetry> vessels = new LinkedList<>();
        lines.forEach(line -> vessels.add(toTelemetry(line)));
        return vessels;
    }

    private Telemetry toTelemetry(String[] line) {
        Telemetry telemetry = new Telemetry();
        telemetry.setReceived(parseDateTime(line[0]));
        telemetry.setLastLocation(line[1]);
        telemetry.setReason(line[2]);
        telemetry.setSpeedKph(line[3]);
        telemetry.setMileageKm(line[4]);
        telemetry.setHeading(line[5]);
        telemetry.setGps(line[6]);
        telemetry.setInstalled(line[7]);
        telemetry.setButton(line[8]);
        telemetry.setDoor(line[9]);
        telemetry.setMainPower(line[10]);
        telemetry.setLight(line[11]);
        telemetry.setTemperatureC(line[12]);
        telemetry.setAddress(line[13]);
        telemetry.setLongitude(Double.parseDouble(line[14]));
        telemetry.setLatitude(Double.parseDouble(line[15]));
        telemetry.setAltitudeM(line[16]);
        telemetry.setName(line[17]);
        return telemetry;
    }
}
