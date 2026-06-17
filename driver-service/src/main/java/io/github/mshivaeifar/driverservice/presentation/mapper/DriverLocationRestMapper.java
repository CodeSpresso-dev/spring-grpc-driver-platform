package io.github.mshivaeifar.driverservice.presentation.mapper;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.driverservice.presentation.response.DriverLocationResponseBody;

public class DriverLocationRestMapper {
    public static DriverLocationResponseBody toResponse(DriverLocationResponse response){
        return new DriverLocationResponseBody(
                response.driverId(),
                response.latitude(),
                response.latitude(),
                response.timestamp()
        );
    }
}
