package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ActivityActionMappingDTO;

public interface ActivityActionMappingDTORespository extends JpaRepository<ActivityActionMappingDTO, Integer> {

    @Query(value = "(select ACT.id as id,ACT.activity_id as activity_id,AC.name as name,AC.sector_id as sector_id,AC.item_no as item_no "
            + "from master.activity_action_mapping as ACT inner join master.activities AC "
            + "on ACT.activity_id=AC.id where AC.is_active='true' order by AC.name asc)", nativeQuery = true)
    List<ActivityActionMappingDTO> getAllActivities();

    @Query(value = "(select ACT.id as id,ACT.activity_id as activity_id,AC.name as name,AC.sector_id as sector_id,AC.item_no as item_no "
            + "from master.activity_action_mapping as ACT inner join master.activities AC "
            + "on ACT.activity_id=AC.id where AC.is_active='true' and ACT.application_id =?1 order by AC.name asc)",
			nativeQuery =	true)
    List<ActivityActionMappingDTO> getAllActivitiesByClearenceId(Integer application_id);
}
