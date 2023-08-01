package com.backend.repository.postgres.TrackYourProposal;

import com.backend.dto.GetDataDto;
import com.backend.dto.TrackYourProposal.ApplicationProcessHistoryDto;
import com.backend.dto.TrackYourProposal.TrackYourProposalDto;
import com.backend.model.ProponentApplications;
import com.backend.model.reports.TrackYourProposalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackYourProposalRepository extends JpaRepository<ProponentApplications, Integer> {

    @Query(value = "SELECT distinct pa.id,pa.proposal_id, pa.proposal_no as proposalNo,pa.caf_id as cAFNumber,pa.project_id,\n" +
            "pa.last_status as proposalStatus,app.id as application_id,app.name as clearanceType,\n" +
            "pd.project_name as projectName,pd.sw_no as singleWindowNumber,\n" +
            "cd.organization_name as nameOfUserAgency,env.project_category as category,\n" +
            "pa.state as state,pa.updated_on as dateOfSubmission,pa.moefcc_file_number as moefccFileNumber,pa.certificate_url as certificateUrl,\n" +
            "we.abbreviation as proposalType, pa.other_property\n" +
            "from master.proponent_applications pa\n" +
            "left join master.applications app on app.id = pa.clearance_id\n" +
            "left join master.project_details pd on pd.id = pa.project_id \n" +
            "left join master.caf_details cd on cd.id = pa.caf_id \n" +
            "left join master.ec env on env.caf_id = cd.id\n" +
            "left join ua.workgroup_entity we on we.entityid = app.work_group_id\n" +
            "where pa.proposal_no= ?1 ", nativeQuery = true)
    List<TrackYourProposalDto> getDataOnBasesOfProposalNo(String proposal_no);

    @Query(value = "SELECT distinct pa.id,pa.proposal_id, pa.proposal_no as proposalNo,pa.caf_id as cAFNumber,pa.project_id,\n" +
            "pa.last_status as proposalStatus,app.id as application_id,app.name as clearanceType,\n" +
            "pd.project_name as projectName,pd.sw_no as singleWindowNumber,\n" +
            "cd.organization_name as nameOfUserAgency,env.project_category as category,\n" +
            "pa.state as state,pa.updated_on as dateOfSubmission,pa.moefcc_file_number as moefccFileNumber,pa.certificate_url as certificateUrl,\n" +
            "we.abbreviation as proposalType, pa.other_property\n" +
            "from master.proponent_applications pa\n" +
            "left join master.applications app on app.id = pa.clearance_id\n" +
            "left join master.project_details pd on pd.id = pa.project_id \n" +
            "left join master.caf_details cd on cd.id = pa.caf_id \n" +
            "left join master.ec env on env.caf_id = cd.id\n" +
            "left join ua.workgroup_entity we on we.entityid = app.work_group_id\n" +
            "where pd.sw_no = ?1 and pa.last_status not like '%DRAFT%' ", nativeQuery = true)
    List<TrackYourProposalDto> getDataOnBasesOfSingleWindowNo(String sw_no);


    @Query(value = "SELECT distinct pa.id,pa.proposal_id, pa.proposal_no as proposalNo,pa.caf_id as cAFNumber,pa.project_id,\n" +
            "pa.last_status as proposalStatus,app.id as application_id , app.name as clearanceType,\n" +
            "pd.project_name as projectName,pd.sw_no as singleWindowNumber,\n" +
            "cd.organization_name as nameOfUserAgency,env.project_category as category,\n" +
            "pa.state as state,pa.last_submission_date as dateOfSubmission,pa.moefcc_file_number as moefccFileNumber,pa" +
            ".certificate_url1 as certificateUrl1,pa" +
            ".certificate_url as certificateUrl,\n" +
            "we.abbreviation as proposalType , pa.other_property\n" +
            "from master.proponent_applications pa\n" +
            "left join master.applications app on app.id = pa.clearance_id\n" +
            "left join master.project_details pd on pd.id = pa.project_id \n" +
            "left join master.caf_details cd on cd.id = pa.caf_id \n" +
            "left join master.ec env on env.caf_id = cd.id\n" +
            "left join ua.workgroup_entity we on we.entityid = app.work_group_id\n" +
            "where pa.proposal_no= ?1 ORDER BY pa.id ASC limit 1 ", nativeQuery = true)
    List<TrackYourProposalDto> getDataOfProposalNo(String proposal_no);


    @Query(value = "select aph.*, us.entityid, us.name from process_engine.application_process_history aph \n" +
            "join authentication.user_entity us on aph.created_by=us.entityid where aph.proposal_no= ?1 order by created_on DESC \n ", nativeQuery = true)
    List<ApplicationProcessHistoryDto> getHistoryDataOnProposal(String proposal_no);

    @Query(value = "select entityid as id, workgroup_entity.workgroup_name as name from ua.workgroup_entity order by entityid asc \n", nativeQuery = true)
    List<GetDataDto> getAllClearanceType();

    @Query(value = "select id, name from master.applications where work_group_id=  ?1 and is_deleted=false and for_department =false order by name\n", nativeQuery = true)
    List<GetDataDto> getProposalTypeOnBasesOfClearanceType(Integer id);

    @Query(value = "select distinct iss.issuing_authority as name,we.abbreviation as type  from master.issuing_authority_master as iss join ua.workgroup_entity we on we.entityid=iss.workgroup_id \n", nativeQuery = true)
    List<GetDataDto> getListOfIssuingAuthority();

    @Query(value = "select entityid as id, sector_name as name, sector_code as code from ua.sector_entity where isactive =true", nativeQuery = true)
    List<GetDataDto> getListOfSector();

    @Query(value = "select  distinct(projectcategoryname) as name, entityid as id from ua.projectcategoryentity where isactive =true", nativeQuery = true)
    List<GetDataDto> getListOfCategoryForFC();


    @Query(value = "select * from master.track_your_proposal_fnc_2(:clearanceId, :stateId, :sectorId, :proposal_Status, :proposal_type," +
            " :issuingAuthority, :cat, :startDate, :endDate, :proposal_no, :area_min,:area_max, :sw_no, :activity_id, :in_text)", nativeQuery = true)
    List<TrackYourProposalModel> searchProposal(@Param("clearanceId") String clearanceId, @Param("stateId") String stateId,
                                                @Param("sectorId") String sectorId, @Param("proposal_Status") String proposal_Status,
                                                @Param("proposal_type") String proposal_type,
                                                @Param("issuingAuthority") String issuingAuthority,
                                                @Param("cat") String cat,
                                                @Param("startDate") String startDate,
                                                @Param("endDate") String endDate,
                                                @Param("proposal_no") String proposal_no,
                                                @Param("area_min") String areaMin,
                                                @Param("area_max") String areaMax,
                                                @Param("sw_no") String sw_no,
                                                @Param("activity_id") Integer activity_id,
                                                @Param("in_text") String text
    );

    //@Query(value = "select distinct pa.last_visible_status as name  from master.proponent_applications pa where pa.last_visible_status is not null and pa.last_visible_status not ilike '%Draft%' order by pa.last_visible_status", nativeQuery = true)
    @Query(value = "select distinct status as name from process_engine.application_process_history where status not ilike '%Draft%' and status not ilike 'FORWARD%'", nativeQuery = true)
    List<GetDataDto> getListOfStatus();

    //@Query(value = "select distinct status as name from process_engine.application_process_history where workgroup_id = ?1", nativeQuery = true)
    //@Query(value="select distinct pa.last_visible_status as name  from master.proponent_applications pa where pa.clearance_id = ?1 and pa.last_visible_status is not null and pa.last_visible_status not ilike '%Draft%' order by pa.last_visible_status", nativeQuery = true)
    @Query(value = "select distinct aph.status as name from process_engine.application_process_history aph,master.proponent_applications pa\n"
            + "where aph.application_id=pa.id and pa.clearance_id= ?1 and aph.status not ilike '%Draft%' and aph.status not ilike 'FORWARD%'", nativeQuery = true)
    List<GetDataDto> getListOfStatusById(Integer workgroupId);

    @Query(value = "select distinct ps.status_on_pending as name from process_engine.process_steps ps where ps.process_id = ?1 and ps.status_on_pending not ilike '%draft%' order by ps.status_on_pending ;", nativeQuery = true)
    List<GetDataDto> getListOfFCStatusById(Integer workgroupId);

    @Query(value = "select distinct ps.status_on_pending as name from process_engine.process_steps ps left join process_engine.process_definition pd on ps.process_id = pd.id\n" +
            "where pd.workgroup_id=:workgroupId and cast(pd.application_form_id as varchar)=(case when :clearanceType is null or :clearanceType='' then cast(pd.application_form_id as varchar) else :clearanceType end)  and ps.status_on_pending not ilike '%draft%' and ps.status_on_pending not ilike 'FORWARD%' order by ps.status_on_pending;\n", nativeQuery = true)
    List<GetDataDto> getListOfECStatusById(@Param(value = "workgroupId") Integer workgroupId,@Param(value = "clearanceType") String clearanceType);

//    @Query(value = "select distinct sm.display_status  as name  from process_engine.status_mapping sm where sm.display_status not ilike '%draft%' "
//    		+ "and sm.pending_status in ( "
//    		+ "select distinct  ps.status_on_pending from process_engine.process_steps ps left join process_engine.process_definition pd on ps.process_id = pd.id "
//    		+ "where pd.workgroup_id=:workgroupId "
//    		+ "and cast(pd.application_form_id as varchar)=(case when :clearanceType is null or :clearanceType='' then cast(pd.application_form_id as varchar) else :clearanceType end)  "
//    		+ ") and sm.workgroup_id = :workgroupId "
//    		+ "order by sm.display_status; ", nativeQuery = true)
//    List<GetDataDto> getAllStatusbyId(@Param(value = "workgroupId") Integer workgroupId,@Param(value = "clearanceType") String clearanceType);
//    
    
	  @Query(value = "select distinct sm.display_status  as name  from process_engine.status_mapping sm where sm.display_status not ilike '%draft%' "
		+ " and sm.workgroup_id = :workgroupId "
		+ "order by sm.display_status; ", nativeQuery = true)
	  List<GetDataDto> getAllStatusbyId(@Param(value = "workgroupId") Integer workgroupId);

    
    @Query(value = "select distinct coalesce(pa.last_visible_status,pa.last_status) as name from master.proponent_applications pa left join process_engine.process_definition pd on pa.clearance_id = pd.application_form_id where pd.application_form_id = ?1 and pa.last_status not ilike '%DRAFT%' and pa.last_visible_status not ilike '%DRAFT%' and pa.last_status<>'REMOVED';", nativeQuery = true)
    List<GetDataDto> getListOfCRZStatusById(Integer workgroupId);

    @Query(value = "Select distinct cast(app.work_group_id as VARCHAR)  from master.applications app\n" +
            "left join master.proponent_applications pa on pa.clearance_id = app.id\n" +
            "where pa.proposal_no= ?1 or cast(pa.proposal_sequence as varchar) = ?1", nativeQuery = true)
    List<String> getWorkGroupId(String proposal_no);

    @Query(value = "select p.categorycode  from ua.projectcategoryentity p where p.projectcategoryname = ?1 and p.isactive = true limit 1", nativeQuery = true)
    String getCategoryCode(String cat);


    @Query(value = "select * from master.crz_track_your_proposal_fnc(:clearanceid, :stateid, :proposal_status, :proposal_type, :issuingauthority, :startdate, :enddate, :proposal_no, :sw_no, :in_text)", nativeQuery = true)
    List<TrackYourProposalModel> searchCrzProposal(@Param("clearanceid") String clearanceId, @Param("stateid") String stateId,
                                                   @Param("proposal_status") String proposal_Status,
                                                   @Param("proposal_type") String proposal_type,
                                                   @Param("issuingauthority") String issuingAuthority,
                                                   @Param("startdate") String startDate,
                                                   @Param("enddate") String endDate,
                                                   @Param("proposal_no") String proposal_no,
                                                   @Param("sw_no") String sw_no,
                                                   @Param("in_text") String text
    );
    
    @Query(value = "select sm.pending_status  from process_engine.status_mapping sm where sm.display_status ilike ?1 and sm.workgroup_id= ?2", nativeQuery= true)
    List<String> getPendingStatus(String status, Integer wgId);

}

