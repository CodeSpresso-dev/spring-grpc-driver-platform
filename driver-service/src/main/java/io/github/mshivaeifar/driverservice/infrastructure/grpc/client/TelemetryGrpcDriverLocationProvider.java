package io.github.mshivaeifar.driverservice.infrastructure.grpc.client;

import io.github.mshivaeifar.driverservice.application.port.out.DriverLocationProvider;
import io.github.mshivaeifar.driverservice.domain.model.DriverLocation;
import io.github.mshivaeifar.driverservice.infrastructure.grpc.mapper.TelemetryGrpcClientMapper;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationRequest;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.grpc.TelemetryServiceGrpc;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class TelemetryGrpcDriverLocationProvider implements DriverLocationProvider {

    private final TelemetryServiceGrpc.TelemetryServiceBlockingStub stub;
    private final TelemetryGrpcClientMapper mapper;

    @Override
    public DriverLocation getCurrentLocation(UUID driverId) {
        GetDriverLocationRequest request =
                GetDriverLocationRequest
                        .newBuilder()
                        .setDriverId(driverId.toString())
                        .build();

        GetDriverLocationResponse response =
                stub
                        .withDeadlineAfter(2, TimeUnit.SECONDS)
                        .getDriverLocation(request);

        return mapper.toDomain(response);
    }
}
