package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.driverservice.application.helper.DriverValidator;
import io.github.mshivaeifar.driverservice.application.mapper.DriverLocationMapper;
import io.github.mshivaeifar.driverservice.application.port.in.GetDriverLocationUseCase;
import io.github.mshivaeifar.driverservice.application.port.out.DriverLocationProvider;
import io.github.mshivaeifar.driverservice.domain.model.DriverLocation;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetDriverLocationService implements GetDriverLocationUseCase {

    private final DriverValidator driverValidator;
    private final DriverLocationProvider driverLocationProvider;

    @Override
    public DriverLocationResponse fetchLocation(DriverId driverId) {
        driverValidator.ensureExists(driverId.value());
        DriverLocation currentLocation = driverLocationProvider.getCurrentLocation(driverId.value());
        return DriverLocationMapper.toResponse(currentLocation);
    }
}
