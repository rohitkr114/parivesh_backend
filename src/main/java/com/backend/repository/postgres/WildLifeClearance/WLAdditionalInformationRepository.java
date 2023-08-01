package com.backend.repository.postgres.WildLifeClearance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.AdditionalInformation;
import com.backend.model.WildLifeClearance.WLAdditionalInformation;

public interface WLAdditionalInformationRepository extends JpaRepository<WLAdditionalInformation, Integer>{
	
	@Query("SELECT fc from WLAdditionalInformation fc where fc.is_deleted ='false' and fc.ref_id=?1 ")
	List<WLAdditionalInformation> findAdditionalInformationByRefId(String refId);
	
}
