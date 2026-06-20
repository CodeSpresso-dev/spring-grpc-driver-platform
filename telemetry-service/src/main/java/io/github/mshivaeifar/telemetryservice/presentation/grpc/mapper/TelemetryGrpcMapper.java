package io.github.mshivaeifar.telemetryservice.presentation.grpc.mapper;

import io.github.mshivaeifar.telemetryservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.grpc.GetDriverLocationResponse;
import org.springframework.stereotype.Component;

@Component
public class TelemetryGrpcMapper {

    public GetDriverLocationResponse toResponse(
            DriverLocationResponse location
    ) {

        return GetDriverLocationResponse
                .newBuilder()
                .setDriverId(
                        location.driverId().toString()
                )
                .setLatitude(
                        location.latitude()
                )
                .setLongitude(
                        location.longitude()
                )
                .setTimestamp(
                        location.timestamp().toString()
                )
                .build();
    }
}
