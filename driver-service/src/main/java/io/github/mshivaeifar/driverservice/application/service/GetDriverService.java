package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.exception.DriverNotFoundException;
import io.github.mshivaeifar.driverservice.application.mapper.DriverMapper;
import io.github.mshivaeifar.driverservice.application.port.in.GetDriverUseCase;
import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;
import org.springframework.stereotype.Service;

@Service
public class GetDriverService implements GetDriverUseCase {

    private final DriverRepository driverRepository;

    public GetDriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverResponse fetchDriver(DriverId driverId) {
        Driver driver = driverRepository.findById(driverId.value()).orElseThrow(
                DriverNotFoundException::new
        );
        return DriverMapper.toResponse(driver);
    }
}
