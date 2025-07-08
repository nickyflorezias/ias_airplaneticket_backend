package com.ias.adapters;

import com.ias.ReservationDomain;
import com.ias.gateway.ReservationRepositoryGateway;
import com.ias.repositories.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryGateway {

    private final ReservationRepository reservationRepository;

    public ReservationRepositoryAdapter(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationDomain> findAllByUserId(Long userId) {
        return List.of();
    }

    @Override
    public ReservationDomain findById(Long reservationId) {
        return null;
    }

    @Override
    public ReservationDomain save(ReservationDomain reservationDomain) {
        return null;
    }

    @Override
    public String deleteById(Long reservationId) {
        return "";
    }
}
