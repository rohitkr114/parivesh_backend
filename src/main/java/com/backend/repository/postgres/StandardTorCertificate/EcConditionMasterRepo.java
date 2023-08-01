package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.dto.StandadCertificateDto.ConditionMasterDto;
import com.backend.model.StandardTORCertificate.EcConditionMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EcConditionMasterRepo extends JpaRepository<EcConditionMaster, Long> {

    @Query(value = "SELECT cm.heading FROM ua.ec_condition_master cm where cm.sector_name =?1 and cm.type_proposal =?2 and cm.activity_id =?3", nativeQuery = true)
    public Set<String> getHeading (String sectorName, String typeOfProposal, int activityId);

    @Query(value = "select cm.condition_heading  FROM ua.ec_condition_master cm where cm.heading =?1 and cm.activity_id =?2", nativeQuery = true)
    public Set<String> getConditionHeadingByHeading (String heading, int activityId);

    @Query(value = "select cm.conditions FROM ua.ec_condition_master cm where cm.condition_heading =?1 and cm.activity_id =?2", nativeQuery = true)
    public Set<String> getConditionsByConditionHeading (String conditionHeading, int activityId);

    @Query(value = "select cm.heading FROM ua.ec_condition_master cm", nativeQuery = true)
    public Set<String> getAllHeading ();

    @Query(value = "SELECT cm.heading FROM ua.ec_condition_master cm where cm.activity_id =?1 and cm.type_proposal =?2", nativeQuery = true)
    public Set<String> getAllHeadingByActivityId (int activity, String proposalType);

	@Query(value = "select cm.entityid as conditionId , cm.condition_type as conditionType , cm.condition_discription as conditiondiscription, cm.project_category as projectCategory, cm.isactive as isActive from ua.condition_master cm where cm.condition_type like %:conditionType% order by cm.condition_sequence", nativeQuery = true)
	public List<ConditionMasterDto> getGeneralConditions(@Param(value="conditionType")String conditionType);
	
	@Query(value="select cm.entityid as conditionId , cm.condition_type as conditionType , cm.condition_discription as\r\n"
			+ "conditiondiscription, cm.project_category as projectCategory, cm.isactive as isActive from ua.condition_master cm\r\n"
			+ "where cm.condition_type like %:conditionType% and cm.project_category=:projectCategory order by cm.condition_sequence",nativeQuery = true)
	public List<ConditionMasterDto> getStandardConditions(String projectCategory,String conditionType);
	
}
