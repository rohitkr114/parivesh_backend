package com.backend.repository.postgres.ForestClearanceFormCPartB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormCPartB.FcFormCPartBPatchWiseDetails;

public interface FcFormCPartBPatchWiseDetailsRepository extends JpaRepository<FcFormCPartBPatchWiseDetails, Integer>{

//	@Query("SELECT a from FcFormCPartBPatchWiseDetails a where a.fcFormCPartBPatches.id=?1")
//    List<FcFormCPartBPatchWiseDetails> findAllByPatchId(Integer id);
}
