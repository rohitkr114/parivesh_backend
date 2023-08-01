package com.backend.repository.postgres.FcFormB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBProposedLand;
import com.backend.model.ForestClearanceFormC.FcFormCProposedLand;

public interface FcFormBProposedLandRepository extends JpaRepository<FcFormBProposedLand, Integer>{

	@Query("Select new FcFormBProposedLand(pl.id, "
			+ " pl.total_proposed_diversion_area, "
			+ " pl.total_non_forestland_area_required, "
			+ " pl.legal_status_of_forest_land_other, "
			+ " pl.total_forest_land, "
			+ " pl.total_non_forest_land, "
			+ " pl.total_area_of_kmls, "
			+ " pl.formBLegalStatus) from FcFormBProposedLand pl where pl.fcFormBProjectDetails.id=?1 and pl.is_deleted='false' ")
	FcFormBProposedLand getByFcID(Integer id); 
	
	@Query(value = " select geo_referenced_map from master.fc_form_b_proposed_land where fc_form_b_id=?1 and is_deleted='false' ", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);

	@Query("select new FcFormBProposedLand(id) from FcFormBProposedLand where fc_form_b_id=?1")
	FcFormBProposedLand getDataByFcFormBId(Integer id);
	
	@Query("select fc from FcFormBProposedLand fc where fc.fcFormBProjectDetails.id=?1")
	FcFormBProposedLand getDataByFcFormBIdAll(Integer id);

}
