package io.github.mshivaeifar.telemetryservice.application.service;

import io.github.mshivaeifar.telemetryservice.application.port.out.TelemetryRepository;
import io.github.mshivaeifar.telemetryservice.domain.model.DriverLocation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TelemetryQueryService {

    private final TelemetryRepository repository;

    public DriverLocation getCurrentLocation(
            UUID driverId
    ) {
        return repository.findLatestLocation(driverId).orElseThrow();
    }


}
