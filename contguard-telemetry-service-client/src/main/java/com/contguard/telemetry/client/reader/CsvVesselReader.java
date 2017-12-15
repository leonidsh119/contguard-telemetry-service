package com.contguard.telemetry.client.reader;

import com.contguard.telemetry.client.reader.api.IVesselReader;
import com.contguard.telemetry.contract.Vessel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.LinkedList;

public class CsvVesselReader extends CsvReaderBase implements IVesselReader {
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    public CsvVesselReader(InputStream csvStream) {
        super(csvStream);
    }

    @Override
    public Iterable<Vessel> read() {
        _logger.debug("Reading vessels from CSV file.");
        Iterable<String[]> lines = parseCsv(true);
        LinkedList<Vessel> vessels = new LinkedList<>();
        lines.forEach(line -> vessels.add(toVessel(line)));
        _logger.debug("Done reading vessels from CSV file.");
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
