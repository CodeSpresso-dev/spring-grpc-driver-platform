package io.github.mshivaeifar.driverservice.presentation.rest;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocationResponse;
import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.port.in.GetDriverLocationUseCase;
import io.github.mshivaeifar.driverservice.application.port.in.RegisterDriverUseCase;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;
import io.github.mshivaeifar.driverservice.presentation.mapper.DriverLocationRestMapper;
import io.github.mshivaeifar.driverservice.presentation.mapper.DriverRestMapper;
import io.github.mshivaeifar.driverservice.presentation.request.RegisterDriverRequestBody;
import io.github.mshivaeifar.driverservice.presentation.response.DriverLocationResponseBody;
import io.github.mshivaeifar.driverservice.presentation.response.DriverResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/drivers")
@AllArgsConstructor
public class DriverController {
    private final RegisterDriverUseCase registerDriverUseCase;
    private final GetDriverLocationUseCase locationUseCase;

    @PostMapping
    public ResponseEntity<DriverResponseBody> create(
            @RequestBody RegisterDriverRequestBody requestBody
    ) {
        DriverResponse driverResponse = registerDriverUseCase.registerDriver(
                DriverRestMapper.toCommand(requestBody)
        );
        return ResponseEntity.created(
                        buildURI(driverResponse.id().toString()))
                .body(DriverRestMapper.toResponse(driverResponse));
    }



    private URI buildURI(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    private DriverId getDriverId(UUID driverId) {
        return DriverId.of(driverId);
    }
}
