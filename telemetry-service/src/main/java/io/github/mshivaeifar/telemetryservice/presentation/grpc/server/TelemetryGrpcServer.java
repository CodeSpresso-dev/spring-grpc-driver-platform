package io.github.mshivaeifar.telemetryservice.presentation.grpc.server;

import io.github.mshivaeifar.telemetryservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.application.port.in.GetDriverCurrentLocationUseCase;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationRequest;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.grpc.TelemetryServiceGrpc;
import io.github.mshivaeifar.telemetryservice.presentation.grpc.mapper.TelemetryGrpcMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class TelemetryGrpcServer extends TelemetryServiceGrpc.TelemetryServiceImplBase {
    private final GetDriverCurrentLocationUseCase locationService;
    private final TelemetryGrpcMapper mapper;

    @Override
    public void getDriverLocation(
            GetDriverLocationRequest request,
            StreamObserver<GetDriverLocationResponse> responseObserver
    ) {

        UUID driverId = UUID.fromString(request.getDriverId());

        DriverLocationResponse driverLocationResponse = locationService.handle(driverId);

        GetDriverLocationResponse response =
                mapper.toResponse(driverLocationResponse);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
