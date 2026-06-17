package io.github.mshivaeifar.driverservice.presentation.response;

import java.time.Instant;
import java.util.UUID;

public record DriverLocationResponseBody(
        UUID driverId,
        double latitude,
        double longitude,
        Instant timestamp
) {
}
