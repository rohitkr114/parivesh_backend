package com.backend.repository.postgres.EcForm8;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.dto.EcForm8.EcForm8ProjectActivityDetailsDto;
import com.backend.model.EcForm8TransferOfTOR.EcForm8ProjectActivityDetails;

public interface EcForm8ProjectActivityDetailsRepository extends JpaRepository<EcForm8ProjectActivityDetails, Integer>{

	@Query("select new com.backend.dto.EcForm8.EcForm8ProjectActivityDetailsDto(ec.id,"
			+ "ec.activities.id,ec.activities.name,ec.activities.is_active,ec.activities.is_deleted,ec.activities.description,ec.activities.item_no"
			+ ",ec.subActivities.id,ec.subActivities.name,ec.subActivities.is_active,ec.subActivities.is_deleted,ec.subActivities.description,"
			+ "ec.activity_type,ec.threshold,ec.proposalNo,ec.isDelete) from EcForm8ProjectActivityDetails ec where ec.isDelete = 'false' and ec.ecForm8TransferOfTOR.id=?1 order by ec.activity_type asc")
	public List<EcForm8ProjectActivityDetailsDto> findDetailByEcId(Integer id);
	
}
