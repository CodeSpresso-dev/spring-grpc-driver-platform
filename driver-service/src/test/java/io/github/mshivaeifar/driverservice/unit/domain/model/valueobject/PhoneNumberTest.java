package io.github.mshivaeifar.driverservice.unit.domain.model.valueobject;

import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PhoneNumberTest {

    @Test
    void should_create_valid_phone_number() {

        PhoneNumber phoneNumber =
                new PhoneNumber("+989123456789");

        assertThat(
                "+989123456789").isEqualTo(phoneNumber.value());
    }

    @Test
    void should_fail_for_invalid_phone_number() {

        assertThatThrownBy(
                () -> new PhoneNumber("invalid")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_be_equal_for_same_value() {

        PhoneNumber first =
                new PhoneNumber("+989123456789");

        PhoneNumber second =
                new PhoneNumber("+989123456789");

        assertThat(first).isEqualTo(second);
    }

}
