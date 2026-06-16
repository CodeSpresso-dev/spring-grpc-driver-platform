package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.mapper.DriverMapper;
import io.github.mshivaeifar.driverservice.application.port.in.ListDriversUseCase;
import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListDriversService implements ListDriversUseCase {
    private final DriverRepository driverRepository;

    public ListDriversService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<DriverResponse> fetchDrivers() {
        List<Driver> drivers = driverRepository.findAll();

        return drivers.stream().map(
                DriverMapper::toResponse
        ).toList();
    }
}
