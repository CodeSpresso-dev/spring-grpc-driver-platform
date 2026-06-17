package io.github.mshivaeifar.driverservice.application.port.in;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;

public interface GetDriverLocationUseCase {
    public DriverLocationResponse fetchLocation(FetchByUUIDRequest request);
}
