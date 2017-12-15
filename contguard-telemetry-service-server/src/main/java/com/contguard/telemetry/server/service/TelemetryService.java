package com.contguard.telemetry.server.service;

import com.contguard.telemetry.server.dal.ITelemetryDal;
import com.contguard.telemetry.server.model.TelemetryDto;
import com.contguard.telemetry.server.service.api.ITelemetryService;
import com.contguard.telemetry.contract.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelemetryService implements ITelemetryService {
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    @Value("${match.gps-validity}")
    private String _gpsValidity;

    @Value("${match.range-minutes}")
    private int _matchRangeMinutes;

    @Autowired
    private ITelemetryDal _telemetryDal;

    @Override
    @Transactional
    public AddTelemetryResponse add(AddTelemetryRequest request) {
        _logger.debug("Adding new telemetry.");
        TelemetryDto telemetryDto = toDto(request.getTelemetry());
        _telemetryDal.save(telemetryDto);
        _logger.debug("Done adding new telemetry.");
        return new AddTelemetryResponse(telemetryDto.getId());
    }

    @Override
    @Transactional
    public MatchVesselResponse match(MatchVesselRequest request) {
        _logger.debug("Matching vessel {}.", request.getVessel().getVesselMmsi());
        Vessel vessel = request.getVessel();
        LocalDateTime start = vessel.getTime().minusMinutes(_matchRangeMinutes);
        LocalDateTime end = vessel.getTime().plusMinutes(_matchRangeMinutes);
        List<TelemetryDto> matchingTelemetries = _telemetryDal.findByIsValidForMatchAndLongitudeAndLatitudeAndReceivedBetween(true, vessel.getLongitude(), vessel.getLatitude(), start, end);
        MatchVesselResponse response = new MatchVesselResponse();
        matchingTelemetries.forEach(telemetryDto -> response.add(toContract(telemetryDto, vessel.getVesselMmsi())));
        return response;
    }

    @Override
    @Transactional
    public void delete() {
        _logger.debug("Deleting all telemetries.");
        _telemetryDal.deleteAll();
        _logger.debug("All telemetries deleted.");
    }

    private TelemetryDto toDto(Telemetry telemetry) {
        TelemetryDto telemetryDto = new TelemetryDto();
        telemetryDto.setReceived(telemetry.getReceived());
        telemetryDto.setLastLocation(telemetry.getLastLocation());
        telemetryDto.setReason(telemetry.getReason());
        telemetryDto.setSpeedKph(telemetry.getSpeedKph());
        telemetryDto.setMileageKm(telemetry.getMileageKm());
        telemetryDto.setHeading(telemetry.getHeading());
        telemetryDto.setGps(telemetry.getGps());
        telemetryDto.setInstalled(telemetry.getInstalled());
        telemetryDto.setButton(telemetry.getButton());
        telemetryDto.setDoor(telemetry.getDoor());
        telemetryDto.setMainPower(telemetry.getMainPower());
        telemetryDto.setLight(telemetry.getLight());
        telemetryDto.setTemperatureC(telemetry.getTemperatureC());
        telemetryDto.setAddress(telemetry.getAddress());
        telemetryDto.setLongitude(telemetry.getLongitude());
        telemetryDto.setLatitude(telemetry.getLatitude());
        telemetryDto.setAltitudeM(telemetry.getAltitudeM());
        telemetryDto.setName(telemetry.getName());
        telemetryDto.setValidForMatch(isValidForMatch(telemetry.getGps()));
        return telemetryDto;
    }

    private Boolean isValidForMatch(String gps) {
        return gps.contains(_gpsValidity);
    }

    private Telemetry toContract(TelemetryDto telemetryDto, int vesselMmsi) {
        Telemetry telemetry = new Telemetry();
        telemetry.setReceived(telemetryDto.getReceived());
        telemetry.setLastLocation(telemetryDto.getLastLocation());
        telemetry.setReason(telemetryDto.getReason());
        telemetry.setSpeedKph(telemetryDto.getSpeedKph());
        telemetry.setMileageKm(telemetryDto.getMileageKm());
        telemetry.setHeading(telemetryDto.getHeading());
        telemetry.setGps(telemetryDto.getGps());
        telemetry.setInstalled(telemetryDto.getInstalled());
        telemetry.setButton(telemetryDto.getButton());
        telemetry.setDoor(telemetryDto.getDoor());
        telemetry.setMainPower(telemetryDto.getMainPower());
        telemetry.setLight(telemetryDto.getLight());
        telemetry.setTemperatureC(telemetryDto.getTemperatureC());
        telemetry.setAddress(telemetryDto.getAddress());
        telemetry.setLongitude(telemetryDto.getLongitude());
        telemetry.setLatitude(telemetryDto.getLatitude());
        telemetry.setAltitudeM(telemetryDto.getAltitudeM());
        telemetry.setName(telemetryDto.getName());
        telemetry.setVesselMmsi(vesselMmsi);
        return telemetry;
    }
}
