package com.backend.repository.postgres.fcPriorApproval;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.fcPriorApproval.FcPriorApproval;

public interface FcPriorApprovalRepository extends JpaRepository<FcPriorApproval, Integer>{
		
	@Query("SELECT a from FcPriorApproval a where a.id=?1 and a.processedFlag='false' and a.user_id=?2")
    FcPriorApproval findWithId(Integer id, String user_id);
	
	@Query("SELECT a from FcPriorApproval a where a.id=?1 and a.user_id=?2")
    FcPriorApproval findWithIdAll(Integer id, String user_id);
	
	@Query("SELECT a from FcPriorApproval a where a.project.id=?1 and a.processedFlag='false' and a.user_id=?2")
    List<FcPriorApproval> findAllByProject(Integer id, String user_id);
	
	@Query("SELECT a from FcPriorApproval a where a.sw_proposal_no=?1 and a.processedFlag='false' and a.user_id=?2")
    List<FcPriorApproval> findAllBySw(String id, String user_id);
	
	@Query("SELECT a from FcPriorApproval a where a.processedFlag='false' and a.user_id=?1")
    List<FcPriorApproval> findLoginAll(String user_id);
	
}
