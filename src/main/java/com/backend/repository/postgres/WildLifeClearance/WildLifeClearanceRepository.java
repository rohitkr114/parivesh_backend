package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.WildLifeClearance.WildLifeClearance;

public interface WildLifeClearanceRepository extends JpaRepository<WildLifeClearance, Integer>{

	@Query("select c from WildLifeClearance c where c.id=?1")
	public WildLifeClearance findDetailByWlId(Integer id);
}
