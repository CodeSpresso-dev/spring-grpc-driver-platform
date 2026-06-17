package io.github.mshivaeifar.driverservice.application.dto;

import java.util.UUID;

public record DriverLocation(
        UUID driverId,
        double latitude,
        double longitude,
        java.time.Instant timestamp
) {
}
