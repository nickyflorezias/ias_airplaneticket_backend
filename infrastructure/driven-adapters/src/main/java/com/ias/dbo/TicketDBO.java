package com.ias.dbo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
}
