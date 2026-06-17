package io.github.mshivaeifar.driverservice.domain.model;

import io.github.mshivaeifar.driverservice.domain.exception.InvalidDriverStateException;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Driver {

    private final DriverId id;
    private final Instant createdAt;

    private String firstName;
    private String lastName;
    private PhoneNumber phoneNumber;
    private DriverStatus status;

    private Driver(DriverId id,
                   String firstName,
                   String lastName,
                   PhoneNumber phoneNumber,
                   DriverStatus status,
                   Instant createdAt) {

        this.id = id;
        this.createdAt = Objects.requireNonNull(createdAt);

        this.firstName = validateName(firstName);
        this.lastName = validateName(lastName);
        this.phoneNumber = phoneNumber;

        this.status = Objects.requireNonNullElse(status, DriverStatus.OFFLINE);
    }

    public static Driver create(String firstName,
                                String lastName,
                                PhoneNumber phoneNumber) {

        return new Driver(
                DriverId.of(UUID.randomUUID()),
                firstName,
                lastName,
                phoneNumber,
                DriverStatus.OFFLINE,
                Instant.now()
        );
    }
    public static Driver restore(
            DriverId id,
            String firstName,
            String lastName,
            PhoneNumber phoneNumber,
            DriverStatus status,
            Instant createdAt
    ) {
        return new Driver(
                id,
                firstName,
                lastName,
                phoneNumber,
                status,
                createdAt
        );
    }

    public void goOnline() {
        if (this.status == DriverStatus.BUSY) {
            throw new InvalidDriverStateException("BUSY driver cannot go ONLINE directly");
        }
        this.status = DriverStatus.ONLINE;
    }

    public void goOffline() {
        this.status = DriverStatus.OFFLINE;
    }

    public void markBusy() {
        if (this.status != DriverStatus.ONLINE) {
            throw new InvalidDriverStateException("Only ONLINE driver can become BUSY");
        }
        this.status = DriverStatus.BUSY;
    }

    public void updateProfile(String firstName, String lastName, String phoneNumber) {
        this.firstName = validateName(firstName);
        this.lastName = validateName(lastName);
        this.phoneNumber = new PhoneNumber(validatePhone(phoneNumber));
    }

    // getters only (no setters for id/createdAt)
    public DriverId getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public DriverStatus getStatus() {
        return status;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return name;
    }

    private String validatePhone(String phone) {
        if (phone == null || !phone.matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        return phone;
    }

}