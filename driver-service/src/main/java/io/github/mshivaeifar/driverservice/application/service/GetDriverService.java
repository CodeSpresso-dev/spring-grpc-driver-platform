package io.github.mshivaeifar.driverservice.application.service;

import io.github.mshivaeifar.driverservice.application.dto.DriverResponse;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;
import io.github.mshivaeifar.driverservice.application.exception.DriverNotFoundException;
import io.github.mshivaeifar.driverservice.application.mapper.DriverMapper;
import io.github.mshivaeifar.driverservice.application.port.in.GetDriverUseCase;
import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import org.springframework.stereotype.Service;

@Service
public class GetDriverService implements GetDriverUseCase {

    private final DriverRepository driverRepository;

    public GetDriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverResponse fetchDriver(FetchByUUIDRequest request) {
        Driver driver = driverRepository.findById(request.driverId()).orElseThrow(
                DriverNotFoundException::new
        );
        return DriverMapper.toResponse(driver);
    }
}
