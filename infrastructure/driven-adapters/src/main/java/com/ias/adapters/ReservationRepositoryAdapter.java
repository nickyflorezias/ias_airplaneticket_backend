package com.ias.adapters;

import com.ias.ReservationDomain;
import com.ias.dbo.ReservationDBO;
import com.ias.dbo.UserDBO;
import com.ias.gateway.reservation.ReservationRepositoryFindGateway;
import com.ias.gateway.reservation.ReservationRepositorySaveGateway;
import com.ias.gateway.reservation.ReservationRepositoryUpdateDateGateway;
import com.ias.gateway.reservation.ReservationRepositoryUpdateGateway;
import com.ias.gateway.user.UserRepositoryFindGateway;
import com.ias.repositories.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryFindGateway, ReservationRepositorySaveGateway, ReservationRepositoryUpdateGateway, ReservationRepositoryUpdateDateGateway {

    private final ReservationRepository reservationRepository;
    private final UserRepositoryFindGateway userRepositoryFindGateway;

    public ReservationRepositoryAdapter(ReservationRepository reservationRepository, UserRepositoryFindGateway userRepositoryFindGateway) {
        this.reservationRepository = reservationRepository;
        this.userRepositoryFindGateway = userRepositoryFindGateway;
    }

    @Override
    @Transactional
    public List<ReservationDomain> findAllByUserId(Long userId) {
        UserDBO userFounded = UserDBO.fromDomain(userRepositoryFindGateway.findById(userId));
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

    @Override
    @Transactional
    public ReservationDomain save(Long userId, ReservationDomain reservationDomain, Long ticketId) {
        return reservationRepository.save(ReservationDBO.fromDomain(reservationDomain)).toDomain();
    }

    @Override
    @Transactional
    public ReservationDomain update(Long reservationId, ReservationDomain reservationDomain) {
        return reservationRepository.save(ReservationDBO.fromDomain(reservationDomain)).toDomain();
    }

    @Override
    @Transactional
    public ReservationDomain updateDate(Long reservationId, ReservationDomain reservationDomain, LocalDateTime newDate) {
        return reservationRepository.save(ReservationDBO.fromDomain(reservationDomain)).toDomain();
    }
}
