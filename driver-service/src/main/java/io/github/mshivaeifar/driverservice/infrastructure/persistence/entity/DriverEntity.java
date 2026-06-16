package io.github.mshivaeifar.driverservice.infrastructure.persistence.entity;

import io.github.mshivaeifar.driverservice.domain.model.DriverStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(
        name = "drivers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_driver_phone",
                        columnNames = "phone_number"
                )
        }
)
public class DriverEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(
            nullable = false,
            unique = true
    )
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    protected DriverEntity() {
    }
}
