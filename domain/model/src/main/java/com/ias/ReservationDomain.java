package com.ias;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDomain {
    private Long id;
    private LocalDateTime date;
    private boolean isEnabled;
    private UserDomain userDomain;
    private List<TicketDomain> ticketDomains;

    public ReservationDomain() {
    }

    public ReservationDomain(Long id, LocalDateTime date, boolean isEnabled, UserDomain userDomain, List<TicketDomain> ticketDomains) {
        this.id = id;
        this.date = date;
        this.isEnabled = isEnabled;
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

    public List<TicketDomain> getTickets() {
        return ticketDomains;
    }
}
