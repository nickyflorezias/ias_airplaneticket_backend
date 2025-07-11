package com.ias;

public class ReservationService {

    public void reservationIsEnabled(ReservationDomain reservationDomain){
        if(!reservationDomain.isEnabled()){
            throw new ReservationAlreadyCanceledException("The reservation is already canceled, can't be re open");
        }
    }
}
