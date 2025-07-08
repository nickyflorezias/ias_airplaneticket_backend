package com.ias;

import com.ias.dbo.FlightDBO;
import com.ias.dbo.ReservationDBO;
import com.ias.dbo.TicketDBO;
import com.ias.dbo.UserDBO;

import java.util.ArrayList;

public class UserMapper {

    public UserDBO fromDomain(UserDomain domain){
        return new UserDBO(
                domain.getId(),
                domain.getUsername(),
                domain.getEmail(),
                domain.getPassword(),
                domain.getReservations() != null ? domain.getReservations().stream()
                        .map(reservationDomain -> new ReservationDBO(
                                reservationDomain.getId(),
                                reservationDomain.getDate(),
                                reservationDomain.isEnabled(),
                                null,
                                reservationDomain.getTickets() != null ? reservationDomain.getTickets().stream()
                                        .map(ticketDomain -> new TicketDBO(
                                                ticketDomain.getId(),
                                                ticketDomain.getDate(),
                                                ticketDomain.getFlight()  != null ? new FlightDBO(ticketDomain.getFlight().getId(),
                                                        ticketDomain.getFlight().getName(),
                                                        ticketDomain.getFlight().getOriginCity(),
                                                        ticketDomain.getFlight().getDestinyCity(),
                                                        ticketDomain.getFlight().getDate(),
                                                        ticketDomain.getFlight().getPlaneName(),
                                                        ticketDomain.getFlight().getCantSeats(),
                                                        ticketDomain.getFlight().isFull(),
                                                        null) : null,
                                                ticketDomain.getReservation() != null ? new ReservationDBO(
                                                        ticketDomain.getReservation().getId(),
                                                        ticketDomain.getReservation().getDate(),
                                                        ticketDomain.getReservation().isEnabled(),
                                                        null,
                                                        null
                                                ) : null
                                        )).toList(): new ArrayList<>()
                        )).toList(): new ArrayList<>()
        );
    }
    public UserDomain toDomain(UserDBO dbo){
        return new UserDomain(
                dbo.getId(),
                dbo.getUsername(),
                dbo.getEmail(),
                dbo.getPassword(),
                dbo.getReservation() != null ? dbo.getReservation().stream()
                        .map(reservationDBO -> new ReservationDomain(
                                reservationDBO.getId(),
                                reservationDBO.getDate(),
                                reservationDBO.isEnabled(),
                                null,
                                reservationDBO.getTickets() != null ? reservationDBO.getTickets().stream()
                                        .map(ticketDBO -> new TicketDomain(
                                                ticketDBO.getId(),
                                                ticketDBO.getDate(),
                                                ticketDBO.getFlight() != null ? new FlightDomain(
                                                        ticketDBO.getFlight().getId(),
                                                        ticketDBO.getFlight().getName(),
                                                        ticketDBO.getFlight().getOriginCity(),
                                                        ticketDBO.getFlight().getDestinyCity(),
                                                        ticketDBO.getFlight().getDate(),
                                                        ticketDBO.getFlight().getPlaneName(),
                                                        ticketDBO.getFlight().getCantSeats(),
                                                        ticketDBO.getFlight().isFull(),
                                                        null
                                                ) : null,
                                                ticketDBO.getReservation() != null ? new ReservationDomain(
                                                        ticketDBO.getReservation().getId(),
                                                        ticketDBO.getReservation().getDate(),
                                                        ticketDBO.getReservation().isEnabled(),
                                                        null,
                                                        null
                                                ) : null
                                        )).toList(): new ArrayList<>()
                        )).toList(): new ArrayList<>()
        );
    }
}
