package com.ias.gateway.reservation;

import com.ias.ReservationDomain;

import java.util.List;

public interface ReservationRepositoryFindByUserIdGateway {
    List<ReservationDomain> findAllByUserId(Long userId);
}
