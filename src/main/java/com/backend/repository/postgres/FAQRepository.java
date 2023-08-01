package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.dto.ComplianceDTO;
import com.backend.dto.CompliancedetailDTO;
import com.backend.model.FAQ;

public interface FAQRepository extends JpaRepository<FAQ, Integer>{

	@Query("SELECT f FROM FAQ f where f.enabled=cast(?1 as boolean) ORDER BY f.order_no ASC")
	List<FAQ> get_all_faq(String active);

	@Query("SELECT f FROM FAQ f ORDER BY f.order_no ASC")
	List<FAQ> get_all_faq();
	
	@Query(value = "select * from ua.compliance(:email_id)", nativeQuery = true)
	List<ComplianceDTO> getCompliance(String email_id);
	
	@Query(value = "select * from nicdev.condition_detail(:proposal_id)", nativeQuery = true)
	List<CompliancedetailDTO> getCompliancedetail(Integer proposal_id);
	
}
