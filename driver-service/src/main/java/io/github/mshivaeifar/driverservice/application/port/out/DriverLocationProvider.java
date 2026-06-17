package io.github.mshivaeifar.driverservice.application.port.out;

import io.github.mshivaeifar.driverservice.domain.model.DriverLocation;

import java.util.UUID;

public interface DriverLocationProvider {

    DriverLocation getCurrentLocation(UUID driverId);

}
