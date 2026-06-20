package io.github.mshivaeifar.telemetryservice.application.port.in;

import io.github.mshivaeifar.telemetryservice.application.dto.DriverLocationResponse;

import java.util.UUID;

public interface GetDriverCurrentLocationUseCase {

    public DriverLocationResponse handle(UUID driverId);
}
