package com.ias;

public class NonFlightAvailableException extends RuntimeException {
    public NonFlightAvailableException(String message) {
        super(message);
    }
}
