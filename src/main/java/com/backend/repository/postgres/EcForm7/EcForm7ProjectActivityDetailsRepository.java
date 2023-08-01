package com.backend.repository.postgres.EcForm7;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.dto.EcPartA.EcForm7ProjectActivityDetailsDto;
import com.backend.model.EcForm7.EcForm7ProjectActivityDetails;

public interface EcForm7ProjectActivityDetailsRepository extends JpaRepository<EcForm7ProjectActivityDetails, Integer>{
	
	@Query("select new com.backend.dto.EcPartA.EcForm7ProjectActivityDetailsDto(ec.id,"
			+ "ec.activities.id,ec.activities.name,ec.activities.is_active,ec.activities.is_deleted,ec.activities.description,ec.activities.item_no"
			+ ",ec.subActivities.id,ec.subActivities.name,ec.subActivities.is_active,ec.subActivities.is_deleted,ec.subActivities.description,"
			+ "ec.activity_type,ec.threshold,ec.proposalNo,ec.isDelete) from EcForm7ProjectActivityDetails ec where ec.isDelete = 'false' and ec.ecForm7.id=?1 order by ec.activity_type asc")
	public List<EcForm7ProjectActivityDetailsDto> findDetailByEcId(Integer id);

}
