package io.github.mshivaeifar.driverservice.application.port.in;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.dto.RegisterDriverRequest;

public interface RegisterDriverUseCase {
    public DriverResponse registerDriver(RegisterDriverRequest request);
}
