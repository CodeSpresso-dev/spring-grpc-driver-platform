package io.github.mshivaeifar.driverservice.presentation.mapper;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.dto.RegisterDriverCommand;
import io.github.mshivaeifar.driverservice.presentation.request.RegisterDriverRequestBody;
import io.github.mshivaeifar.driverservice.presentation.response.DriverResponseBody;

public class DriverRestMapper {
    public static RegisterDriverCommand toCommand(RegisterDriverRequestBody requestBody) {
        return new RegisterDriverCommand(
                requestBody.firstName(),
                requestBody.lastName(),
                requestBody.phoneNumber()
        );
    }

    public static DriverResponseBody toResponse(DriverResponse response) {
        return new DriverResponseBody(
                response.id(),
                response.firstName(),
                response.lastName(),
                response.phoneNumber(),
                response.status().toString(),
                response.createdAt()
        );
    }
}
