package io.github.mshivaeifar.driverservice.domain.valueobject;

import java.util.Objects;

public record PhoneNumber(String value) {
    private static final String PHONE_REGEX =
            "^\\+?[0-9]{10,15}$";

    public PhoneNumber {
        Objects.requireNonNull(value, "phone number cannot be null");
        if (!value.matches(PHONE_REGEX)) {
            throw new IllegalArgumentException(
                    "invalid phone number: " + value
            );
        }
    }

    public static PhoneNumber of(String value) {
        return new PhoneNumber(value);

    }

    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof PhoneNumber other)) {
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
        return value;
    }
}
