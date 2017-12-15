package com.contguard.telemetry.client.writer;

import java.io.*;

public abstract class CsvWriterBase {
    private FileWriter _writer;

    CsvWriterBase() {
        _writer = null;
    }

    void open(File outputFile) throws IOException {
        if(_writer != null) {
            close();
        }
        _writer = new FileWriter(outputFile.getCanonicalPath());
    }

    void close() throws IOException {
        _writer.close();
        _writer = null;
    }

    void writeLine(String line) throws IOException {
        if(_writer == null) {
            throw new RuntimeException("CSV writer is closer. Call method open() first.");
        }
        _writer.write(line);
        _writer.write(System.lineSeparator());
    }
}
