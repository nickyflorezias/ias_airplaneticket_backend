package com.ias.gateway.reservation;

import com.ias.ReservationDomain;

public interface ReservationRepositoryFindByIdGateway {
    ReservationDomain findById(Long reservationId);
}
