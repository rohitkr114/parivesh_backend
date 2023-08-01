package com.backend.repository.postgres.ForestClearancePartB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearancePartB.ForestClearanceBComponentDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBPatchWiseDetails;

public interface ForestClearanceBPatchWiseDetailsRepository extends JpaRepository<ForestClearanceBPatchWiseDetails, Integer>{

	@Query("SELECT a from ForestClearanceBPatchWiseDetails a where a.forestClearanceBPatches.id=?1")
    List<ForestClearanceBPatchWiseDetails> findAllByPatchId(Integer id);
}
