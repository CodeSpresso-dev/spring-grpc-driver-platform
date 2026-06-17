package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.dto.RegisterDriverCommand;
import io.github.mshivaeifar.driverservice.application.exception.DuplicatePhoneNumberException;
import io.github.mshivaeifar.driverservice.application.mapper.DriverMapper;
import io.github.mshivaeifar.driverservice.application.port.in.RegisterDriverUseCase;
import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterDriverService implements RegisterDriverUseCase {

    private final DriverRepository driverRepository;

    @Override
    public DriverResponse registerDriver(RegisterDriverCommand request) {

        PhoneNumber phoneNumber = PhoneNumber.of(request.phoneNumber());

        boolean isDriverExists = driverRepository.existsByPhoneNumber(phoneNumber);
        if (isDriverExists)
            throw new DuplicatePhoneNumberException();

        Driver driver = DriverMapper.toDomain(request);

        Driver savedDriver = driverRepository.save(driver);

        return DriverMapper.toResponse(savedDriver);
    }
}
