package com.ias;

import com.ias.gateway.flight.FlightRepositoryFindGateway;
import com.ias.gateway.reservation.ReservationRepositoryFindGateway;
import com.ias.gateway.reservation.ReservationRepositorySaveGateway;
import com.ias.gateway.reservation.ReservationRepositoryUpdateDateGateway;
import com.ias.gateway.reservation.ReservationRepositoryUpdateGateway;
import com.ias.gateway.ticket.TicketRepositoryFindGateway;
import com.ias.gateway.ticket.TicketRepositorySaveGateway;
import com.ias.gateway.user.UserRepositoryFindGateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class ReservationUseCase {

    Logger logger = Logger.getLogger(ReservationUseCase.class.getName());

    private final ReservationRepositoryFindGateway reservationRepositoryFindGateway;
    private final ReservationRepositorySaveGateway reservationRepositorySaveGateway;
    private final ReservationRepositoryUpdateGateway reservationRepositoryUpdateGateway;
    private final ReservationRepositoryUpdateDateGateway reservationRepositoryUpdateDateGateway;

    private final UserRepositoryFindGateway userRepositoryFindGateway;

    private final FlightRepositoryFindGateway flightRepositoryFindGateway;

    private final TicketRepositoryFindGateway ticketRepositoryFindGateway;
    private final TicketRepositorySaveGateway ticketRepositorySaveGateway;

    public ReservationUseCase(ReservationRepositoryFindGateway reservationRepositoryFindGateway,
                              ReservationRepositorySaveGateway reservationRepositorySaveGateway,
                              ReservationRepositoryUpdateGateway reservationRepositoryUpdateGateway,
                              ReservationRepositoryUpdateDateGateway reservationRepositoryUpdateDateGateway,
                              UserRepositoryFindGateway userRepositoryFindGateway,
                              FlightRepositoryFindGateway flightRepositoryFindGateway,
                              TicketRepositoryFindGateway ticketRepositoryFindGateway,
                              TicketRepositorySaveGateway ticketRepositorySaveGateway) {
        this.reservationRepositoryFindGateway = reservationRepositoryFindGateway;
        this.reservationRepositorySaveGateway = reservationRepositorySaveGateway;
        this.reservationRepositoryUpdateGateway = reservationRepositoryUpdateGateway;
        this.reservationRepositoryUpdateDateGateway = reservationRepositoryUpdateDateGateway;
        this.userRepositoryFindGateway = userRepositoryFindGateway;
        this.flightRepositoryFindGateway = flightRepositoryFindGateway;
        this.ticketRepositoryFindGateway = ticketRepositoryFindGateway;
        this.ticketRepositorySaveGateway = ticketRepositorySaveGateway;
    }

    public ReservationDomain createReservation(Long userId, ReservationDomain reservationDomain, Long ticketId){
        UserDomain userFounded = findUserById(userId);
        TicketDomain ticketFounded = findTicketById(ticketId);

        setUserToReservation(reservationDomain, userFounded);
        setTicketToReservation(reservationDomain, ticketFounded);
        logger.info("Create reservation with " + reservationDomain);
        return reservationRepositorySaveGateway.save(userId, reservationDomain, ticketId);
    }

    public ReservationDomain cancelReservation(Long reservationId){
        ReservationDomain reservationFounded = getById(reservationId);
        reservationIsEnabled(reservationFounded);
        reservationFounded.setEnabled(false);
        return reservationRepositoryUpdateGateway.update(reservationId, reservationFounded);
    }

    public ReservationDomain updateDate(Long reservationId, LocalDateTime newDate){
        ReservationDomain reservationFounded = getById(reservationId);

        reservationIsEnabled(reservationFounded);

        List<FlightDomain> flights = flightRepositoryFindGateway.findAllByDate(newDate);

        if(flights.isEmpty()){
            throw new NonFlightAvailableException("No flights available to date " + newDate);
        }else{
            reservationFounded.setDate(newDate);
            reservationFounded.getTicket().setFlightDomain(flights.stream().findFirst().get());
            ticketRepositorySaveGateway.save(reservationFounded.getTicket().getFlight().getId(), reservationFounded.getTicket());
        }
        return reservationRepositoryUpdateDateGateway.updateDate(reservationId, reservationFounded, newDate);
    }

    public UserDomain findUserById(Long userId){
        return userRepositoryFindGateway.findById(userId);
    }

    public TicketDomain findTicketById(Long ticketId){
        return ticketRepositoryFindGateway.findById(ticketId);
    }

    public List<ReservationDomain> getAllReservationsByUserId(Long userId){
        logger.info("Get all reservations by user id");
        return reservationRepositoryFindGateway.findAllByUserId(userId);
    }

    public ReservationDomain getById(Long reservationId){
        logger.info("Get all reservations by reservation id");
        return reservationRepositoryFindGateway.findById(reservationId);
    }

    private void setUserToReservation(ReservationDomain reservationDomain, UserDomain userDomain){
        reservationDomain.setUserDomain(userDomain);
    }

    private void setTicketToReservation(ReservationDomain reservationDomain, TicketDomain ticketDomain){
        reservationDomain.setTicketDomain(ticketDomain);
    }

    private void reservationIsEnabled(ReservationDomain reservationDomain){
        if(!reservationDomain.isEnabled()){
            throw new ReservationAlreadyCanceledException("The reservation is already canceled, can't be re open");
        }
    }
}
