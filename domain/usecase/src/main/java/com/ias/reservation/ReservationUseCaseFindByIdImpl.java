package com.ias.reservation;

import com.ias.ReservationDomain;
import com.ias.gateway.reservation.ReservationRepositoryFindByIdGateway;

public class ReservationUseCaseFindByIdImpl {

    private final ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway;

    public ReservationUseCaseFindByIdImpl(ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway) {
        this.reservationRepositoryFindByIdGateway = reservationRepositoryFindByIdGateway;
    }

    public ReservationDomain findById(Long reservationId){
        return reservationRepositoryFindByIdGateway.findById(reservationId);
    }
}
