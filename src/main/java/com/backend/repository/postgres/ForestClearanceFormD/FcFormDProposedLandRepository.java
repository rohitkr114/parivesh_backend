package com.backend.repository.postgres.ForestClearanceFormD;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormD.FcFormDProposedLand;

public interface FcFormDProposedLandRepository extends JpaRepository<FcFormDProposedLand, Integer> {

	@Query(" select new FcFormDProposedLand( id, total_proposed_diversion_area,"
			+ "total_proposed_diversion_period,total_forest_land,"
			+ "total_non_forest_land,total_area_of_kmls,distance"
			+ ") from FcFormDProposedLand where fc_form_d_id=?1")
	FcFormDProposedLand getFcFormDProposedLand(Integer id);
	
	@Query(value = " select geo_referenced_map_id,letter_of_intent_id,copy_map_outer_boundary_id from master.fc_form_d_proposed_land where fc_form_d_id=?1 ", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);
	
	@Query("select new FcFormDProposedLand(id) from FcFormDProposedLand where fc_form_d_id=?1")
	FcFormDProposedLand getDataByFcFormDId(Integer id);
}
