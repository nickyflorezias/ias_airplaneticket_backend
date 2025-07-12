package com.ias.reservation;

import com.ias.FlightDomain;
import com.ias.NonFlightAvailableException;
import com.ias.ReservationDomain;
import com.ias.ReservationService;
import com.ias.gateway.flight.FlightRepositoryFindAllByDateGateway;
import com.ias.gateway.reservation.ReservationRepositoryFindByIdGateway;
import com.ias.gateway.reservation.ReservationRepositoryUpdateDateGateway;
import com.ias.gateway.ticket.TicketRepositorySaveGateway;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationUseCaseUpdateDateImpl {

    private final ReservationRepositoryUpdateDateGateway reservationRepositoryUpdateDateGateway;
    private final ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway;
    private final ReservationService reservationService;
    private final FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway;
    private final TicketRepositorySaveGateway ticketRepositorySaveGateway;

    public ReservationUseCaseUpdateDateImpl(ReservationRepositoryUpdateDateGateway reservationRepositoryUpdateDateGateway,
                                            ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway,
                                            ReservationService reservationService,
                                            FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway,
                                            TicketRepositorySaveGateway ticketRepositorySaveGateway) {
        this.reservationRepositoryUpdateDateGateway = reservationRepositoryUpdateDateGateway;
        this.reservationRepositoryFindByIdGateway = reservationRepositoryFindByIdGateway;
        this.reservationService = reservationService;
        this.flightRepositoryFindAllByDateGateway = flightRepositoryFindAllByDateGateway;
        this.ticketRepositorySaveGateway = ticketRepositorySaveGateway;
    }

    public ReservationDomain updateDate(Long reservationId, LocalDateTime newDate){
        ReservationDomain reservationFounded = reservationRepositoryFindByIdGateway.findById(reservationId);

        reservationService.reservationStatusIsEnabled(reservationFounded);

        List<FlightDomain> flights = flightRepositoryFindAllByDateGateway.findAllByDate(newDate);

        if(flights.isEmpty()){
            throw new NonFlightAvailableException("No flights available to date " + newDate);
        }else{
            reservationFounded.setDate(newDate);
            reservationFounded.getTicket().setFlightDomain(flights.stream().findFirst().get());
            ticketRepositorySaveGateway.save(reservationFounded.getTicket().getFlight().getId(), reservationFounded.getTicket());
        }
        return reservationRepositoryUpdateDateGateway.updateDate(reservationId, reservationFounded, newDate);
    }
}
