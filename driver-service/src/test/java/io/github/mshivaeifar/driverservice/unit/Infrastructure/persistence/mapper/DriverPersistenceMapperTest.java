package io.github.mshivaeifar.driverservice.unit.Infrastructure.persistence.mapper;

import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.model.DriverStatus;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.entity.DriverEntity;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.mapper.DriverPersistenceMapper;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DriverPersistenceMapperTest {
    @Test
    void should_map_domain_to_entity() {

        PhoneNumber phoneNumber = PhoneNumber.of("+989123456789");

        Driver driver =
                Driver.create(
                        "Mahdi",
                        "Shivaeifar",
                        phoneNumber
                );

        DriverEntity entity =
                DriverPersistenceMapper.toEntity(driver);

        assertThat(
                driver.getId().value())
                .isEqualTo(entity.getId());

        assertThat(
                driver.getPhoneNumber().value())
                .isEqualTo(entity.getPhoneNumber());

    }

    @Test
    void should_map_entity_to_domain() {
        String phoneNumber = "+989123456789";
        DriverEntity entity =
                DriverEntity.builder()
                        .id(UUID.randomUUID())
                        .firstName("Mahdi")
                        .lastName("Shivaeifar")
                        .phoneNumber(phoneNumber)
                        .status(DriverStatus.ONLINE)
                        .createdAt(Instant.now())
                        .build();

        Driver driver =
                DriverPersistenceMapper.toDomain(entity);

        assertThat(
                phoneNumber).isEqualTo(
                driver.getPhoneNumber().value());

        assertThat(
                DriverStatus.ONLINE).isEqualTo(
                driver.getStatus()
        );
    }

}
