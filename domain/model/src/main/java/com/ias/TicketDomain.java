package com.ias;

import com.ias.enums.SeatClass;

import java.util.List;

public class TicketDomain {
    private Long id;
    private String seat;
    private SeatClass seatClass;
    private FlightDomain flightDomain;
    private UserDomain user;
    private List<ReservationDomain> reservationDomain;

    public TicketDomain() {
    }

    public TicketDomain(Long id, String seat, SeatClass seatClass, FlightDomain flightDomain, UserDomain user, List<ReservationDomain> reservationDomain) {
        this.id = id;
        this.seat = seat;
        this.seatClass = seatClass;
        this.flightDomain = flightDomain;
        this.user = user;
        this.reservationDomain = reservationDomain;
    }

    public Long getId() {
        return id;
    }


    public FlightDomain getFlight() {
        return flightDomain;
    }

    public List<ReservationDomain> getReservation() {
        return reservationDomain;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlightDomain(FlightDomain flightDomain) {
        this.flightDomain = flightDomain;
    }

    public void setReservationDomain(List<ReservationDomain> reservationDomain) {
        this.reservationDomain = reservationDomain;
    }

    public String getSeat() {
        return seat;
    }

    public UserDomain getUser() {
        return user;
    }

    public void setUser(UserDomain user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TicketDomain{" +
                "id=" + id +
                ", seat='" + seat + '\'' +
                ", flightDomain=" + flightDomain +
                ", user=" + user +
                ", reservationDomain=" + reservationDomain +
                '}';
    }
}
