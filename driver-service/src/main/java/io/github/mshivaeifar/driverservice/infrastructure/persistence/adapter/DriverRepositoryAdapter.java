package io.github.mshivaeifar.driverservice.infrastructure.persistence.adapter;

import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.DriverId;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.entity.DriverEntity;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.mapper.DriverPersistenceMapper;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.repository.SpringDataDriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DriverRepositoryAdapter implements DriverRepository {

    private final SpringDataDriverRepository driverRepository;

    @Override
    public Driver save(Driver driver) {
        DriverEntity driverEntity = DriverPersistenceMapper.toEntity(driver);

        DriverEntity savedDriver = driverRepository.save(driverEntity);

        return DriverPersistenceMapper.toDomain(savedDriver);
    }

    @Override
    public Optional<Driver> findById(DriverId id) {
        return driverRepository.findById(id.value()).map(DriverPersistenceMapper::toDomain);
    }

    @Override
    public List<Driver> findAll() {
        List<DriverEntity> driverEntities = driverRepository.findAll();
        return driverEntities.stream().map(
                DriverPersistenceMapper::toDomain
        ).toList();
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return driverRepository.existsByPhoneNumber(phoneNumber.value());
    }
}
