package io.github.mshivaeifar.driverservice.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DriverId(UUID value) {

    public DriverId {
        Objects.requireNonNull(value, "DriverId cannot be null");
    }

    public static DriverId of(UUID value) {

        return new DriverId(value);
    }

    public UUID value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof DriverId other)) {
            return false;
        }

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
