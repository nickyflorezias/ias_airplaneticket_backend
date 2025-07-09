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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public void setDestinyCity(String destinyCity) {
        this.destinyCity = destinyCity;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public void setCantSeats(int cantSeats) {
        this.cantSeats = cantSeats;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public void setTicketDomains(List<TicketDomain> ticketDomains) {
        this.ticketDomains = ticketDomains;
    }

    @Override
    public String toString() {
        return "FlightDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originCity='" + originCity + '\'' +
                ", destinyCity='" + destinyCity + '\'' +
                ", date=" + date +
                ", planeName='" + planeName + '\'' +
                ", cantSeats=" + cantSeats +
                ", isFull=" + isFull +
                '}';
    }
}
