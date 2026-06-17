package io.github.mshivaeifar.driverservice.infrastructure.grpc.client;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocation;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;
import io.github.mshivaeifar.driverservice.application.port.out.TelemetryClient;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationRequest;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.grpc.TelemetryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class TelemetryGrpcClient implements TelemetryClient {

    private final TelemetryServiceGrpc.TelemetryServiceBlockingStub stub;

    public TelemetryGrpcClient() {

        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress("localhost", 9090)
                        .usePlaintext()
                        .build();

        this.stub =
                TelemetryServiceGrpc
                        .newBlockingStub(channel);
    }


    @Override
    public DriverLocation getCurrentLocation(FetchByUUIDRequest driverId) {
        GetDriverLocationRequest request =
                GetDriverLocationRequest
                        .newBuilder()
                        .setDriverId(driverId.toString())
                        .build();

        GetDriverLocationResponse response =
                stub.getDriverLocation(request);

        return new DriverLocation(
                UUID.fromString(response.getDriverId()),
                response.getLatitude(),
                response.getLongitude(),
                Instant.parse(response.getTimestamp())
        );
    }
}
