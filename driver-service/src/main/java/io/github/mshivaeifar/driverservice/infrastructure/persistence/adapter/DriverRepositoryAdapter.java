package io.github.mshivaeifar.driverservice.infrastructure.persistence.adapter;

import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.domain.valueobject.PhoneNumber;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.entity.DriverEntity;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.mapper.DriverPersistenceMapper;
import io.github.mshivaeifar.driverservice.infrastructure.persistence.repository.SpringDataDriverRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DriverRepositoryAdapter implements DriverRepository {

    private final SpringDataDriverRepository driverRepository;

    public DriverRepositoryAdapter(SpringDataDriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver save(Driver driver) {
        DriverEntity driverEntity = DriverPersistenceMapper.toEntity(driver);

        DriverEntity savedDriver = driverRepository.save(driverEntity);

        return DriverPersistenceMapper.toDomain(savedDriver);
    }

    @Override
    public Optional<Driver> findById(UUID id) {
        return driverRepository.findById(id).map(DriverPersistenceMapper::toDomain);
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
