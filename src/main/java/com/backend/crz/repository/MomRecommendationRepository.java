package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.MomRecommendation;

@Repository
public interface MomRecommendationRepository extends JpaRepository<MomRecommendation, Integer> {
	
	@Query(value = "SELECT * FROM master.crz_mom_recommendation", nativeQuery = true)
	List<MomRecommendation> findAllMomRecommendationList();

	
	
}
