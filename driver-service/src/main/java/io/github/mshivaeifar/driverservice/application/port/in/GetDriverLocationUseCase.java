package io.github.mshivaeifar.driverservice.application.port.in;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;

public interface GetDriverLocationUseCase {
    public DriverLocationResponse fetchLocation(DriverId driverId);
}
