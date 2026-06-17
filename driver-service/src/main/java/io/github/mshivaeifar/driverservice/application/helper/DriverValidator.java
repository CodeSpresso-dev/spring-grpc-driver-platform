package io.github.mshivaeifar.driverservice.application.helper;

import io.github.mshivaeifar.driverservice.application.exception.DriverNotFoundException;
import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DriverValidator {
    private final DriverRepository repository;

    public void ensureExists(UUID driverId) {
        repository.findById(driverId)
                .orElseThrow(DriverNotFoundException::new);
    }

}
