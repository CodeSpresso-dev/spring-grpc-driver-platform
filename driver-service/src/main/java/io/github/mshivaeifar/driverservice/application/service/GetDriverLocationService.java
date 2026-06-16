package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocation;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;
import io.github.mshivaeifar.driverservice.application.port.in.GetDriverLocationUseCase;
import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.application.port.out.TelemetryClient;
import org.springframework.stereotype.Service;

@Service
public class GetDriverLocationService implements GetDriverLocationUseCase {
    private final GetDriverService getDriverService;
    private final TelemetryClient telemetryClient;

    public GetDriverLocationService(DriverRepository driverRepository, GetDriverService getDriverService, TelemetryClient telemetryClient) {
        this.getDriverService = getDriverService;
        this.telemetryClient = telemetryClient;
    }

    @Override
    public DriverLocation fetchLocation(FetchByUUIDRequest request) {
        getDriverService.fetchDriver(request);
        return telemetryClient.getCurrentLocation(request);
    }
}
