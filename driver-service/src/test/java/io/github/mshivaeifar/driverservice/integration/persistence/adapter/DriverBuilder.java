package io.github.mshivaeifar.driverservice.integration.persistence.adapter;

import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.model.DriverStatus;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;

import java.time.Instant;
import java.util.UUID;

public class DriverBuilder {

    private UUID id = UUID.randomUUID();
    private Instant createdAt = Instant.now();

    private String firstName = "John";
    private String lastName = "Doe";
    private PhoneNumber phoneNumber = PhoneNumber.of("+989123456789");
    private DriverStatus status = DriverStatus.OFFLINE;

    public DriverBuilder driverId(UUID id) {
        this.id = id;
        return this;
    }

    public DriverBuilder createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public DriverBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public DriverBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public DriverBuilder phoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public DriverBuilder status(DriverStatus status) {
        this.status = status;
        return this;
    }

    public Driver build() {
        return Driver.restore(
                id,
                firstName,
                lastName,
                phoneNumber,
                status,
                createdAt);
    }
}
