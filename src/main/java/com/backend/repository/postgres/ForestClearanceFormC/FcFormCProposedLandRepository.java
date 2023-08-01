package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCPriorApproval;
import com.backend.model.ForestClearanceFormC.FcFormCProposedLand;

public interface FcFormCProposedLandRepository extends JpaRepository<FcFormCProposedLand, Integer>{

	@Query(" select new FcFormCProposedLand( id,"
			+ "total_proposed_diversion_period,"
			+ "legal_status_of_forest_land, legal_status_of_forest_land_other, total_forest_land,"
			+ "total_non_forest_land, total_area_of_kmls,legal_status_forest_land_area,canopy_density) from FcFormCProposedLand where fc_form_c_id=?1 ")
	FcFormCProposedLand getFcFormCProposedLandById(Integer id);
	
	@Query("select new FcFormCProposedLand(id) from FcFormCProposedLand where fc_form_c_id=?1")
	FcFormCProposedLand getDataByFcFormCId(Integer id);
}
