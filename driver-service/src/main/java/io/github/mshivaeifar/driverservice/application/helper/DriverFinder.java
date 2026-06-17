package io.github.mshivaeifar.driverservice.application.helper;

import io.github.mshivaeifar.driverservice.application.exception.DriverNotFoundException;
import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverFinder {
    private final DriverRepository repository;

    public void ensureExists(DriverId driverId) {
        findById(driverId);
    }

    public Driver findById(DriverId driverId) {
        return repository.findById(driverId)
                .orElseThrow(DriverNotFoundException::new);
    }
}
