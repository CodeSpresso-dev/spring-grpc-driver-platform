package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocation;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;
import io.github.mshivaeifar.driverservice.application.port.in.GetDriverLocationUseCase;
import io.github.mshivaeifar.driverservice.application.port.out.TelemetryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetDriverLocationService implements GetDriverLocationUseCase {

    private final GetDriverService getDriverService;
    private final TelemetryClient telemetryClient;

    @Override
    public DriverLocation fetchLocation(FetchByUUIDRequest request) {
        getDriverService.fetchDriver(request);
        return telemetryClient.getCurrentLocation(request);
    }
}
