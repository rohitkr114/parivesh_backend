package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CafGovernmentOrders;

public interface CafGovernmentOrderRepository extends JpaRepository<CafGovernmentOrders, Integer> {

}
