package com.backend.repository.postgres.FcFormEPartB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormEPartB.ForestClearanceEPatchWiseDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBComponentDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBPatchWiseDetails;

public interface ForestClearanceEPatchWiseDetailsRepository extends JpaRepository<ForestClearanceEPatchWiseDetails, Integer>{

	@Query("SELECT a from ForestClearanceEPatchWiseDetails a where a.forestClearanceEPatches.id=?1")
    List<ForestClearanceEPatchWiseDetails> findAllByPatchId(Integer id);
}
