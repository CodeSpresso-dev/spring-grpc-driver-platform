package io.github.mshivaeifar.telemetryservice.presentation.rest.dto;

public record DriverLocationRestResponse(String driverId,
                                         double latitude,
                                         double longitude,
                                         String timestamp) {
}
