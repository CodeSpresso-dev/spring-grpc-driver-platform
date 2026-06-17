package io.github.mshivaeifar.driverservice.presentation.response;

import io.github.mshivaeifar.driverservice.domain.model.DriverStatus;

import java.time.Instant;
import java.util.UUID;

public record DriverResponseBody(
        UUID id,
        String firstName,
        String lastName,
        String phoneNumber,
        String status,
        Instant createdAt
) {
}
