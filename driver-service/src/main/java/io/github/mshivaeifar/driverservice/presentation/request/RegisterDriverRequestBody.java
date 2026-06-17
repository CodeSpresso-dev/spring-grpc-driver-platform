package io.github.mshivaeifar.driverservice.presentation.request;

public record RegisterDriverRequestBody(
        String firstName,
        String lastName,
        String phoneNumber
) {
}
