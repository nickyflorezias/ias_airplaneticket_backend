package com.ias.gateway;

import com.ias.ReservationDomain;

import java.util.List;

public interface ReservationRepositoryGateway {
    List<ReservationDomain> findAllByUserId(Long userId);
    ReservationDomain findById(Long reservationId);
    ReservationDomain save(Long userId, ReservationDomain reservationDomain, Long ticketId);
    ReservationDomain update(Long reservationId, ReservationDomain reservationDomain);
    String deleteById(Long reservationId);
}
