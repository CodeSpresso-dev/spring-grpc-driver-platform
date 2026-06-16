package io.github.mshivaeifar.driverservice.infrastructure.persistence.mapper;

import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.entity.DriverEntity;

public class DriverPersistenceMapper {

    public static DriverEntity toEntity(Driver driver) {
        return DriverEntity.builder()
                .createdAt(driver.getCreatedAt())
                .phoneNumber(driver.getPhoneNumber().value())
                .id(driver.getId())
                .lastName(driver.getLastName())
                .firstName(driver.getFirstName())
                .status(driver.getStatus())
                .build();
    }

    public static Driver toDomain(DriverEntity entity) {
        return Driver.restore(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                PhoneNumber.of(entity.getPhoneNumber()),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

}
