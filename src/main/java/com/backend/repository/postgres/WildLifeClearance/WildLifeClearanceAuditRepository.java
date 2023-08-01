package com.backend.repository.postgres.WildLifeClearance;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.WildLifeClearance.WildLifeClearanceAudit;

public interface WildLifeClearanceAuditRepository extends JpaRepository<WildLifeClearanceAudit, Integer>{
	
	  @Query("select c from WildLifeClearanceAudit c where c.id=?1") 
	  public WildLifeClearanceAudit findDetailByWlId(Integer id);
	 
	 

}
