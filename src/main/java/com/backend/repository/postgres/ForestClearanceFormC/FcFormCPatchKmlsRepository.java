package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCPatchKmls;

public interface FcFormCPatchKmlsRepository extends JpaRepository<FcFormCPatchKmls, Integer>{
	
	@Query("select fc from FcFormCPatchKmls fc where fc_form_c_id=?1 and is_deleted='false'")
	public List<FcFormCPatchKmls> findfcKMLbyfcFormCID(Integer id);
	
	/*@Query(value = " select * from master.fc_form_c where id=?1 ", nativeQuery = true)
	public List<Object[]> getDocuments(Integer id);*/
}
