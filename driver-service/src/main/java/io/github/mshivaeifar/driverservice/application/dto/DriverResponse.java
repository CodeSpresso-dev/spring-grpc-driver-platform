package io.github.mshivaeifar.driverservice.application.dto;

import io.github.mshivaeifar.driverservice.domain.model.DriverStatus;

import java.time.Instant;
import java.util.UUID;

public record DriverResponse(
        UUID id,
        String firstName,
        String lastName,
        String phoneNumber,
        DriverStatus status,
        Instant createdAt
) {
}
