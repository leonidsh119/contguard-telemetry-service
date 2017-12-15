package com.contguard.telemetry.client.reader.api;

import com.contguard.telemetry.contract.Telemetry;

public interface ITelemetryReader {
    Iterable<Telemetry> read();
}
