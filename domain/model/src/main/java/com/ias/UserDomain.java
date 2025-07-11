package com.ias;

import java.util.List;

public class UserDomain {
    private Long id;
    private String username;
    private String email;
    private String password;

    private List<TicketDomain> tickets;

    private List<ReservationDomain> reservationDomains;

    public UserDomain() {
    }

    public UserDomain(Long id, String username, String email, String password, List<TicketDomain> tickets, List<ReservationDomain> reservationDomains) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.tickets = tickets;
        this.reservationDomains = reservationDomains;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<ReservationDomain> getReservations() {
        return reservationDomains;
    }

    public List<TicketDomain> getTickets() {
        return tickets;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDomain{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}