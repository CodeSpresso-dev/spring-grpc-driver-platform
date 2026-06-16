package io.github.mshivaeifar.driverservice.infrastructure.persistence.repository;

import io.github.mshivaeifar.driverservice.infrastructure.persistence.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataDriverRepository extends JpaRepository<DriverEntity, UUID> {

    boolean existsByPhoneNumber(String phoneNumber);

}
