package com.contguard.telemetry.client.writer.api;

import com.contguard.telemetry.contract.Telemetry;

public interface ITelemetryWriter {
    void write(Iterable<Telemetry> telemetries);
}
