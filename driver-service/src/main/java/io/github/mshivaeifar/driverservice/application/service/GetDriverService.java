package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.helper.DriverFinder;
import io.github.mshivaeifar.driverservice.application.mapper.DriverMapper;
import io.github.mshivaeifar.driverservice.application.port.in.GetDriverUseCase;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetDriverService implements GetDriverUseCase {

    private final DriverFinder driverFinder;

    @Override
    public DriverResponse fetchDriver(DriverId driverId) {

        Driver driver = driverFinder.findById(driverId);

        return DriverMapper.toResponse(driver);
    }
}
