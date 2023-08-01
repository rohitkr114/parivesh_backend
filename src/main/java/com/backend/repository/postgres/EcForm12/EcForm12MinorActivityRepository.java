package com.backend.repository.postgres.EcForm12;

import com.backend.model.EcForm12.EcForm12MinorActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcForm12MinorActivityRepository extends JpaRepository<EcForm12MinorActivity, Integer>{

//	@Modifying
//	@Query("update EcForm12MinorActivity set is_delete='true' , is_active='false'  where id=?1")
//	Integer delete(Integer id);
//	
//	@Query("Select new EcForm12MinorActivity(em.id,em.activityId,em.subActivityId,em.threshold,em.activity_type) from EcForm12MinorActivity em where em.ecForm12.id=?1 and em.is_delete='false'")
//	List<EcForm12MinorActivity> getByEcId(Integer id);
}
