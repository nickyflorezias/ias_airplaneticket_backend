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

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "user_TABLE")
public class UserDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<ReservationDBO> reservation;

    public static UserDBO fromDomain(UserDomain domain){
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
    public UserDomain toDomain(){
        return new UserDomain(
                getId(),
                getUsername(),
                getEmail(),
                getPassword(),
                getReservation() != null ? getReservation().stream()
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
