package io.github.mshivaeifar.driverservice.unit.Infrastructure.grpc;

import io.github.mshivaeifar.driverservice.domain.model.DriverLocation;
import io.github.mshivaeifar.driverservice.infrastructure.grpc.client.TelemetryGrpcDriverLocationProvider;
import io.github.mshivaeifar.driverservice.infrastructure.grpc.mapper.TelemetryGrpcClientMapper;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationRequest;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.grpc.TelemetryServiceGrpc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelemetryGrpcDriverLocationProviderTest {

    @Mock
    TelemetryServiceGrpc.TelemetryServiceBlockingStub stub;

    @Mock
    TelemetryGrpcClientMapper mapper;

    @InjectMocks
    TelemetryGrpcDriverLocationProvider driverLocationProvider;

    @Test
    public void given_valid_driver_id_when_fetch_driver_location_should_return_driver_location_successfully() {
        //Given
        UUID driverId = UUID.randomUUID();

        GetDriverLocationRequest request =
                GetDriverLocationRequest
                        .newBuilder()
                        .setDriverId(driverId.toString())
                        .build();

        GetDriverLocationResponse response =
                GetDriverLocationResponse
                        .newBuilder()
                        .setDriverId(driverId.toString())
                        .setLatitude(20.0)
                        .setLongitude(50.0)
                        .setTimestamp(Instant.now().toString())
                        .build();

        DriverLocation mapped = new DriverLocation(
                driverId,
                20.0,
                50.0,
                Instant.now()
        );

        when(stub.withDeadlineAfter(anyLong(), any())).thenReturn(stub);

        when(stub.getDriverLocation(request)).thenReturn(response);

        when(mapper.toDomain(response)).thenReturn(mapped);

        //When
        DriverLocation result = driverLocationProvider.getCurrentLocation(driverId);

        //Then
        then(result).isNotNull();
        then(driverId).isEqualTo(result.driverId());

    }

    @Test
    public void given_invalid_driver_id_when_invoke_fallback_should_return_default_location() {
        //Given
        UUID driverId = UUID.randomUUID();

        Exception ex = new RuntimeException("gRPC failed");

        //When
        DriverLocation result = driverLocationProvider.telemetryGrpcFallback(driverId, ex);

        //Then
        then(result).isNotNull();
        then(driverId).isEqualTo(result.driverId());
        then(result.latitude()).isEqualTo(0.0);
    }
}
