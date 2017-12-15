package com.contguard.telemetry.client.reader;

import com.contguard.telemetry.client.reader.api.IVesselReader;
import com.contguard.telemetry.contract.Vessel;

import java.io.InputStream;
import java.util.LinkedList;

public class CsvVesselReader extends CsvReaderBase implements IVesselReader {
    public CsvVesselReader(InputStream csvStream) {
        super(csvStream);
    }

    @Override
    public Iterable<Vessel> read() {
        Iterable<String[]> lines = parseCsv(true);
        LinkedList<Vessel> vessels = new LinkedList<>();
        lines.forEach(line -> vessels.add(toVessel(line)));
        return vessels;
    }

    private Vessel toVessel(String[] line) {
        Vessel vessel = new Vessel();
        vessel.setVesselMmsi(Integer.parseInt(line[0]));
        vessel.setTime(parseDateTime(line[1]));
        vessel.setLongitude(Double.parseDouble(line[2]));
        vessel.setLatitude(Double.parseDouble(line[3]));
        return vessel;
    }
}
