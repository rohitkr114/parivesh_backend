package com.backend.repository.postgres.ForestClearanceFormD;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormD.FcFormDLegalStatus;

public interface FcFormDLegalStatusRepository extends JpaRepository<FcFormDLegalStatus, Integer>{
	
	@Query("SELECT fc from FcFormDLegalStatus fc where fc.is_deleted ='false' and fc.fcFormD.id=?1")
	List<FcFormDLegalStatus> findByFcFormDLegalStatus(Integer id);

}
