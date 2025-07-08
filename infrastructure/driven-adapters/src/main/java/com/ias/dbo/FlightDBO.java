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
                                ticketDomain.getDate(),
                                null,
                                ticketDomain.getReservation() != null ? new ReservationDBO(
                                        ticketDomain.getReservation().getId(),
                                        ticketDomain.getReservation().getDate(),
                                        ticketDomain.getReservation().isEnabled(),
                                        ticketDomain.getReservation().getUserDomain() != null ? new UserDBO(
                                                ticketDomain.getReservation().getUserDomain().getId(),
                                                ticketDomain.getReservation().getUserDomain().getUsername(),
                                                ticketDomain.getReservation().getUserDomain().getEmail(),
                                                ticketDomain.getReservation().getUserDomain().getPassword(),
                                                null
                                        ) : null,
                                        null
                                ): null
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
                                ticketDBO.getDate(),
                                null,
                                ticketDBO.getReservation() != null ? new ReservationDomain(
                                        ticketDBO.getReservation().getId(),
                                        ticketDBO.getReservation().getDate(),
                                        ticketDBO.getReservation().isEnabled(),
                                        ticketDBO.getReservation().getUser() != null ? new UserDomain(
                                                ticketDBO.getReservation().getUser().getId(),
                                                ticketDBO.getReservation().getUser().getUsername(),
                                                ticketDBO.getReservation().getUser().getEmail(),
                                                ticketDBO.getReservation().getUser().getPassword(),
                                                null
                                        ) : null,
                                        null
                                ) : null
                        )).toList() : new ArrayList<>()
        );
    }
}
