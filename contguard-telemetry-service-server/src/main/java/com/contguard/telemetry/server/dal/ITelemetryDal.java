package com.contguard.telemetry.server.dal;

import com.contguard.telemetry.server.model.TelemetryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ITelemetryDal extends JpaRepository<TelemetryDto, String> {
    List<TelemetryDto> findByIsValidForMatchAndLongitudeAndLatitudeAndReceivedBetween(Boolean isValidForMatch, Double longitude, Double latitude, LocalDateTime start, LocalDateTime end);
}
