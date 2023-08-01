package com.backend.repository.postgres.EcForm7;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcForm7.EcForm7MinorActivity;

public interface EcForm7MinorActivityRepository extends JpaRepository<EcForm7MinorActivity, Integer>{

//	@Modifying
//	@Query("update EcForm7MinorActivity set is_delete='true' , is_active='false'  where id=?1")
//	Integer delete(Integer id);
//	
//	@Query("Select new EcForm7MinorActivity(em.id,em.activityId,em.subActivityId,em.threshold,em.activity_type) from EcForm7MinorActivity em where em.ecForm7.id=?1 and em.is_delete='false'")
//	List<EcForm7MinorActivity> getByEcId(Integer id);
}
