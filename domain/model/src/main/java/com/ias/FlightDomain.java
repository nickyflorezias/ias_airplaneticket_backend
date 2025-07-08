package com.ias;

import java.time.LocalDateTime;
import java.util.List;

public class FlightDomain {
    private Long id;
    private String name;
    private String originCity;
    private String destinyCity;
    private LocalDateTime date;
    private String planeName;
    private int cantSeats;
    private boolean isFull;

    private List<TicketDomain> ticketDomains;

    public FlightDomain() {
    }

    public FlightDomain(Long id, String name, String originCity, String destinyCity, LocalDateTime date, String planeName, int cantSeats, boolean isFull, List<TicketDomain> ticketDomains) {
        this.id = id;
        this.name = name;
        this.originCity = originCity;
        this.destinyCity = destinyCity;
        this.date = date;
        this.planeName = planeName;
        this.cantSeats = cantSeats;
        this.isFull = isFull;
        this.ticketDomains = ticketDomains;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinyCity() {
        return destinyCity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getPlaneName() {
        return planeName;
    }

    public int getCantSeats() {
        return cantSeats;
    }

    public boolean isFull() {
        return isFull;
    }

    public List<TicketDomain> getTickets() {
        return ticketDomains;
    }
}
