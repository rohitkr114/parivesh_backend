package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCAfforestationDetails;
import com.backend.model.ForestClearanceFormC.FcFormCLandDetails;

public interface FcFormCAfforestationDetailsRepository extends JpaRepository<FcFormCAfforestationDetails, Integer>{

	@Query(" select new FcFormCAfforestationDetails( id, comp_afforestation_type,"
			+ "is_applicable_compensatory_afforestation,is_non_forest_land,"
			+ "total_patches,total_districts_involved_in_ca,"
			+ "is_ua_land_smaller_from_proposed,is_nfl_free,"
			+ "present_owner_nfl,ua_land_smaller_reason,"
			+ "ua_land_area,trees_to_be_cut,trees_to_be_planted,"
			+ "trees_compensation_ratio,ca_land,trees_plantation_land_type,reason) from FcFormCAfforestationDetails where fc_form_c_id=?1")
	FcFormCAfforestationDetails getFcFormCAfforestationDetailsById(Integer id);
	
	@Query(value = " select identified_land_for_compensatory_afforestaion_copy_id,copy_of_mou_id from master.fc_form_c_afforestation_details where fc_form_c_id=?1 ", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);
	
	@Query("select new FcFormCAfforestationDetails(id) from FcFormCAfforestationDetails where fc_form_c_id=?1")
	FcFormCAfforestationDetails getDataByFcFormCId(Integer id);
}
