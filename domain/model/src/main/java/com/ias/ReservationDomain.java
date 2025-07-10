package com.ias;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDomain {
    private Long id;
    private LocalDateTime date;
    private boolean isEnabled;
    private String description;
    private UserDomain userDomain;
    private TicketDomain ticketDomains;

    public ReservationDomain() {
    }

    public ReservationDomain(Long id, LocalDateTime date, boolean isEnabled, String description, UserDomain userDomain, TicketDomain ticketDomains) {
        this.id = id;
        this.date = date;
        this.isEnabled = isEnabled;
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

    public boolean isEnabled() {
        return isEnabled;
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

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserDomain(UserDomain userDomain) {
        this.userDomain = userDomain;
    }

    public void setTicketDomains(TicketDomain ticketDomains) {
        this.ticketDomains = ticketDomains;
    }

    @Override
    public String toString() {
        return "ReservationDomain{" +
                "id=" + id +
                ", date=" + date +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
