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
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "reservation_TABLE")
public class ReservationDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private boolean isEnabled;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDBO user;

    @OneToMany(mappedBy = "reservation")
    private List<TicketDBO> tickets;

    public static ReservationDBO fromDomain(ReservationDomain domain){
        return new ReservationDBO(
                domain.getId(),
                domain.getDate(),
                domain.isEnabled(),
                domain.getUserDomain() != null ? new UserDBO(
                        domain.getUserDomain().getId(),
                        domain.getUserDomain().getUsername(),
                        domain.getUserDomain().getEmail(),
                        domain.getUserDomain().getPassword(),
                        null
                ) : null,
                domain.getTickets() != null ? domain.getTickets().stream()
                        .map(ticketDomain -> new TicketDBO(
                                ticketDomain.getId(),
                                ticketDomain.getDate(),
                                ticketDomain.getFlight() != null ? new FlightDBO(
                                        ticketDomain.getFlight().getId(),
                                        ticketDomain.getFlight().getName(),
                                        ticketDomain.getFlight().getOriginCity(),
                                        ticketDomain.getFlight().getDestinyCity(),
                                        ticketDomain.getFlight().getDate(),
                                        ticketDomain.getFlight().getPlaneName(),
                                        ticketDomain.getFlight().getCantSeats(),
                                        ticketDomain.getFlight().isFull(),
                                        null
                                ) : null,
                                ticketDomain.getReservation() != null ? new ReservationDBO(
                                        ticketDomain.getReservation().getId(),
                                        ticketDomain.getReservation().getDate(),
                                        ticketDomain.getReservation().isEnabled(),
                                        null,
                                        null
                                ) : null
                        )).toList() : new ArrayList<>()
        );
    }

    public ReservationDomain toDomain(){
        return new ReservationDomain(
                getId(),
                getDate(),
                isEnabled,
                getUser() != null ? new UserDomain(
                        getUser().getId(),
                        getUser().getUsername(),
                        getUser().getEmail(),
                        getUser().getPassword(),
                        getUser().getReservation() != null ? getUser().getReservation().stream()
                                .map(reservationDBO -> new ReservationDomain(
                                        reservationDBO.getId(),
                                        reservationDBO.getDate(),
                                        reservationDBO.isEnabled,
                                        null,
                                        reservationDBO.toDomain().getTickets()
                                )).toList() : new ArrayList<>()
                ) : null,
                getTickets() != null ? getTickets().stream()
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
                                null
                        )).toList() : new ArrayList<>()
        );
    }
}
