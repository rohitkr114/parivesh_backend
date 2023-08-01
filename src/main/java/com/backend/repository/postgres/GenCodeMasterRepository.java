package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.GenCodeMaster;

public interface GenCodeMasterRepository extends JpaRepository<GenCodeMaster, Long>{

	//@Query("Select g from GenCodeMaster g where parent_grp = ?1 and is_active=true and is_deleted=false order by name asc")
	@Query(value = "Select * from master.gen_code_master g where g.parent_grp =?1  and is_active=true and is_deleted=false order by (g.name <> 'Others') desc,g.name", nativeQuery = true)
	List<GenCodeMaster> findActiveAllByParentGrp(String group);
	
	@Query("Select g from GenCodeMaster g where parent_grp = ?1 and is_active=true and is_deleted=false order by name asc")
	List<GenCodeMaster> findAllByParentGrp(String group);
	
	//@Query("Select g from GenCodeMaster g where is_active=true and is_deleted=false")
	@Query("Select g from GenCodeMaster g where is_active=true and is_deleted=false order by name asc")
	List<GenCodeMaster> findAll();
	
	@Query("Select g from GenCodeMaster g order by name asc")
	List<GenCodeMaster> findAllInactive();
	
	@Query("Select g from GenCodeMaster g where g.abbr=?1 and g.is_active='false' and g.is_deleted='true'")
	GenCodeMaster findabbrIsActiveFalse(String abbr);
	
	@Query("Select g from GenCodeMaster g where g.abbr=?1 and g.is_deleted='false'")
	GenCodeMaster findabbr(String abbr);
	
	//@Query("select DISTINCT g.parent_grp from GenCodeMaster g order by name asc")
	@Query("select DISTINCT g.parent_grp from GenCodeMaster g order by g.parent_grp asc")
	List<String> findDistinctRegistration();
}
