package io.github.mshivaeifar.driverservice.application.dto;

import io.github.mshivaeifar.driverservice.domain.model.DriverStatus;

public record UpdateDriverStatusRequest(
        DriverStatus status
) {
}
