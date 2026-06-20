package io.github.mshivaeifar.telemetryservice.application.dto;

import java.time.Instant;
import java.util.UUID;

public record DriverLocationResponse(
        UUID driverId,
        double latitude,
        double longitude,
        Instant timestamp
) {
}