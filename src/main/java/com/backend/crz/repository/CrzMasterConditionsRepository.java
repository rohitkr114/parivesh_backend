package com.backend.crz.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.model.CrzMasterConditionsDto;

@Repository
public interface CrzMasterConditionsRepository extends JpaRepository<CrzMasterConditionsDto, Integer> {
	
	@Query(value = "select * from master.crz_master_conditions order by 1 asc", nativeQuery = true)
	Set<CrzMasterConditionsDto> findAllConditionsByOrder();
	
	@Query(value = "select * from master.crz_master_conditions cmc where cmc.type = ?1 order by 1 asc", nativeQuery = true)
	Set<CrzMasterConditionsDto> findConditionsByType(int type);
	
	@Query(value = "select * from master.crz_master_conditions cmc where cmc.type = ?1 and cmc.state_id = ?2 order by 1 asc", nativeQuery = true)
	Set<CrzMasterConditionsDto> findConditionsByStateIdAndType(int type, int StateId);
}