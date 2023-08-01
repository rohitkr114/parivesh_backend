package com.backend.repository.postgres.ForestClearanceFormC;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCActivitiesDetails;
import com.backend.model.ForestClearanceFormC.FcFormCLandDetails;

public interface FcFormCActivitiesDetailsRepository extends JpaRepository<FcFormCActivitiesDetails, Integer>{

	@Query(" select  new FcFormCActivitiesDetails( id, surface_Sampling, area"
			+ ",no_of_pits,width_of_pit,depth_of_pit,length_of_pit"
			+ ",volume_of_excavation,surface_area,no_of_drills,diameter,depth_of_boreholes"
			+ ",forest_area_temporary_change,forest_area_permanent_change,meterage_of_boreholes"
			+ ",length_temporary_change,width_in_temporary_change,area_in_temporary_change,"
			+ "length_permanent_change,width_in_permanent_change,area_in_permanent_change,"
			+ "is_activity_involved_on_temporary_change,activity_on_temporary_change,"
			+ "activity_area_on_temporary_change,"
			+ "is_activity_involved_on_permanent_change,activity_on_permanent_change,"
			+ "activity_area_on_permanent_change) from FcFormCActivitiesDetails where fc_form_c_id=?1")
	FcFormCActivitiesDetails getFcFormCActivityDetailsById(Integer id);
	
	@Query("select new FcFormCActivitiesDetails(id) from FcFormCActivitiesDetails where fc_form_c_id=?1")
	FcFormCActivitiesDetails getDataByFcFormCId(Integer id);
}
