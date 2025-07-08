package com.ias.dbo;

import com.ias.FlightDomain;
import com.ias.ReservationDomain;
import com.ias.TicketDomain;
import com.ias.UserDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "ticket_TABLE")
public class TicketDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private FlightDBO flight;

    @ManyToOne
    @JoinColumn(name = "reservationId")
    private ReservationDBO reservation;

    public static TicketDBO fromDomain(TicketDomain domain){
        return new TicketDBO(
                domain.getId(),
                domain.getDate(),
                domain.getFlight() != null ? new FlightDBO(
                        domain.getFlight().getId(),
                        domain.getFlight().getName(),
                        domain.getFlight().getOriginCity(),
                        domain.getFlight().getDestinyCity(),
                        domain.getFlight().getDate(),
                        domain.getFlight().getPlaneName(),
                        domain.getFlight().getCantSeats(),
                        domain.getFlight().isFull(),
                        domain.getFlight().getTickets() != null ? domain.getFlight().getTickets().stream()
                                .map(ticketDomain -> new TicketDBO(
                                        ticketDomain.getId(),
                                        ticketDomain.getDate(),
                                        null,
                                        null
                                )).toList() : new ArrayList<>()
                ) : null,
                domain.getReservation() != null ? new ReservationDBO(
                        domain.getReservation().getId(),
                        domain.getReservation().getDate(),
                        domain.getReservation().isEnabled(),
                        domain.getReservation().getUserDomain() != null ? new UserDBO(
                                domain.getReservation().getUserDomain().getId(),
                                domain.getReservation().getUserDomain().getUsername(),
                                domain.getReservation().getUserDomain().getEmail(),
                                domain.getReservation().getUserDomain().getPassword(),
                                null
                        ) : null,
                        null
                ) : null
        );
    }

    public TicketDomain toDomain(){
        return new TicketDomain(
                getId(),
                getDate(),
                getFlight() != null ? new FlightDomain(
                        getFlight().getId(),
                        getFlight().getName(),
                        getFlight().getOriginCity(),
                        getFlight().getDestinyCity(),
                        getFlight().getDate(),
                        getFlight().getPlaneName(),
                        getFlight().getCantSeats(),
                        getFlight().isFull(),
                        getFlight().getTicketDomains() != null ? getFlight().getTicketDomains().stream()
                                .map(ticketDBO -> new TicketDomain(
                                        ticketDBO.getId(),
                                        ticketDBO.getDate(),
                                        null,
                                        null
                                )).toList() : new ArrayList<>()
                ) : null,
                getReservation() != null ? new ReservationDomain(
                        getReservation().getId(),
                        getReservation().getDate(),
                        getReservation().isEnabled(),
                        getReservation().getUser() != null ? new UserDomain(
                                getReservation().getUser().getId(),
                                getReservation().getUser().getUsername(),
                                getReservation().getUser().getEmail(),
                                getReservation().getUser().getPassword(),
                                null
                        ) : null,
                        null
                ) : null
        );
    }
}
