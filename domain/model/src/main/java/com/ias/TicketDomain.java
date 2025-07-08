package com.ias;

import java.time.LocalDateTime;

public class TicketDomain {
    private Long id;
    private LocalDateTime date;
    private FlightDomain flightDomain;
    private ReservationDomain reservationDomain;

    public TicketDomain() {
    }

    public TicketDomain(Long id, LocalDateTime date, FlightDomain flightDomain, ReservationDomain reservationDomain) {
        this.id = id;
        this.date = date;
        this.flightDomain = flightDomain;
        this.reservationDomain = reservationDomain;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public FlightDomain getFlight() {
        return flightDomain;
    }

    public ReservationDomain getReservation() {
        return reservationDomain;
    }
}
