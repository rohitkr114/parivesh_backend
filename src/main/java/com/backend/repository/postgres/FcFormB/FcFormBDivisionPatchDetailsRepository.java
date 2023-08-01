package com.backend.repository.postgres.FcFormB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBDivisionPatchDetails;

public interface FcFormBDivisionPatchDetailsRepository extends JpaRepository<FcFormBDivisionPatchDetails, Integer>{
	
	@Query("select dp from FcFormBDivisionPatchDetails dp where dp.fcFormBProjectDetails.id=?1  and dp.is_deleted='false'")
	List<FcFormBDivisionPatchDetails> getByFcID(Integer id);

}
