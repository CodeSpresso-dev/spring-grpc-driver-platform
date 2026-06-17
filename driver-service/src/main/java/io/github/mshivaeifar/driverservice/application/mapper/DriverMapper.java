package io.github.mshivaeifar.driverservice.application.mapper;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.dto.RegisterDriverCommand;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;

public final class DriverMapper {

    private DriverMapper() {
    }

    public static DriverResponse toResponse(Driver driver) {
        return new DriverResponse(
                driver.getId().value(),
                driver.getFirstName(),
                driver.getLastName(),
                driver.getPhoneNumber().value(),
                driver.getStatus(),
                driver.getCreatedAt()
        );
    }

    public static Driver toDomain(RegisterDriverCommand request) {
        PhoneNumber phoneNumber = PhoneNumber.of(request.phoneNumber());
        return Driver.create(
                request.firstName()
                , request.lastName()
                , phoneNumber);
    }
}
