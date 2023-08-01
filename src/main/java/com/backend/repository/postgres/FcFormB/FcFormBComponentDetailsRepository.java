package com.backend.repository.postgres.FcFormB;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBComponentDetails;
import com.backend.model.ForestClearanceFormB.FcFormBLeaseDetails;

public interface FcFormBComponentDetailsRepository extends JpaRepository<FcFormBComponentDetails, Integer>{

	@Modifying
	@Query("update FcFormBComponentDetails set is_deleted='true' where id=?1")
	Integer updateFcFormBById(int id);
	
	@Query("Select ld from FcFormBComponentDetails ld where ld.fcFormBProjectDetails.id=?1 and ld.is_deleted='false'")
	List<FcFormBComponentDetails> getByFcID(Integer id); 

}
