package io.github.mshivaeifar.driverservice.integration.persistence.adapter;

import io.github.mshivaeifar.driverservice.application.port.out.DriverRepository;
import io.github.mshivaeifar.driverservice.domain.model.Driver;
import io.github.mshivaeifar.driverservice.integration.AbstractPostgresIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@ActiveProfiles("test")
public class DriverRepositoryAdapterIT extends AbstractPostgresIT {
    @Autowired
    private DriverRepository repository;

    @Test
    void given_valid_driver_when_saved_then_it_is_persisted_with_id() {

        //Given
        Driver driver = new DriverBuilder().build();

        //When
        Driver savedDriver = repository.save(driver);

        //Then
        then(savedDriver.getId()).isNotNull();
        then(savedDriver.getPhoneNumber().value()).isEqualTo(driver.getPhoneNumber().value());
    }

    @Test
    void given_valid_driver_when_fetched_by_id_should_find_it_successfully() {

        //Given
        Driver driver = new DriverBuilder().build();
        Driver savedDriver = repository.save(driver);

        //When
        Driver fetchedDriver = repository.findById(savedDriver.getId()).orElse(null);

        //Then
        then(fetchedDriver).isNotNull();
        then(fetchedDriver.getId()).isEqualTo(savedDriver.getId());
        then(fetchedDriver.getPhoneNumber().value()).isEqualTo(savedDriver.getPhoneNumber().value());
//        then(fetchedDriver)
//                .usingRecursiveComparison()
//                .isEqualTo(savedDriver);
    }

    @Test
    void given_valid_driver_when_fetched_by_phone_number_should_find_it_successfully() {

        //Given
        Driver driver = new DriverBuilder().build();
        Driver savedDriver = repository.save(driver);

        //When
        Boolean isExists = repository.existsByPhoneNumber(savedDriver.getPhoneNumber());

        //Then
        then(isExists).isTrue();
    }
}
