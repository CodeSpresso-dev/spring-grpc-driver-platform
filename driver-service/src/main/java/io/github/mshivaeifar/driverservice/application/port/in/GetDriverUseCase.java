package io.github.mshivaeifar.driverservice.application.port.in;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;

public interface GetDriverUseCase {
    public DriverResponse fetchDriver(FetchByUUIDRequest request);
}
