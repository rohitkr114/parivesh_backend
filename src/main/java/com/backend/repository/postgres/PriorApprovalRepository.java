package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.PriorApprovals;



public interface PriorApprovalRepository extends JpaRepository<PriorApprovals, Integer> {

	@Query("SELECT fc from PriorApprovals fc where fc.isDelete ='false' and fc.forestClearance.id=?1")
	List<PriorApprovals> findByFCID(Integer id);
	
	@Query("SELECT wl from PriorApprovals wl where wl.isDelete ='false' and wl.wildLifeClearance.id=?1")
	List<PriorApprovals> findByWLID(Integer id);
}
