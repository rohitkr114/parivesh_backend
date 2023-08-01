package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.MsRecommendation;

public interface MsRecommendationRepository extends JpaRepository<MsRecommendation,Integer>{

}
