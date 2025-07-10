package com.ias;

import com.ias.gateway.FlightRepositoryGateway;
import com.ias.gateway.ReservationRepositoryGateway;
import com.ias.gateway.TicketRepositoryGateway;
import com.ias.gateway.UserRepositoryGateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class ReservationUseCase {

    Logger logger = Logger.getLogger(ReservationUseCase.class.getName());

    private final ReservationRepositoryGateway reservationRepositoryGateway;
    private final FlightRepositoryGateway flightRepositoryGateway;
    private final UserRepositoryGateway userRepositoryGateway;
    private final TicketRepositoryGateway ticketRepositoryGateway;

    public ReservationUseCase(ReservationRepositoryGateway reservationRepositoryGateway, FlightRepositoryGateway flightRepositoryGateway, UserRepositoryGateway userRepositoryGateway, TicketRepositoryGateway ticketRepositoryGateway) {
        this.reservationRepositoryGateway = reservationRepositoryGateway;
        this.flightRepositoryGateway = flightRepositoryGateway;
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
        UserDomain userFounded = userRepositoryGateway.findById(userId);
        TicketDomain ticket = ticketRepositoryGateway.findById(ticketId);

        reservationDomain.setUserDomain(userFounded);
        reservationDomain.setTicketDomain(ticket);
        logger.info("Create reservation with " + reservationDomain);
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

    public ReservationDomain updateDate(Long reservationId, LocalDateTime newDate){
        ReservationDomain reservationFounded = getById(reservationId);

        if(!reservationFounded.isEnabled()){
            throw new ReservationAlreadyCanceledException("The reservation is already canceled, can't be updated");
        }

        List<FlightDomain> flights = flightRepositoryGateway.findAllByDate(newDate);

        if(flights.isEmpty()){
            throw new NonFlightAvailableException("No flights available to date " + newDate);
        }else{
            reservationFounded.setDate(newDate);
            reservationFounded.getTicket().setFlightDomain(flights.stream().findFirst().get());
            ticketRepositoryGateway.save(reservationFounded.getTicket().getFlight().getId(), reservationFounded.getTicket());
        }
        return reservationRepositoryGateway.updateDate(reservationId, reservationFounded, newDate);
    }
}
