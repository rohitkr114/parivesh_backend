package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CafLocationOfKml;


public interface CafLocationKmlRepository extends JpaRepository<CafLocationOfKml, Integer> {

	
}
