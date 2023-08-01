package com.backend.repository.postgres.FcFormB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBApprovalDetails;
import com.backend.model.ForestClearanceFormB.FcFormBMiningProposals;

public interface FcFormBMiningProposalsRepository extends JpaRepository<FcFormBMiningProposals, Integer>{
	
	@Query("Select pa from FcFormBMiningProposals pa where pa.fcFormBProjectDetails.id=?1 and pa.is_deleted='false'")
	FcFormBMiningProposals getByFcID(Integer id); 

	@Query("select new FcFormBMiningProposals(id) from FcFormBMiningProposals where fc_form_b_id=?1")
	FcFormBMiningProposals getDataByFcFormBId(Integer id);

}
