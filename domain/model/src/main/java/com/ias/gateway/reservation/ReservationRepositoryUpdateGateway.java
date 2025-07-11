package com.ias.gateway.reservation;

import com.ias.ReservationDomain;

public interface ReservationRepositoryUpdateGateway {
    ReservationDomain update(Long reservationId, ReservationDomain reservationDomain);
}
