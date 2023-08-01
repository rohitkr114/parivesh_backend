package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CafViolations;

public interface CafViolationsRepository extends JpaRepository<CafViolations, Integer> {

}
