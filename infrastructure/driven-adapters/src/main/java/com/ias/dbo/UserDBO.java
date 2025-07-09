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
    private List<TicketDBO> tickets;

    @OneToMany(mappedBy = "user")
    private List<ReservationDBO> reservation;

    public static UserDBO fromDomain(UserDomain domain){
        return new UserDBO(
                domain.getId(),
                domain.getUsername(),
                domain.getEmail(),
                domain.getPassword(),
                domain.getTickets() != null ? domain.getTickets().stream()
                                .map(ticketDomain -> new TicketDBO(
                                        ticketDomain.getId(),
                                        ticketDomain.getSeat(),
                                        null,
                                        null,
                                        null
                                )).toList() : null,
                domain.getReservations() != null ? domain.getReservations().stream()
                        .map(reservationDomain -> new ReservationDBO(
                                reservationDomain.getId(),
                                reservationDomain.getDate(),
                                reservationDomain.isEnabled(),
                                reservationDomain.getDescription(),
                                null,
                                reservationDomain.getTicket() != null ? new TicketDBO(
                                        reservationDomain.getTicket().getId(),
                                        reservationDomain.getTicket().getSeat(),
                                        null,
                                        null,
                                        null
                                ) : null
                        )).toList(): new ArrayList<>()
        );
    }
    public UserDomain toDomain(){
        return new UserDomain(
                getId(),
                getUsername(),
                getEmail(),
                getPassword(),
                getTickets() != null ? getTickets().stream()
                                .map(ticketDBO -> new TicketDomain(
                                        ticketDBO.getId(),
                                        ticketDBO.getSeat(),
                                        null,
                                        null,
                                        null
                                )).toList() : null,
                getReservation() != null ? getReservation().stream()
                        .map(reservationDBO -> new ReservationDomain(
                                reservationDBO.getId(),
                                reservationDBO.getDate(),
                                reservationDBO.isEnabled(),
                                reservationDBO.getDescription(),
                                null,
                                reservationDBO.getTicket() != null ? new TicketDomain(
                                        reservationDBO.getTicket().getId(),
                                        reservationDBO.getTicket().getSeat(),
                                        null,
                                        null,
                                        null
                                ) : null
                        )).toList(): new ArrayList<>()
        );
    }
}
