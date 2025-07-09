package com.ias;

import com.ias.gateway.ReservationRepositoryGateway;
import com.ias.gateway.TicketRepositoryGateway;
import com.ias.gateway.UserRepositoryGateway;

import java.util.List;
import java.util.logging.Logger;

public class ReservationUseCase {

    Logger logger = Logger.getLogger(ReservationUseCase.class.getName());

    private final ReservationRepositoryGateway reservationRepositoryGateway;
    private final UserRepositoryGateway userRepositoryGateway;
    private final TicketRepositoryGateway ticketRepositoryGateway;

    public ReservationUseCase(ReservationRepositoryGateway reservationRepositoryGateway, UserRepositoryGateway userRepositoryGateway, TicketRepositoryGateway ticketRepositoryGateway) {
        this.reservationRepositoryGateway = reservationRepositoryGateway;
        this.userRepositoryGateway = userRepositoryGateway;
        this.ticketRepositoryGateway = ticketRepositoryGateway;
    }

    public List<ReservationDomain> getAllReservationsByUserId(Long userId){
        logger.info("Get all reservations by user id");
        return reservationRepositoryGateway.findAllByUserId(userId);
    }

    public ReservationDomain getById(Long reservationId){
        logger.info("Get all reservations by reservation id");
        return reservationRepositoryGateway.findById(reservationId);
    }

    public ReservationDomain createReservation(Long userId, ReservationDomain reservationDomain, Long ticketId){
        logger.info("Create reservation with " + reservationDomain.toString());
        return reservationRepositoryGateway.save(userId, reservationDomain, ticketId);
    }

    public ReservationDomain cancelReservation(Long reservationId){
        ReservationDomain reservationFounded = getById(reservationId);
        if(!reservationFounded.isEnabled()){
            throw new ReservationAlreadyCanceledException("The reservation is already canceled, can't be re open");
        }
        reservationFounded.setEnabled(false);
        return reservationRepositoryGateway.update(reservationId, reservationFounded);
    }
}
