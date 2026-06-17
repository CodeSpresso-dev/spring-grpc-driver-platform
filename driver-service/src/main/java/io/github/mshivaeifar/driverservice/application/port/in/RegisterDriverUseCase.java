package io.github.mshivaeifar.driverservice.application.port.in;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.dto.RegisterDriverCommand;

public interface RegisterDriverUseCase {
    public DriverResponse registerDriver(RegisterDriverCommand request);
}
