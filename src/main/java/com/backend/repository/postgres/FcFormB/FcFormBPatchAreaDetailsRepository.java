package com.backend.repository.postgres.FcFormB;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBPatchAreaDetails;

public interface FcFormBPatchAreaDetailsRepository extends JpaRepository<FcFormBPatchAreaDetails, Integer>{

//	@Query("Select new FcFormBPatchAreaDetails(pa.id, pa.area, pa.return_date, pa.authority_name, pa.authority_name_other, pa.land_status) from FcFormBPatchAreaDetails pa where pa.fcFormBProjectDetails.id=?1")
//	Set<FcFormBPatchAreaDetails> getByFcID(Integer id); 
	
//	@Query("Select new FcFormBPatchAreaDetails(pa.id, pa.area, pa.patch_kml, pa.gps_patch, pa.return_date, pa.authority_name, pa.authority_name_other, pa.land_status, pa.documentary_proof_copy) from FcFormBPatchAreaDetails pa where pa.fcFormBProjectDetails.id=?1")
//	Set<FcFormBPatchAreaDetails> getByFcID(Integer id); 
	
	@Query("Select pa from FcFormBPatchAreaDetails pa where pa.fcFormBProjectDetails.id=?1 and pa.is_deleted='false'")
	List<FcFormBPatchAreaDetails> getByFcID(Integer id); 
	
	@Modifying
	@Query("update FcFormBPatchAreaDetails set is_deleted='true' where id=?1")
	Integer updateFcFormBById(int id);
	
	@Query(value = " select gps_patch,documentary_proof_copy from master.fc_form_b_patch_area_details where fc_form_b_id=?1 and is_deleted='false' ", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);

}
