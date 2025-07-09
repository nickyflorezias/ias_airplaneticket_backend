package com.ias;

public class ReservationAlreadyCanceledException extends RuntimeException{
    public  ReservationAlreadyCanceledException(String message){
        super(message);
    }
}
