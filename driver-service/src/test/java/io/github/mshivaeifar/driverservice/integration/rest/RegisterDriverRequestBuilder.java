package io.github.mshivaeifar.driverservice.integration.rest;

import io.github.mshivaeifar.driverservice.presentation.request.RegisterDriverRequestBody;

public class RegisterDriverRequestBuilder {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public RegisterDriverRequestBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterDriverRequestBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterDriverRequestBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public RegisterDriverRequestBody buildValidRequest() {
        firstName = "John";
        lastName = "Doe";
        phoneNumber = "+989123456789";
        return new RegisterDriverRequestBody(
                firstName,
                lastName,
                phoneNumber);
    }

    public RegisterDriverRequestBody build() {
        return new RegisterDriverRequestBody(
                firstName,
                lastName,
                phoneNumber);
    }
}
