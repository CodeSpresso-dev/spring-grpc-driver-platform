package io.github.mshivaeifar.driverservice.application.dto;

public record RegisterDriverCommand(
        String firstName,
        String lastName,
        String phoneNumber
) {
}
