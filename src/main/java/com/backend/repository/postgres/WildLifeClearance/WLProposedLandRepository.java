package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.WildLifeClearance.WLProposedLand;


public interface WLProposedLandRepository extends JpaRepository<WLProposedLand, Integer>{
	
	@Query(value="select * from master.wl_proposed_land where wl_id=?1",nativeQuery=true)
	public WLProposedLand findDetailByWlId(Integer wl_id);
	

}
