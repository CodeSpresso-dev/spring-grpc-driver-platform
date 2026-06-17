package io.github.mshivaeifar.driverservice.application.mapper;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.driverservice.domain.model.DriverLocation;

public class DriverLocationMapper {
    private DriverLocationMapper() {
    }

    public static DriverLocationResponse toResponse(DriverLocation driverLocation) {

        return new DriverLocationResponse(
                driverLocation.driverId(),
                driverLocation.latitude(),
                driverLocation.longitude(),
                driverLocation.timestamp()
        );
    }
}
