package com.ias.gateway.reservation;

import com.ias.ReservationDomain;

import java.time.LocalDateTime;

public interface ReservationRepositoryUpdateDateGateway {
    ReservationDomain updateDate(Long reservationId, ReservationDomain reservationDomain, LocalDateTime newDate);
}
