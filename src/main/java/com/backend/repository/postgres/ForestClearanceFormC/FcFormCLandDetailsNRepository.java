package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCLandDetails;
import com.backend.model.ForestClearanceFormC.FcFormCLandDetailsN;

public interface FcFormCLandDetailsNRepository extends JpaRepository<FcFormCLandDetailsN, Integer>{

	@Query(" select new FcFormCLandDetailsN( id, surface_sampling_of_temporary_change,"
			+ "pitting_of_temporary_change,drilling_of_boreholes_of_temporary_change,"
			+ "construction_of_roads_temporary_change,other_activity_temporary_change,"
			+ "total_temporary_change,drilling_of_boreholes_of_permanent_change,"
			+ "construction_of_roads_permanent_change,other_activity_permanent_change,"
			+ "total_permanent_change,name_equipment,mode_of_traction,"
			+ "size,estimated_deployment,maximum_noise,no_of_person,"
			+ "duration_of_person,details_of_sample,quantity_proposed_sample,"
			+ "estimated_accuracy_level_mineral_reserve,estimated_confidence_mineral_reserve,"
			+ "estimated_accuracy_level_boreholes,estimated_confidence_boreholes,"
			+ "proposal_no,date_of_approval,permitted_forest_land_area,"
			+ "validity_from,validity_to,details_of_violation,justification_for_extension_copy) from FcFormCLandDetailsN where fc_form_c_id=?1 ")
	FcFormCLandDetailsN getFcFormCLandDetailsById(Integer id);
	
	@Query("select new FcFormCLandDetailsN(id) from FcFormCLandDetailsN where fc_form_c_id=?1")
	FcFormCLandDetailsN getDataByFcFormCId(Integer id);
	
	@Query(value = " select details_of_existing_path_copy_id,note_containing_details_copy_id from master.fc_form_c_land_details_n where fc_form_c_id=?1 ", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);
}
