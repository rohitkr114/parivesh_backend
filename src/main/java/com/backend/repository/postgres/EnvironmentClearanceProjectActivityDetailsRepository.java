package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.model.EnvironmentClearanceProjectActivityDetails;

public interface EnvironmentClearanceProjectActivityDetailsRepository
		extends JpaRepository<EnvironmentClearanceProjectActivityDetails, Integer> {

	@Query("select new com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto(ec.id,"
			+ "ec.activities.id,ec.activities.name,ec.activities.is_active,ec.activities.is_deleted,ec.activities.description,ec.activities.item_no"
			+ ",ec.subActivities.id,ec.subActivities.name,ec.subActivities.is_active,ec.subActivities.is_deleted,ec.subActivities.description,"
			+ "ec.activity_type,ec.threshold,ec.proposalNo,ec.isDelete) from EnvironmentClearanceProjectActivityDetails ec where ec.isDelete = 'false' and ec.environmentClearence.id=?1 order by ec.activity_type asc")
	public List<EnvironmentClearanceProjectActivityDetailsDto> findDetailByEcId(Integer id);

}