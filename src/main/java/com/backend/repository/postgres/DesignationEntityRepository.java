package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.DesignationEntity;

public interface DesignationEntityRepository extends JpaRepository<DesignationEntity, Integer> {
	
	@Query(value="select de.* from ua.designation_entity de where de.entityid =(select e.designation_id from authentication.employee e where e.entityid=:id)", nativeQuery=true)
	public DesignationEntity getDesignationEntity(@Param(value="id") Integer id);
	
	@Query("select de from DesignationEntity de where de.isActive=true order by entityId")
	public List<DesignationEntity> getAllDesignation();
	
	@Query(value="select count(de.*) from ua.designation_entity de where de.designation_name ilike :designation and de.isactive =true", nativeQuery = true)
	public Integer checkDuplicateDesignation(@Param(value="designation") String designation);
}
