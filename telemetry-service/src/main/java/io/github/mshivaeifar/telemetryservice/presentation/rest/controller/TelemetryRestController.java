package io.github.mshivaeifar.telemetryservice.presentation.rest.controller;

import io.github.mshivaeifar.telemetryservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.telemetryservice.application.port.in.GetDriverCurrentLocationUseCase;
import io.github.mshivaeifar.telemetryservice.presentation.rest.dto.DriverLocationRestResponse;
import io.github.mshivaeifar.telemetryservice.presentation.rest.mapper.TelemetryRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/drivers", produces = "application/json")
@RequiredArgsConstructor
public class TelemetryRestController {
    private final GetDriverCurrentLocationUseCase locationService;
    private final TelemetryRestMapper mapper;

    @GetMapping("/{driverId}/location")
    public ResponseEntity<DriverLocationRestResponse> getDriverLocation(
            @PathVariable("driverId") UUID driverId
    ) {

        DriverLocationResponse driverLocationResponse = locationService.handle(driverId);

        DriverLocationRestResponse response =
                mapper.toResponse(driverLocationResponse);

        return ResponseEntity.ok(response);
    }
}
