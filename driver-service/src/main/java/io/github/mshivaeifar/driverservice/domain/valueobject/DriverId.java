package io.github.mshivaeifar.driverservice.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DriverId(UUID value) {
    public static DriverId of(UUID value) {
        Objects.requireNonNull(value, "DriverId cannot be null");
        return new DriverId(value);
    }
}
