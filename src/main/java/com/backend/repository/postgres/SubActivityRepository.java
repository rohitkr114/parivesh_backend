package com.backend.repository.postgres;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.SubActivities;

@Repository
@Transactional
public interface SubActivityRepository extends JpaRepository<SubActivities, Integer>{

	Optional<SubActivities> findById(Long id);
	
	
    //@Query("SELECT s from SubActivities s where s.is_active='true' and s.is_deleted='false' order by name")
	@Query("SELECT s from SubActivities s order by name ")
    List<SubActivities> findAllSubActivities();
    
    @Query("SELECT a from SubActivities a where a.is_active=cast(?1 as boolean) and a.is_deleted=cast(?2 as boolean) order by name")
    List<SubActivities> findAllSubActivitiesByStatus(String active,String delete);
    
    //@Query("SELECT s from SubActivities s where s.is_active='true' and s.is_deleted='false' and s.activity_id=?1 order by name")
    @Query("SELECT s from SubActivities s where s.is_active='true' and s.is_deleted='false' and activity_id=?1 order by name")
    List<SubActivities> findAllSubactivityById(Integer id);
    
	@Modifying
	@Query("update SubActivities set is_active='false' , is_deleted='true' where id=?1")
	public void deleteSubActivityById(Integer id);
	
	@Modifying
	@Query("update SubActivities set name=?1 , description=?2 where id=?3")
	public void updateSubActivityById(String name,String description,Integer id);
	
	@Modifying
	@Query("update SubActivities set is_active=?1 where id=?2")
	public void updateSubActivityStatusById(Boolean active,Integer id);
	
}
