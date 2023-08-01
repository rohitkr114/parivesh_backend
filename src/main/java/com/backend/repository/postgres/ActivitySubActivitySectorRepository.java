package com.backend.repository.postgres;

import com.backend.dto.SectorEntityDto;
import com.backend.model.ActivitySubActivitySector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivitySubActivitySectorRepository extends JpaRepository<ActivitySubActivitySector, Integer> {

    @Query("SELECT s from ActivitySubActivitySector s where s.activity_id=?1 and s.subactivity_id=?2")
    ActivitySubActivitySector getSector(Integer ActId, Integer SubActId);

    @Query(value = " select se.sector_code from master.activities a left join ua.sector_entity se on se.entityid = a.sector_id where a.id = ?1 ", nativeQuery = true)
    String getSectorByActivityID(Integer activities_id);

    @Query(value = " select a.sector_id from master.activities a where id = ?1 ", nativeQuery = true)
    Integer getSectorID(Integer activities_id);
    
    @Query(value = "select se.sector_name from master.activity_subactivity_sector ass join ua.sector_entity se on ass.sector_id = se.entityid  where ass.activity_id = ?1 and ass.subactivity_id = ?2 limit 1", nativeQuery = true)
    String getSectorName(Integer ActId, Integer SubActId);
    
    @Query(value = "select se.sub_activity_condition_applicability from master.activity_subactivity_sector ass join ua.sector_entity se on ass.sector_id = se.entityid  where ass.activity_id = ?1 and ass.subactivity_id = ?2", nativeQuery = true)
    Boolean getSubActivityConditionApllicability(Integer ActId, Integer SubActId);
}


