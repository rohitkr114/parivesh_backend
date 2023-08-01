package com.backend.repository.postgres;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ThresholdParameter;

@Transactional
public interface ThresholdRepository extends JpaRepository<ThresholdParameter, Integer>{

	
	@Query("SELECT t from ThresholdParameter t where t.is_active='true' and t.is_deleted='false' and activity_id=?1")
    List<ThresholdParameter> findThresholdbyActId(Integer id);
	
	@Query("SELECT a from ThresholdParameter a where a.is_active=cast(?1 as boolean) and a.is_deleted=cast(?2 as boolean)")
	List<ThresholdParameter> getThresholdByStatus(String active,String delete);
    
    @Query("SELECT t from ThresholdParameter t where t.is_active='true' and t.is_deleted='false' and subactivity_id=?1")
    List<ThresholdParameter> findThresholdbySubActId(Integer id);
    
    @Query("SELECT t from ThresholdParameter t where t.is_active='true' and t.is_deleted='false' and activity_id=?2 and subactivity_id=?1")
    List<ThresholdParameter> findThreshold(Integer ActId,Integer SubId);
     
    @Query("SELECT t from ThresholdParameter t where t.is_active='true' and t.is_deleted='false'")
    List<ThresholdParameter> findAllThreshold();
    
    @Modifying
	@Query("update ThresholdParameter set name=?1 where id=?2")
	public void updateThresholdNameById(String name,Integer id);
    
    @Modifying
	@Query("update ThresholdParameter set unit=?1 where id=?2")
	public void updateThresholdUnitById(Integer unit,Integer id);
    
    @Modifying
	@Query("update ThresholdParameter set val=?1 where id=?2")
	public void updateThresholdValueById(String value,Integer id);
    
    @Modifying
	@Query("update ThresholdParameter set data_type=?1 where id=?2")
	public void updateThresholdDataTypeById(String dataType,Integer id);
    
    @Modifying
	@Query("update ThresholdParameter set is_active='false' , is_deleted='true' where id=?1")
	public void deleteThreshold(Integer id);
    
}
