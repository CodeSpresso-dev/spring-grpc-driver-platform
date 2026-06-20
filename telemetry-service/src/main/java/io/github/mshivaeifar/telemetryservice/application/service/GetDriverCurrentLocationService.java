package io.github.mshivaeifar.telemetryservice.application.service;

import io.github.mshivaeifar.telemetryservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.application.mapper.DriverCurrentLocationMapper;
import io.github.mshivaeifar.telemetryservice.application.port.in.GetDriverCurrentLocationUseCase;
import io.github.mshivaeifar.telemetryservice.application.port.out.TelemetryRepository;
import io.github.mshivaeifar.telemetryservice.domain.model.DriverLocation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
class GetDriverCurrentLocationService implements GetDriverCurrentLocationUseCase {

    private final TelemetryRepository repository;

    @Override
    public DriverLocationResponse handle(UUID driverId) {
        DriverLocation driverLocation = repository.findLatestLocation(driverId).orElseThrow();

        return DriverCurrentLocationMapper.toResponse(driverLocation);
    }
}
