package com.contguard.telemetry.client.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

abstract class CsvReaderBase {
    private final Logger _logger = LoggerFactory.getLogger(getClass());
    private final Pattern _pattern = Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
    private final InputStream _csvStream;
    private final DateTimeFormatter _formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:m");

    CsvReaderBase(InputStream csvStream) {
        _csvStream = csvStream;
    }

    Iterable<String[]> parseCsv(boolean removeCaption)
    {
        _logger.debug("Parsing CSV file.");
        ArrayList<String[]> lines = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(_csvStream))) {
            while ((line = br.readLine()) != null) {
                lines.add(parseCsvLine(line));
            }
        } catch (IOException e) {
            _logger.error("Caught exception when parsing CSV file.", e);
        }
        if(removeCaption && !lines.isEmpty()) {
            _logger.debug("Removing caption line from parsed CSV lines.");
            lines.remove(lines.get(0));
        }
        _logger.debug("Done parsing CSV file.");
        return lines;
    }

    private String[] parseCsvLine(String line) {
        _logger.trace("Parsing CSV line [{}].", line);
        String[] fields = _pattern.split(line);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].replace("\"", "");
        }
        return fields;
    }

    LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, _formatter);
        } catch (DateTimeParseException dtpe) {
            return LocalDateTime.parse("1/1/1900 00:00", _formatter);
        }
    }
}
