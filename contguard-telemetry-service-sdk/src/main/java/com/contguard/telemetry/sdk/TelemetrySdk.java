package com.contguard.telemetry.sdk;

import com.contguard.telemetry.contract.*;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

public class TelemetrySdk implements ITelemetrySdk {
    private final RestTemplate _restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    private String _apiEndpoint;

    public TelemetrySdk(String serviceUrl) {
        _apiEndpoint = MessageFormat.format("{0}/telemetry", serviceUrl);
    }

    public TelemetrySdk(String ip, int port) {
        this(MessageFormat.format("{0}{1}:{2}", ip.contains("//") ? "" : "http://", ip, Integer.toString(port)));
    }

    @Override
    public AddTelemetryResponse addTelemetry(AddTelemetryRequest request) {
        String url = MessageFormat.format("{0}/telemetries", _apiEndpoint);
        ResponseEntity<AddTelemetryResponse> responseEntity = sendRequest(url, HttpMethod.POST, new HttpEntity<>(request), AddTelemetryResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public MatchVesselResponse matchVessel(MatchVesselRequest request) {
        String url = MessageFormat.format("{0}/telemetries", _apiEndpoint);
        ResponseEntity<MatchVesselResponse> responseEntity = sendRequest(url, HttpMethod.PUT, new HttpEntity<>(request), MatchVesselResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public boolean healthCheck() {
        String url = MessageFormat.format("{0}/healthcheck", _apiEndpoint);
        ResponseEntity<Void> responseEntity = sendRequest(url, HttpMethod.GET, new HttpEntity(new HttpHeaders()), Void.class);
        return responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    private <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, Class<T> responseType) {
        return this.sendRequest(url, method, (HttpEntity)null, responseType);
    }

    private <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, HttpEntity<?> entity, Class<T> responseType) {
        return _restTemplate.exchange(url, method, entity, responseType);
        // TODO: Handle errors
    }
}
