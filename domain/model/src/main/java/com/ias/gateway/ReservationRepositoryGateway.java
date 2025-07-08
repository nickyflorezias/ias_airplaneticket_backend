package com.ias.gateway;

import com.ias.ReservationDomain;

import java.util.List;

public interface ReservationRepositoryGateway {
    List<ReservationDomain> findAllByUserId(Long userId);
    ReservationDomain findById(Long reservationId);
    ReservationDomain save(ReservationDomain reservationDomain);
    String deleteById(Long reservationId);
}
