package com.ias.gateway.reservation;

import com.ias.ReservationDomain;

public interface ReservationRepositorySaveGateway {
    ReservationDomain save(Long userId, ReservationDomain reservationDomain, Long ticketId);
}
