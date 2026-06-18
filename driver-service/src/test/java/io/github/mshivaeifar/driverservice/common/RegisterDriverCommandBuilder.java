package io.github.mshivaeifar.driverservice.common;

import io.github.mshivaeifar.driverservice.application.dto.RegisterDriverCommand;

public class RegisterDriverCommandBuilder {

    private String firstName ;
    private String lastName ;
    private String phoneNumber ;

    public RegisterDriverCommandBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterDriverCommandBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterDriverCommandBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public RegisterDriverCommand buildValidCommand() {
        firstName = "John";
        lastName = "Doe";
        phoneNumber = "+989123456789";
        return new RegisterDriverCommand(
                firstName,
                lastName,
                phoneNumber);
    }
    public RegisterDriverCommand build() {
        return new RegisterDriverCommand(
                firstName,
                lastName,
                phoneNumber);
    }
}
