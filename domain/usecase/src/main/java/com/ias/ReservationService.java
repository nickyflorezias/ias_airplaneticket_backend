package com.ias;

import com.ias.enums.ReservationStatus;

public class ReservationService {

    public void reservationStatusIsEnabled(ReservationDomain reservationDomain){
        if(reservationDomain.getStatus() == ReservationStatus.CANCELLED){
            throw new ReservationAlreadyCanceledException("The reservation is already canceled, can't be re open");
        }
    }
}
