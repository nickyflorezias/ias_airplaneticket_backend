package com.ias;

import com.ias.enums.AirlineName;
import com.ias.enums.FlightType;
import com.ias.enums.PlaneName;

import java.time.LocalDateTime;
import java.util.List;

public class FlightDomain {
    private Long id;
    private String name;
    private String originCity;
    private String destinyCity;
    private LocalDateTime date;
    private PlaneName planeName;
    private FlightType type;
    private AirlineName airlineName;
    private int cantSeats;
    private boolean isFull;

    private List<TicketDomain> ticketDomains;

    public FlightDomain() {
    }

    public FlightDomain(Long id, String name, String originCity, String destinyCity, LocalDateTime date, PlaneName planeName, FlightType type, AirlineName airlineName, int cantSeats, boolean isFull, List<TicketDomain> ticketDomains) {
        this.id = id;
        this.name = name;
        this.originCity = originCity;
        this.destinyCity = destinyCity;
        this.date = date;
        this.planeName = planeName;
        this.type = type;
        this.airlineName = airlineName;
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

    public PlaneName getPlaneName() {
        return planeName;
    }

    public FlightType getType() {
        return type;
    }

    public AirlineName getAirlineName() {
        return airlineName;
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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setFull(boolean full) {
        isFull = full;
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
