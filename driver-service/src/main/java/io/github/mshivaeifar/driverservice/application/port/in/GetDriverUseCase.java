package io.github.mshivaeifar.driverservice.application.port.in;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;

public interface GetDriverUseCase {
    public DriverResponse fetchDriver(DriverId driverId);
}
