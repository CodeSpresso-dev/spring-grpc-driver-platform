package io.github.mshivaeifar.telemetryservice.domain.model;

import java.time.Instant;
import java.util.UUID;

public record DriverLocation(
        UUID driverId,
        double latitude,
        double longitude,
        Instant timestamp
) {
}