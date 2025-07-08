package com.ias;

import com.ias.gateway.ReservationRepositoryGateway;

import java.util.List;

public class ReservationUseCase {

    private final ReservationRepositoryGateway reservationRepositoryGateway;

    public ReservationUseCase(ReservationRepositoryGateway reservationRepositoryGateway) {
        this.reservationRepositoryGateway = reservationRepositoryGateway;
    }

    public List<ReservationDomain> getAllReservationsByUserId(Long userId){
        return reservationRepositoryGateway.findAllByUserId(userId);
    }

    public ReservationDomain getById(Long reservationId){
        return reservationRepositoryGateway.findById(reservationId);
    }

    public ReservationDomain createReservation(ReservationDomain reservationDomain){
        if(reservationDomain.isEnabled()){
            throw new ReservationDisabledException("Reservation can't be re activate.");
        }
        return reservationRepositoryGateway.save(reservationDomain);
    }
}
