package io.github.mshivaeifar.driverservice.application.dto;

public record RegisterDriverRequest(
        String firstName,
        String lastName,
        String phoneNumber
) {
}
