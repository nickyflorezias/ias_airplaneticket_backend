package com.ias;

public class ReservationDisabledException extends RuntimeException{
    public ReservationDisabledException(String message){
        super(message);
    }
}
