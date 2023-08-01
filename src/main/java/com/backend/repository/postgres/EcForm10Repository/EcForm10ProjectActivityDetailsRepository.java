package com.backend.repository.postgres.EcForm10Repository;

import com.backend.dto.EcPartA.EcForm10ProjectActivityDetailsDto;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10ProjectActivityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EcForm10ProjectActivityDetailsRepository extends JpaRepository<EcForm10ProjectActivityDetails, Integer> {

    @Query("select new com.backend.dto.EcPartA.EcForm10ProjectActivityDetailsDto(ec.id,"
            + "ec.activities.id,ec.activities.name,ec.activities.is_active,ec.activities.is_deleted,ec.activities.description,ec.activities.item_no"
            + ",ec.subActivities.id,ec.subActivities.name,ec.subActivities.is_active,ec.subActivities.is_deleted,ec.subActivities.description,"
            + "ec.activity_type,ec.threshold,ec.proposalNo,ec.isDelete) from EcForm10ProjectActivityDetails ec where ec.isDelete = 'false' and ec.ecForm10BasicInformation.id=?1 order by ec.activity_type asc")
    public List<EcForm10ProjectActivityDetailsDto> findDetailByEcId(Integer id);

}
