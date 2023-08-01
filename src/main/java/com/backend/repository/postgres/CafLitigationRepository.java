package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CafLitigations;

public interface CafLitigationRepository extends JpaRepository<CafLitigations, Integer> {

}
