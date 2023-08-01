package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WildLifeClearance.WLComponentWiseDetails;

public interface WLComponentWiseDetailsRepository extends JpaRepository<WLComponentWiseDetails, Integer>{

}
