package com.ias.reservation;

import com.ias.ReservationDomain;
import com.ias.ReservationService;
import com.ias.gateway.reservation.ReservationRepositoryFindByIdGateway;
import com.ias.gateway.reservation.ReservationRepositoryUpdateGateway;

public class ReservationUseCaseUpdateImpl {

    private final ReservationRepositoryUpdateGateway reservationRepositoryUpdateGateway;
    private final ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway;
    private final ReservationService reservationService;

    public ReservationUseCaseUpdateImpl(ReservationRepositoryUpdateGateway reservationRepositoryUpdateGateway, ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway, ReservationService reservationService) {
        this.reservationRepositoryUpdateGateway = reservationRepositoryUpdateGateway;
        this.reservationRepositoryFindByIdGateway = reservationRepositoryFindByIdGateway;
        this.reservationService = reservationService;
    }

    public ReservationDomain cancelReservation(Long reservationId){
        ReservationDomain reservationFounded = reservationRepositoryFindByIdGateway.findById(reservationId);
        reservationService.reservationIsEnabled(reservationFounded);
        reservationFounded.setEnabled(false);
        return reservationRepositoryUpdateGateway.update(reservationId, reservationFounded);
    }
}
