package com.backend.repository.postgres;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.backend.dto.GetDataDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.Activities;


@Transactional
public interface ActivityRepository extends JpaRepository<Activities, Integer>{

	//@Query("SELECT a from activities a order by name")
	@Query("SELECT a from activities a where a.is_active= cast('true' as boolean) and a.is_deleted=cast('false' as boolean) order by name")
    List<Activities> findAllActivities();
	
	@Query("SELECT a from activities a where a.is_active=cast(?1 as boolean) and a.is_deleted=cast(?2 as boolean) order by name")
	List<Activities> findAllActivitiesbyStatus(String active,String delete);
	
	@Query("SELECT s from activities s where s.is_active='true' and s.is_deleted='false' and s.id=?1 order by name")
    List<Activities> findAllActivityById(Integer id);
	
	@Modifying
	@Query("update activities set is_active='false' , is_deleted='true' where id=?1")
	public void deleteActivityById(Integer id);
    
	@Modifying
	@Query("update activities set name=?1 , description=?2 where id=?3")
	public void updateActivityById(String name,String description,Integer id);
	
	@Modifying
	@Query("update activities set is_active=?1 where id=?2")
	public void updateActivityStatusById(Boolean active,Integer id);

	@Query(value="Select Distinct(se.sector_code) From master.activities act\n" +
			"join ua.sector_entity se on se.entityid = act.sector_id and se.isactive = true\n" +
			"where act.sector_id =?1", nativeQuery = true)
	public Optional<String> getSectorCodeByEcId (Integer ecId);

	@Query(value="select sector_code as name ,se.entityid as id,id as id1 from  master.activities act inner join ua.sector_entity se\n" +
			"on se.entityid = act.sector_id\n" +
			"where act.is_active = true and act.item_no is not null and act.id =?1",nativeQuery = true)
	public Optional<GetDataDto> getSectorCodeByName (Integer activity_id);
	
}
