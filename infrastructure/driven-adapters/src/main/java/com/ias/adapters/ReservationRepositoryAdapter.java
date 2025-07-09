package com.ias.adapters;

import com.ias.ReservationDomain;
import com.ias.TicketDomain;
import com.ias.UserDomain;
import com.ias.dbo.ReservationDBO;
import com.ias.dbo.TicketDBO;
import com.ias.dbo.UserDBO;
import com.ias.gateway.ReservationRepositoryGateway;
import com.ias.gateway.TicketRepositoryGateway;
import com.ias.gateway.UserRepositoryGateway;
import com.ias.repositories.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryGateway {

    private final ReservationRepository reservationRepository;
    private final UserRepositoryGateway userRepositoryGateway;
    private final TicketRepositoryGateway ticketRepositoryGateway;

    public ReservationRepositoryAdapter(ReservationRepository reservationRepository, UserRepositoryGateway userRepositoryGateway, TicketRepositoryGateway ticketRepositoryGateway) {
        this.reservationRepository = reservationRepository;
        this.userRepositoryGateway = userRepositoryGateway;
        this.ticketRepositoryGateway = ticketRepositoryGateway;
    }

    @Override
    @Transactional
    public List<ReservationDomain> findAllByUserId(Long userId) {
        UserDomain userFounded = userRepositoryGateway.findById(userId);
        return userFounded.getReservations();
    }

    @Override
    @Transactional
    public ReservationDomain findById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(ReservationDBO::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found."));
    }

    // TODO - MOVER LA LOGICA A EL USECASE
    @Override
    @Transactional
    public ReservationDomain save(Long userId, ReservationDomain reservationDomain, Long ids) {
        UserDBO userFounded = UserDBO.fromDomain(userRepositoryGateway.findById(userId));
        TicketDBO tickets = TicketDBO.fromDomain(ticketRepositoryGateway.findById(ids));

        ReservationDBO reservation = ReservationDBO.fromDomain(reservationDomain);
        reservation.setUser(userFounded);
        reservation.setTicket(tickets);
        reservationRepository.save(reservation);

        return reservation.toDomain();
    }

    @Override
    @Transactional
    public ReservationDomain update(Long reservationId, ReservationDomain reservationDomain) {
        findById(reservationId);
        return reservationRepository.save(ReservationDBO.fromDomain(reservationDomain)).toDomain();
    }

    @Override
    @Transactional
    public String deleteById(Long reservationId) {
        ReservationDBO reservationFounded = ReservationDBO.fromDomain(findById(reservationId));
        reservationRepository.delete(reservationFounded);
        return "Reservation with id: " + reservationId + " removed successfully";
    }
}
