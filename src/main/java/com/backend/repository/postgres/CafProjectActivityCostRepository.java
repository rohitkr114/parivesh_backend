package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CafProjectActivityCost;

public interface CafProjectActivityCostRepository extends JpaRepository<CafProjectActivityCost, Integer> {

}
