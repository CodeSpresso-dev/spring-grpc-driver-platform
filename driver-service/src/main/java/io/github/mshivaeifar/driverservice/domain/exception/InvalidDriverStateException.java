package io.github.mshivaeifar.driverservice.domain.exception;

public class InvalidDriverStateException extends RuntimeException {
    public InvalidDriverStateException(String message) {
        super(message);
    }
}
