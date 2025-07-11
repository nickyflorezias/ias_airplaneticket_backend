package com.ias.gateway.reservation;

import com.ias.ReservationDomain;

import java.util.List;

public interface ReservationRepositoryFindGateway {
    List<ReservationDomain> findAllByUserId(Long userId);
    ReservationDomain findById(Long reservationId);
}
