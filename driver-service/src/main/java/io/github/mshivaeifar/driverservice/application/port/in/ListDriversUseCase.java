package io.github.mshivaeifar.driverservice.application.port.in;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;

import java.util.List;

public interface ListDriversUseCase {
    public List<DriverResponse> fetchDrivers();

}
