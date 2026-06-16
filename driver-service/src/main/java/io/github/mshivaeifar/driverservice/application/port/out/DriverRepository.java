package io.github.mshivaeifar.driverservice.application.port.out;

import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DriverRepository {

    Driver save(Driver driver);

    Optional<Driver> findById(UUID id);

    List<Driver> findAll();

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
