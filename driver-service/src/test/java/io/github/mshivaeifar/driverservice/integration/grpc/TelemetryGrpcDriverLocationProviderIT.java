package io.github.mshivaeifar.driverservice.integration.grpc;

import io.github.mshivaeifar.driverservice.domain.model.DriverLocation;
import io.github.mshivaeifar.driverservice.infrastructure.grpc.client.TelemetryGrpcDriverLocationProvider;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationRequest;
import io.github.mshivaeifar.telemetryservice.grpc.TelemetryServiceGrpc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TelemetryGrpcDriverLocationProviderIT {

    @MockitoBean
    TelemetryServiceGrpc.TelemetryServiceBlockingStub stub;

    @Autowired
    TelemetryGrpcDriverLocationProvider driverLocationProvider;

    @Test
    public void given_invalid_driver_id_when_current_location_invoked_should_throw_exception_and_return_default_location() {
        //Given
        UUID driverId = UUID.randomUUID();

        Exception ex = new RuntimeException("gRPC failed");

        when(stub.withDeadlineAfter(anyLong(), any())).thenReturn(stub);

        when(stub.getDriverLocation(any(GetDriverLocationRequest.class)))
                .thenThrow(ex);

        //When
        DriverLocation result = driverLocationProvider.getCurrentLocation(driverId);

        //Then
        then(result).isNotNull();
        then(driverId).isEqualTo(result.driverId());
        then(result.latitude()).isEqualTo(0.0);
    }
}
