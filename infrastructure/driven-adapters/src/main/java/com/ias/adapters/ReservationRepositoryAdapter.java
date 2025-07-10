package com.ias.adapters;

import com.ias.ReservationDomain;
import com.ias.dbo.FlightDBO;
import com.ias.dbo.ReservationDBO;
import com.ias.dbo.TicketDBO;
import com.ias.dbo.UserDBO;
import com.ias.gateway.FlightRepositoryGateway;
import com.ias.gateway.ReservationRepositoryGateway;
import com.ias.gateway.TicketRepositoryGateway;
import com.ias.gateway.UserRepositoryGateway;
import com.ias.repositories.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryGateway {

    private final ReservationRepository reservationRepository;
    private final UserRepositoryGateway userRepositoryGateway;
    private final TicketRepositoryGateway ticketRepositoryGateway;
    private final FlightRepositoryGateway flightRepositoryGateway;

    public ReservationRepositoryAdapter(ReservationRepository reservationRepository, UserRepositoryGateway userRepositoryGateway, TicketRepositoryGateway ticketRepositoryGateway, FlightRepositoryGateway flightRepositoryGateway) {
        this.reservationRepository = reservationRepository;
        this.userRepositoryGateway = userRepositoryGateway;
        this.ticketRepositoryGateway = ticketRepositoryGateway;
        this.flightRepositoryGateway = flightRepositoryGateway;
    }

    @Override
    @Transactional
    public List<ReservationDomain> findAllByUserId(Long userId) {
        UserDBO userFounded = UserDBO.fromDomain(userRepositoryGateway.findById(userId));
        return userFounded.getReservation()
                .stream()
                .map(ReservationDBO::toDomain)
                .toList();
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
        TicketDBO ticket = TicketDBO.fromDomain(ticketRepositoryGateway.findById(ids));

        ReservationDBO reservation = ReservationDBO.fromDomain(reservationDomain);
        reservation.setUser(userFounded);
        reservation.setTicket(ticket);
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
    public ReservationDomain updateDate(Long reservationId, ReservationDomain reservationDomain, LocalDateTime newDate) {
        ReservationDBO reservationDBO = ReservationDBO.fromDomain(findById(reservationId));
        List<FlightDBO> flights = flightRepositoryGateway.findAllByDate(newDate)
                .stream().map(FlightDBO::fromDomain).toList();

        reservationDBO.setDate(newDate);
        reservationDBO.getTicket().setFlight(flights.stream().findFirst().get());
        ticketRepositoryGateway.save(reservationDBO.getTicket().getFlight().getId(), reservationDBO.getTicket().toDomain());
        return reservationRepository.save(ReservationDBO.fromDomain(reservationDomain)).toDomain();
    }
}
