package io.github.mshivaeifar.driverservice.integration.rest;

import io.github.mshivaeifar.driverservice.application.service.RegisterDriverService;
import io.github.mshivaeifar.driverservice.integration.AbstractPostgresIT;
import io.github.mshivaeifar.driverservice.presentation.request.RegisterDriverRequestBody;
import io.github.mshivaeifar.driverservice.presentation.response.DriverLocationResponseBody;
import io.github.mshivaeifar.driverservice.presentation.response.DriverResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DriverControllerIT extends AbstractPostgresIT {

    @LocalServerPort
    int port;

    @Autowired
    RegisterDriverService registerDriverService;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test
    void given_valid_driver_request_when_register_invoked_should_create_driver_successfully() {

        //Given
        RegisterDriverRequestBody registerDriverRequestBody = new RegisterDriverRequestBuilder().buildValidRequest();

        //When
        EntityExchangeResult<DriverResponseBody> result = webTestClient.post()
                .uri("/api/v1/drivers")
                .bodyValue(registerDriverRequestBody)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(DriverResponseBody.class)
                .returnResult();
        DriverResponseBody response = result.getResponseBody();

        //Then
        assertThat(response).isNotNull();
        assertThat(response.id()).isNotNull();
        assertThat(response.phoneNumber())
                .isEqualTo(registerDriverRequestBody.phoneNumber());
    }

}
