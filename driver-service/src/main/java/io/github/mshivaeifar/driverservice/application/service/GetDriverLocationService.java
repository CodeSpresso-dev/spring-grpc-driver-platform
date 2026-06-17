package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;
import io.github.mshivaeifar.driverservice.application.port.in.GetDriverLocationUseCase;
import io.github.mshivaeifar.driverservice.application.port.out.DriverLocationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetDriverLocationService implements GetDriverLocationUseCase {

    private final GetDriverService getDriverService;
    private final DriverLocationProvider driverLocationProvider;

    @Override
    public DriverLocationResponse fetchLocation(FetchByUUIDRequest request) {
        getDriverService.fetchDriver(request);
        driverLocationProvider.getCurrentLocation(request.driverId());
        return null;
    }
}
