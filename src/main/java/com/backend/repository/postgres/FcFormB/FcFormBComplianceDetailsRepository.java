package com.backend.repository.postgres.FcFormB;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBComplianceDetails;

public interface FcFormBComplianceDetailsRepository extends JpaRepository<FcFormBComplianceDetails, Integer>{
	
	@Query("Select new FcFormBComplianceDetails(fc.id, "
			+ "fc.proposal_no, "
			+ "fc.moef_file_no, "
			+ "fc.moef_condition, "
			+ "fc.compliance_details) from FcFormBComplianceDetails fc where fc.fcFormBProjectDetails.id=?1 and fc.is_deleted='false'")
	List<FcFormBComplianceDetails> getByFcID(Integer id);

	@Modifying
	@Query("update FcFormBComplianceDetails set is_deleted='true' where id=?1")
	Integer updateFcFormBById(int id);

}
