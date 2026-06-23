package io.github.mshivaeifar.telemetryservice.presentation.rest.mapper;

import io.github.mshivaeifar.telemetryservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.presentation.rest.dto.DriverLocationRestResponse;
import org.springframework.stereotype.Component;

@Component
public class TelemetryRestMapper {

    public DriverLocationRestResponse toResponse(
            DriverLocationResponse location
    ) {

        return new DriverLocationRestResponse(
                location.driverId().toString(),
                location.latitude(),
                location.longitude(),
                location.timestamp().toString()
        );
    }
}
