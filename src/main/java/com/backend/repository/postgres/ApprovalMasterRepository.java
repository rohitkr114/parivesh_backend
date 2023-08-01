package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.HomeApprovalMaster;

public interface ApprovalMasterRepository extends JpaRepository<HomeApprovalMaster, Integer> {
	
	@Query("SELECT h FROM HomeApprovalMaster h where h.enabled=cast(?1 as boolean) ORDER BY h.order_no ASC")
	List<HomeApprovalMaster> get_all_Approval(String active);
	
	@Query("SELECT h FROM HomeApprovalMaster h ORDER BY h.order_no ASC")
	List<HomeApprovalMaster> get_all_Approvals();
}
