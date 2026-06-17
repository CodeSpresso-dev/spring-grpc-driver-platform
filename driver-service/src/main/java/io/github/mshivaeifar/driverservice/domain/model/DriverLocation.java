package io.github.mshivaeifar.driverservice.domain.model;

import java.util.UUID;

public record DriverLocation(
        UUID driverId,
        double latitude,
        double longitude,
        java.time.Instant timestamp
) {
}
