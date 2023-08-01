package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.MsRecommendationEACDetails;

public interface MsRecommendationEACDetailsRepository extends JpaRepository<MsRecommendationEACDetails, Integer>{
	
	@Query("select new MsRecommendationEACDetails(id) from MsRecommendationEACDetails where ms_recommendation_id=?1")
	MsRecommendationEACDetails getDataByMsRecommendationId(Integer id);

}
