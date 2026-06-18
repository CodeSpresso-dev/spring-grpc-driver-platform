package io.github.mshivaeifar.driverservice.unit.application;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.dto.RegisterDriverCommand;
import io.github.mshivaeifar.driverservice.application.exception.DuplicatePhoneNumberException;
import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.application.service.RegisterDriverService;
import io.github.mshivaeifar.driverservice.common.RegisterDriverCommandBuilder;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;
import io.github.mshivaeifar.driverservice.integration.persistence.adapter.DriverBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterDriveServiceTest {

    @Mock
    private DriverRepository repository;

    @InjectMocks
    RegisterDriverService registerDriverService;

    @Test
    public void given_valid_driver_when_registered_should_return_driver_successfully() {
        //Given
        RegisterDriverCommand registerDriverCommand = new RegisterDriverCommandBuilder().buildValidCommand();
        Driver driver = new DriverBuilder().driverId(
                        UUID.randomUUID())
                .phoneNumber(PhoneNumber.of(registerDriverCommand.phoneNumber()))
                .build();
        when(repository.existsByPhoneNumber(any(PhoneNumber.class))).thenReturn(false);
        when(repository.save(any(Driver.class))).thenReturn(driver);

        //When
        DriverResponse registeredDriver = registerDriverService.registerDriver(registerDriverCommand);

        //Then
        assertNotNull(registeredDriver);
        ArgumentCaptor<Driver> driverCaptor = ArgumentCaptor.forClass(Driver.class);
        verify(repository).save(driverCaptor.capture());
        Driver savedDriver = driverCaptor.getValue();
        assertThat(registerDriverCommand.phoneNumber()).isEqualTo(savedDriver.getPhoneNumber().value());

        verify(repository, times(1)).existsByPhoneNumber(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void given_duplicate_phone_when_registered_should_throw_duplicatePhoneNumberException() {
        //Given
        RegisterDriverCommand registerDriverCommand = new RegisterDriverCommandBuilder().buildValidCommand();
        when(repository.existsByPhoneNumber(any(PhoneNumber.class))).thenReturn(true);

        //When
        assertThatThrownBy(() -> registerDriverService.registerDriver(registerDriverCommand))
                .isInstanceOf(DuplicatePhoneNumberException.class);

        //Then
    }

}
