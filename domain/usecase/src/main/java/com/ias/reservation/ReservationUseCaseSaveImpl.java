package com.ias.reservation;

import com.ias.ReservationDomain;
import com.ias.TicketDomain;
import com.ias.UserDomain;
import com.ias.gateway.reservation.ReservationRepositorySaveGateway;
import com.ias.gateway.ticket.TicketRepositoryFindByIdGateway;
import com.ias.gateway.user.UserRepositoryFindByIdGateway;
import com.ias.ticket.TicketUseCaseFindByIdImpl;
import com.ias.user.UserUseCaseFindByIdImpl;

import java.util.logging.Logger;

public class ReservationUseCaseSaveImpl {

    private static final Logger LOGGER = Logger.getLogger(ReservationUseCaseSaveImpl.class.getName());
    private final ReservationRepositorySaveGateway reservationRepositorySaveGateway;
    private final UserUseCaseFindByIdImpl userUseCaseFindById;
    private final TicketUseCaseFindByIdImpl ticketUseCaseFindById;

    public ReservationUseCaseSaveImpl(ReservationRepositorySaveGateway reservationRepositorySaveGateway,
                                      UserUseCaseFindByIdImpl userUseCaseFindById,
                                      TicketUseCaseFindByIdImpl ticketUseCaseFindById) {
        this.reservationRepositorySaveGateway = reservationRepositorySaveGateway;
        this.userUseCaseFindById = userUseCaseFindById;
        this.ticketUseCaseFindById = ticketUseCaseFindById;
    }

    public ReservationDomain createReservation(Long userId, ReservationDomain reservationDomain, Long ticketId){
        UserDomain userFounded = userUseCaseFindById.findById(userId);
        TicketDomain ticketFounded = ticketUseCaseFindById.findById(ticketId);

        setUserToReservation(reservationDomain, userFounded);
        setTicketToReservation(reservationDomain, ticketFounded);
        LOGGER.info("Create reservation with " + reservationDomain);
        return reservationRepositorySaveGateway.save(userId, reservationDomain, ticketId);
    }

    private void setUserToReservation(ReservationDomain reservationDomain, UserDomain userDomain){
        reservationDomain.setUserDomain(userDomain);
    }

    private void setTicketToReservation(ReservationDomain reservationDomain, TicketDomain ticketDomain){
        reservationDomain.setTicketDomain(ticketDomain);
    }
}
