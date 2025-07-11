package com.ias.dbo;

import com.ias.*;
import com.ias.enums.SeatClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "seat")
    private String seat;

    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private FlightDBO flight;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDBO user;

    @OneToMany(mappedBy = "ticket")
    private List<ReservationDBO> reservation;

    public static TicketDBO fromDomain(TicketDomain domain){
        return new TicketDBO(
                domain.getId(),
                domain.getSeat(),
                domain.getSeatClass(),
                domain.getFlight() != null ? new FlightDBO(
                        domain.getFlight().getId(),
                        domain.getFlight().getName(),
                        domain.getFlight().getOriginCity(),
                        domain.getFlight().getDestinyCity(),
                        domain.getFlight().getDate(),
                        domain.getFlight().getPlaneName(),
                        domain.getFlight().getType(),
                        domain.getFlight().getAirlineName(),
                        domain.getFlight().getCantSeats(),
                        domain.getFlight().getStatus(),
                        domain.getFlight().getTickets() != null ? domain.getFlight().getTickets().stream()
                                .map(ticketDomain -> new TicketDBO(
                                        ticketDomain.getId(),
                                        ticketDomain.getSeat(),
                                        ticketDomain.getSeatClass(),
                                        null,
                                        null,
                                        null
                                )).toList() : new ArrayList<>()
                ) : null,
                domain.getUser() != null ? new UserDBO(
                        domain.getUser().getId(),
                        domain.getUser().getUsername(),
                        domain.getUser().getEmail(),
                        domain.getUser().getPassword(),
                        null,
                        null
                ) : null,
                domain.getReservation() != null ? domain.getReservation().stream()
                        .map(reservationDomain -> new ReservationDBO(
                                reservationDomain.getId(),
                                reservationDomain.getDate(),
                                reservationDomain.getStatus(),
                                reservationDomain.getDescription(),
                                null,
                                null
                        )).toList() : null
        );
    }

    public TicketDomain toDomain(){
        return new TicketDomain(
                getId(),
                getSeat(),
                getSeatClass(),
                getFlight() != null ? new FlightDomain(
                        getFlight().getId(),
                        getFlight().getName(),
                        getFlight().getOriginCity(),
                        getFlight().getDestinyCity(),
                        getFlight().getDate(),
                        getFlight().getPlaneName(),
                        getFlight().getFlightType(),
                        getFlight().getAirlineName(),
                        getFlight().getCantSeats(),
                        getFlight().getStatus(),
                        getFlight().getTicketDomains() != null ? getFlight().getTicketDomains().stream()
                                .map(ticketDBO -> new TicketDomain(
                                        ticketDBO.getId(),
                                        ticketDBO.getSeat(),
                                        ticketDBO.getSeatClass(),
                                        null,
                                        null,
                                        null
                                )).toList() : new ArrayList<>()
                ) : null,
                getUser() != null ? new UserDomain(
                  getUser().getId(),
                  getUser().getUsername(),
                        getUser().getEmail(),
                        getUser().getPassword(),
                        null,
                     null
                ) : null,
                getReservation() != null ? getReservation().stream()
                        .map(reservationDBO -> new ReservationDomain(
                                reservationDBO.getId(),
                                reservationDBO.getDate(),
                                reservationDBO.getStatus(),
                                reservationDBO.getDescription(),
                                null,
                                null
                        )).toList() : null
        );
    }
}
