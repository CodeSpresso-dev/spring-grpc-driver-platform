package io.github.mshivaeifar.telemetryservice.infrastructure.persistence;

import io.github.mshivaeifar.telemetryservice.application.port.out.TelemetryRepository;
import io.github.mshivaeifar.telemetryservice.domain.model.DriverLocation;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryTelemetryRepository implements TelemetryRepository {
    @Override
    public Optional<DriverLocation> findLatestLocation(UUID driverId) {
        return Optional.of(new DriverLocation(
                UUID.randomUUID(),
                14.02,
                45.02,
                Instant.now()
        ));
    }
}
