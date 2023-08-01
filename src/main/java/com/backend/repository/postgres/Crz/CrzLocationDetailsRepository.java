package com.backend.repository.postgres.Crz;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Crz.CrzLocationDetails;

public interface CrzLocationDetailsRepository extends JpaRepository<CrzLocationDetails, Integer>{
	
}
