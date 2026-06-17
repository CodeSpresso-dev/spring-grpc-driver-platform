package io.github.mshivaeifar.driverservice.infrastructure.grpc.mapper;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocation;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class TelemetryGrpcClientMapper {

    public DriverLocation toDomain(
            GetDriverLocationResponse response
    ) {

        return new DriverLocation(
                UUID.fromString(response.getDriverId()),
                response.getLatitude(),
                response.getLongitude(),
                Instant.parse(response.getTimestamp())
        );
    }
}
