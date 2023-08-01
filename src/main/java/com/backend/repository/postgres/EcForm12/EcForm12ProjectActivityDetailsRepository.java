package com.backend.repository.postgres.EcForm12;

import com.backend.dto.EcPartA.EcForm12ProjectActivityDetailsDto;
import com.backend.model.EcForm12.EcForm12ProjectActivityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EcForm12ProjectActivityDetailsRepository extends JpaRepository<EcForm12ProjectActivityDetails, Integer>{
	
	@Query("select new com.backend.dto.EcPartA.EcForm12ProjectActivityDetailsDto(ec.id,"
			+ "ec.activities.id,ec.activities.name,ec.activities.is_active,ec.activities.is_deleted,ec.activities.description,ec.activities.item_no"
			+ ",ec.subActivities.id,ec.subActivities.name,ec.subActivities.is_active,ec.subActivities.is_deleted,ec.subActivities.description,"
			+ "ec.activity_type,ec.threshold,ec.proposalNo,ec.isDelete) from EcForm12ProjectActivityDetails ec where ec.isDelete = 'false' and ec.ecForm12.id=?1 order by ec.activity_type asc")
	public List<EcForm12ProjectActivityDetailsDto> findDetailByEcId(Integer id);

}
