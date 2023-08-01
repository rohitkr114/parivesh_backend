package com.backend.repository.postgres.FcFormB;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBLeaseDetails;

public interface FcFormBLeaseDetailsRepository extends JpaRepository<FcFormBLeaseDetails, Integer>{

//	@Query("Select new FcFormBLeaseDetails(ld.id,ld.moef_file_no,ld.approval_date) from FcFormBLeaseDetails ld where ld.fcFormBProjectDetails.id=?1")
//	Set<FcFormBLeaseDetails> getByFcID(Integer id); 
	
//	@Query("Select new FcFormBLeaseDetails(ld.id,ld.moef_file_no,ld.approval_copy,ld.approval_date) from FcFormBLeaseDetails ld where ld.fcFormBProjectDetails.id=?1")
//	Set<FcFormBLeaseDetails> getByFcID(Integer id); 
	
	@Query("Select ld from FcFormBLeaseDetails ld where ld.fcFormBProjectDetails.id=?1 and ld.is_deleted='false'")
	List<FcFormBLeaseDetails> getByFcID(Integer id); 
	
	@Query(value = " select approval_copy from master.fc_form_b_lease_details where fc_form_b_id=?1 and is_deleted='false' ", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);
}
