package com.ias.dbo;

import com.ias.*;
import com.ias.enums.AirlineName;
import com.ias.enums.FlightStatus;
import com.ias.enums.FlightType;
import com.ias.enums.PlaneName;
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

    @Enumerated(value = EnumType.STRING)
    private PlaneName planeName;

    @Enumerated(value = EnumType.STRING)
    private FlightType flightType;

    @Enumerated(value = EnumType.STRING)
    private AirlineName airlineName;
    private int cantSeats;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

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
                domain.getType(),
                domain.getAirlineName(),
                domain.getCantSeats(),
                domain.getStatus(),
                domain.getTickets() != null ? domain.getTickets().stream()
                        .map(ticketDomain -> new TicketDBO(
                                ticketDomain.getId(),
                                ticketDomain.getSeat(),
                                ticketDomain.getSeatClass(),
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
                                                reservationDomain.getStatus(),
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
                getFlightType(),
                getAirlineName(),
                getCantSeats(),
                getStatus(),
                getTicketDomains() != null ? getTicketDomains().stream()
                        .map(ticketDBO -> new TicketDomain(
                                ticketDBO.getId(),
                                ticketDBO.getSeat(),
                                ticketDBO.getSeatClass(),
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
                                                reservationDBO.getStatus(),
                                                reservationDBO.getDescription(),
                                                null,
                                                null
                                                )).toList() : null
                                )
                        ).toList() : null
        );
    }
}
