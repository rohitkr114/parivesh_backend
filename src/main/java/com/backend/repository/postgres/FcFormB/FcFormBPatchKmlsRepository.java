package com.backend.repository.postgres.FcFormB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBPatchKmls;

public interface FcFormBPatchKmlsRepository extends JpaRepository<FcFormBPatchKmls, Integer>{
	
	@Query("select fc from FcFormBPatchKmls fc where fc_form_b_id=?1 and is_deleted='false'")
	public List<FcFormBPatchKmls> findfcKMLbyfcFormBID(Integer id);
}
