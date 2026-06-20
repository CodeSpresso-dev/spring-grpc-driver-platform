package io.github.mshivaeifar.telemetryservice.application.mapper;

import io.github.mshivaeifar.telemetryservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.domain.model.DriverLocation;

public class DriverCurrentLocationMapper {

    public static DriverLocationResponse toResponse(DriverLocation driverLocation) {
        return new DriverLocationResponse(
                driverLocation.driverId(),
                driverLocation.latitude(),
                driverLocation.longitude(),
                driverLocation.timestamp()
        );
    }
}
