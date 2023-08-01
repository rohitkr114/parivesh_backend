package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCActivitiesDetails;
import com.backend.model.ForestClearanceFormC.FcFormCPriorApproval;

public interface FcFormCPriorApprovalRepository extends JpaRepository<FcFormCPriorApproval, Integer>{

	@Query("SELECT fc from FcFormCPriorApproval fc where fc.is_delete ='false' and fc.fcFormC.id=?1")
	List<FcFormCPriorApproval> findByFCID(Integer id);

	@Query(" Select new FcFormCPriorApproval( id, proposal_no,proposal_name,"
			+ "moefcc_file_no,area_proposed_diversion,area_diverted,recommended_diversion_area,"
			+ " in_principal_approval_date, final_approval, application_date)"
			+ " from FcFormCPriorApproval where fc_form_c_id = ?1 and is_delete='false'")
	List<FcFormCPriorApproval> getFcPriorAppById(Integer id);
	
	@Query("select new FcFormCPriorApproval(id) from FcFormCPriorApproval where fc_form_c_id=?1")
	FcFormCPriorApproval getDataByFcFormCId(Integer id);
}
