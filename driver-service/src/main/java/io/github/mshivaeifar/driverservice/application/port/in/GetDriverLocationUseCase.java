package io.github.mshivaeifar.driverservice.application.port.in;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocation;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;

public interface GetDriverLocationUseCase {
    public DriverLocation fetchLocation(FetchByUUIDRequest request);
}
