package com.backend.crz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzMomDelibrationDto;

@Repository
public interface CrzAgendaMomDelibrationRepository extends JpaRepository<CrzMomDelibrationDto, Integer> {

}
