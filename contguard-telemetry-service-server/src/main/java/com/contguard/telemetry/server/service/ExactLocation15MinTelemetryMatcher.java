package com.contguard.telemetry.server.service;

import com.contguard.telemetry.contract.Vessel;
import com.contguard.telemetry.server.dal.ITelemetryDal;
import com.contguard.telemetry.server.model.TelemetryDto;
import com.contguard.telemetry.server.service.api.ITelemetryMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class ExactLocation15MinTelemetryMatcher implements ITelemetryMatcher {
    @Autowired
    ITelemetryDal _telemetryDal;

    @Value("${match.range-minutes}")
    private int _matchRangeMinutes;

    @Override
    public Iterable<TelemetryDto> match(Vessel vessel) {
        LocalDateTime start = vessel.getTime().minusMinutes(_matchRangeMinutes);
        LocalDateTime end = vessel.getTime().plusMinutes(_matchRangeMinutes);
        return _telemetryDal.findByIsValidForMatchAndLongitudeAndLatitudeAndReceivedBetween(true, vessel.getLongitude(), vessel.getLatitude(), start, end);
    }
}
