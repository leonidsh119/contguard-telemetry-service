package com.contguard.telemetry.client.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public abstract class CsvWriterBase {
    private final Logger _logger = LoggerFactory.getLogger(getClass());
    private FileWriter _writer;

    CsvWriterBase() {
        _writer = null;
    }

    void open(File outputFile) throws IOException {
        if(_writer != null) {
            _logger.warn("CSV writer is already opened. Closing.");
            close();
        }
        _logger.debug("Opening CSV writer for {} file", outputFile.getAbsolutePath());
        _writer = new FileWriter(outputFile.getCanonicalPath());
    }

    void close() throws IOException {
        _logger.debug("Closing CSV writer.");
        _writer.close();
        _writer = null;
    }

    void writeLine(String line) throws IOException {
        if(_writer == null) {
            throw new RuntimeException("CSV writer is closed. Call method open() first.");
        }
        _logger.trace("Writing CSV line to file.");
        _writer.write(line);
        _writer.write(System.lineSeparator());
    }
}
