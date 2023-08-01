package com.backend.repository.postgres.FcFormB;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBApprovalDetails;
import com.backend.model.ForestClearanceFormB.FcFormBPaymentDetails;
import com.backend.model.ForestClearanceFormB.FcFormBProposedLand;

public interface FcFormBApprovalDetailsRepository extends JpaRepository<FcFormBApprovalDetails, Integer>{
	
	@Query("Select new FcFormBApprovalDetails(fc.id,"
			+ "fc.forest_land_returned,"
			+ "fc.patch_count,"
			+ "fc.forest_area,"
			+ "fc.lease_agency_name,"
			+ "fc.is_lease_transferred,"
			+ "fc.firm_name,"
			+ "fc.firm_address,"
			+ "fc.lease_transfer_date,"
			+ "fc.approval_transfer_lease,"
			+ "fc.lease_agency_address) from FcFormBApprovalDetails fc where fc.fcFormBProjectDetails.id=?1 and fc.is_deleted='false'")
	FcFormBApprovalDetails getByFcID(Integer id);

	@Modifying
	@Query("update FcFormBApprovalDetails ad set ad.is_deleted='true' where ad.id=?1")
	Integer updateFcFormBById(Integer id);
	
	@Query(value = " select transfer_deed_copy,all_patch_kml from master.fc_form_b_approval_details where fc_form_b_id=?1 and is_deleted='false'", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);
	
	@Query("select new FcFormBApprovalDetails(id) from FcFormBApprovalDetails where fc_form_b_id=?1")
	FcFormBApprovalDetails getDataByFcFormBId(Integer id);
}
