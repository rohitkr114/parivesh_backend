package com.backend.repository.postgres.ForestClearanceFormE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceE.FcFormEKmls;
import com.backend.model.ForestClearanceE.FcFormEPatchDetails;

public interface FcFormEPatchDetailsRepository extends JpaRepository<FcFormEPatchDetails, Integer> {

	@Query("select new FcFormEPatchDetails(id) from FcFormEPatchDetails where fc_form_e_id=?1")
	FcFormEPatchDetails getDataByFcFormEId(Integer id);

	@Query("Select pa from FcFormEPatchDetails pa where pa.fcFormE.id=?1 and pa.is_delete='false'")
	List<FcFormEPatchDetails> getByFcID(Integer id);

	@Modifying
	@Query("update FcFormEPatchDetails set is_delete='true' where id=?1")
	Integer deletePatchDetails(Integer fc_form_e_id);

//	@Query("Select pa from FcFormEPatchDetails pa where pa.fcFormEOtherDetails.id=?1 and pa.is_delete='false'")
//	FcFormEPatchDetails getByOtherId(Integer id); 
}
