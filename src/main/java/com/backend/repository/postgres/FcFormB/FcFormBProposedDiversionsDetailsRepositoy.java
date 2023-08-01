package com.backend.repository.postgres.FcFormB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBProposedDiversionsDetails;

public interface FcFormBProposedDiversionsDetailsRepositoy extends JpaRepository<FcFormBProposedDiversionsDetails, Integer>{
	
//	@Query("select dp from FcFormBProposedDiversionsDetails dp where dp.fcFormBProjectDetails.id=?1 and dp.is_deleted='false'")
//	List<FcFormBProposedDiversionsDetails> getByFcID(Integer id);
	
	@Modifying
	@Query("update FcFormBProposedDiversionsDetails set is_deleted='true' where id=?1")
	Integer updateFcFormBById(int id);

}
