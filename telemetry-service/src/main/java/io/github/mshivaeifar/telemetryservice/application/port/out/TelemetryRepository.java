package io.github.mshivaeifar.telemetryservice.application.port.out;

import io.github.mshivaeifar.telemetryservice.domain.model.DriverLocation;

import java.util.Optional;
import java.util.UUID;

public interface TelemetryRepository {

    Optional<DriverLocation> findLatestLocation(
            UUID driverId
    );
}
