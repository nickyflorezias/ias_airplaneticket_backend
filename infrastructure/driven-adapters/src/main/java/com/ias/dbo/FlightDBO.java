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

import static java.util.stream.Collectors.toList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "flight_TABLE")
public class FlightDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String originCity;
    private String destinyCity;
    private LocalDateTime date;
    private String planeName;
    private int cantSeats;
    private boolean isFull;

    @OneToMany(mappedBy = "flight")
    private List<TicketDBO> ticketDomains;

    public static FlightDBO fromDomain(FlightDomain domain){
        return new FlightDBO(
                domain.getId(),
                domain.getName(),
                domain.getOriginCity(),
                domain.getDestinyCity(),
                domain.getDate(),
                domain.getPlaneName(),
                domain.getCantSeats(),
                domain.isFull(),
                domain.getTickets() != null ? domain.getTickets().stream()
                        .map(ticketDomain -> new TicketDBO(
                                ticketDomain.getId(),
                                ticketDomain.getSeat(),
                                null,
                                ticketDomain.getUser() != null ? new UserDBO(
                                        ticketDomain.getUser().getId(),
                                        ticketDomain.getUser().getUsername(),
                                        ticketDomain.getUser().getEmail(),
                                        ticketDomain.getUser().getPassword(),
                                        null,
                                        null
                                ) : null,
                                ticketDomain.getReservation() != null ? ticketDomain.getReservation().stream()
                                        .map(reservationDomain -> new ReservationDBO(
                                                reservationDomain.getId(),
                                                reservationDomain.getDate(),
                                                reservationDomain.isEnabled(),
                                                reservationDomain.getDescription(),
                                                null,
                                                null
                                        )).toList() : null
                        )).toList(): new ArrayList<>()
        );
    }

    public FlightDomain toDomain(){
        return new FlightDomain(
                getId(),
                getName(),
                getOriginCity(),
                getDestinyCity(),
                getDate(),
                getPlaneName(),
                getCantSeats(),
                isFull(),
                getTicketDomains() != null ? getTicketDomains().stream()
                        .map(ticketDBO -> new TicketDomain(
                                ticketDBO.getId(),
                                ticketDBO.getSeat(),
                                null,
                                ticketDBO.getUser() != null ? new UserDomain(
                                        ticketDBO.getUser().getId(),
                                        ticketDBO.getUser().getUsername(),
                                        ticketDBO.getUser().getEmail(),
                                        ticketDBO.getUser().getPassword(),
                                        null,
                                        null
                                ) : null,
                                ticketDBO.getReservation() != null ? ticketDBO.getReservation().stream()
                                        .map(reservationDBO -> new ReservationDomain(
                                                reservationDBO.getId(),
                                                reservationDBO.getDate(),
                                                reservationDBO.isEnabled(),
                                                reservationDBO.getDescription(),
                                                null,
                                                null
                                                )).toList() : null
                                )
                        ).toList() : null
        );
    }
}
