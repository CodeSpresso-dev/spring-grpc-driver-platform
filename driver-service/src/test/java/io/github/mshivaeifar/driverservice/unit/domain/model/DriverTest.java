package io.github.mshivaeifar.driverservice.unit.domain.model;

import io.github.mshivaeifar.driverservice.domain.exception.InvalidDriverStateException;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.model.DriverStatus;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DriverTest {

    @Test
    public void should_create_driver_with_offline_status() {
        //Arrange
        PhoneNumber phoneNumber = PhoneNumber.of("+12345678901");

        //Act
        Driver driver = Driver.create("Mehdi",
                "Shivaeifar",phoneNumber
                );
        //Assert
        assertThat(driver.getStatus()).isEqualTo(DriverStatus.OFFLINE);
        assertThat(driver.getId()).isNotNull();
    }

    @Test
    void should_change_status_to_online() {
        //Arrange
        PhoneNumber phoneNumber = PhoneNumber.of("+12345678901");
        Driver driver = Driver.create("Mahdi", "Shivaeifar", phoneNumber);

        //Act
        driver.goOnline();

        //Assert
        assertThat(DriverStatus.ONLINE).isEqualTo(driver.getStatus());
    }

    @Test
    void busy_driver_cannot_go_online_directly() {
        //Arrange
        PhoneNumber phoneNumber = PhoneNumber.of("+12345678901");
        Driver driver = Driver.create("Mahdi", "Shivaeifar", phoneNumber);

        driver.goOnline();
        driver.markBusy();

        //Act
        assertThatThrownBy(driver::goOnline).isInstanceOf(InvalidDriverStateException.class)

        //Assert
                .hasMessageContaining("BUSY driver cannot go ONLINE");
    }

}
