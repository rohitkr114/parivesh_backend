package com.backend.repository.postgres.FcFormB;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBPriorProposals;
import com.backend.model.ForestClearanceFormC.FcFormCLandDetails;

public interface FcFormBPriorProposalsRepository extends JpaRepository<FcFormBPriorProposals, Integer>{

	@Query("Select new FcFormBPriorProposals(fc.id, "
			+ "fc.proposal_no, "
			+ "fc.moef_file_no, "
			+ "fc.forest_land_area, "
			+ "fc.diversion_period, "
			+ "fc.approval_date) from FcFormBPriorProposals fc where fc.id=?1")
	FcFormBPriorProposals getById(Integer id);

	@Query("Select new FcFormBPriorProposals(fc.id, "
			+ "fc.proposal_no, "
			+ "fc.moef_file_no, "
			+ "fc.forest_land_area, "
			+ "fc.diversion_period, "
			+ "fc.approval_date) from FcFormBPriorProposals fc where fc.fcFormBProjectDetails.id=?1 and fc.is_deleted='false'")
	List<FcFormBPriorProposals> getByFcId(Integer id);
	
	@Query("Select new FcFormBPriorProposals(fc.id, "
			+ "fc.proposal_no, "
			+ "fc.moef_file_no, "
			+ "fc.forest_land_area, "
			+ "fc.diversion_period, "
			+ "fc.approval_date, "
			+ "fc.is_active, "
			+ "fc.is_deleted) from FcFormBPriorProposals fc where fc.fcFormBProjectDetails.id=?1 and fc.is_deleted='false'")
	List<FcFormBPriorProposals> getByFcIdAll(Integer id);
	
	@Modifying
	@Query("update FcFormBPriorProposals set is_deleted='true' where id=?1")
	Integer updateFcFormBById(int id);

}
