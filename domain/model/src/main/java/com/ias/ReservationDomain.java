package com.ias;

import com.ias.enums.ReservationStatus;

import java.time.LocalDateTime;

public class ReservationDomain {
    private Long id;
    private LocalDateTime date;
    private ReservationStatus status;
    private String description;
    private UserDomain userDomain;
    private TicketDomain ticketDomains;

    public ReservationDomain() {
    }

    public ReservationDomain(Long id, LocalDateTime date, ReservationStatus status, String description, UserDomain userDomain, TicketDomain ticketDomains) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.description = description;
        this.userDomain = userDomain;
        this.ticketDomains = ticketDomains;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public UserDomain getUserDomain() {
        return userDomain;
    }

    public TicketDomain getTicket() {
        return ticketDomains;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void setUserDomain(UserDomain userDomain) {
        this.userDomain = userDomain;
    }

    public void setTicketDomain(TicketDomain ticketDomains) {
        this.ticketDomains = ticketDomains;
    }

    @Override
    public String toString() {
        return "ReservationDomain{" +
                "id=" + id +
                ", date=" + date +
                ", isEnabled=" + status +
                '}';
    }
}
