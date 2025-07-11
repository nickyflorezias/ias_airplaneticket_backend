package com.ias.reservation;

import com.ias.ReservationDomain;
import com.ias.gateway.reservation.ReservationRepositoryFindByUserIdGateway;

import java.util.List;

public class ReservationUseCaseFindByUserIdImpl {

    private final ReservationRepositoryFindByUserIdGateway reservationRepositoryFindByUserIdGateway;

    public ReservationUseCaseFindByUserIdImpl(ReservationRepositoryFindByUserIdGateway reservationRepositoryFindByUserIdGateway) {
        this.reservationRepositoryFindByUserIdGateway = reservationRepositoryFindByUserIdGateway;
    }

    public List<ReservationDomain> findByUserId(Long userId){
        return reservationRepositoryFindByUserIdGateway.findAllByUserId(userId);
    }
}
