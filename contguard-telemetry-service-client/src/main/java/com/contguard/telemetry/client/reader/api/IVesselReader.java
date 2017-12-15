package com.contguard.telemetry.client.reader.api;

import com.contguard.telemetry.contract.Vessel;

public interface IVesselReader {
    Iterable<Vessel> read();
}
