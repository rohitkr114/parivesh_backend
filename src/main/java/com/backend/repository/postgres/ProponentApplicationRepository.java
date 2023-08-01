package com.backend.repository.postgres;

import com.backend.dto.*;
import com.backend.model.ProponentApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProponentApplicationRepository extends JpaRepository<ProponentApplications, Integer> {

    @Query("select p.state_id from ProponentApplications p where p.id=:id")
    public Integer getStateCodeById(@Param(value = "id") Integer id);

    @Query("Select p from ProponentApplications p where p.caf_id=?1")
    public List<ProponentApplications> getApplicationByCafId(Integer id);

    @Query("Select p from ProponentApplications p where p.proposal_no=?1")
    public ProponentApplications getApplicationByProposalNo(String proposal);

    @Query("Select p from ProponentApplications p where p.moefccFileNumber like '%?1%'")
    public ProponentApplications getApplicationByFileNo(String fileNo);

    @Query("Select distinct p from ProponentApplications p left join p.projectDetails pro left join pro.employees em left join pro.consultants con where em.entityid=?2 or p.created_by=?2 or con.entityid=?2 and p.projectDetails.id=?1")
    public List<ProponentApplications> findByProjectId(Integer projectId, Integer user_id);

    @Query("Select distinct p from ProponentApplications p left join p.projectDetails pro left join pro.employees em left join pro.consultants con where p.created_by=?1 or em.entityid=?1 or con.entityid=?1")
    public List<ProponentApplications> findAllProponents(Integer user_id);

    @Query(value = "select * from master.proponent_applications pa left join " +
            "master.project_details pd on pd.id = pa.project_id left join " +
            "master.employee_to_project etp on etp.project_id = pd.id left join " +
            "master.consultant_to_project ctp on ctp.project_id = pd.id where (pd.created_by = ?1 or etp.user_id = ?1 or ctp.user_id = ?1) " +
            "and (pa.proposal_no not in (select distinct aph.proposal_no from process_engine.application_process_history aph where aph.status = 'Submitted') and pa.last_status = 'EDS_RAISED')", nativeQuery = true)
    public List<ProponentApplications> getEDSApplications(Integer user_id);

    @Query("Select p from ProponentApplications p where p.proposal_id=?1")
    public ProponentApplications getApplicationByProposalId(Integer proposalId);

    @Query("Select p from ProponentApplications p where p.proposal_id=?1")
    public ProponentApplications getApplicationByProposalId_6(Integer proposalId);

    @Query("Select p from ProponentApplications p where p.proposal_id=?1")
    public Optional<ProponentApplications> getApplicationByProposalId_7(Integer proposalId);

    @Query(value = "Select * from master.proponent_applications where proposal_id=?1 Order by id DESC Limit 1", nativeQuery = true)
    public ProponentApplications getApplicationByProposalIdD(Integer proposalId);

    @Query("select new ProponentApplications(p.id, p.caf_id, p.proposal_id, p.clearance_id," + "	p.proposal_no, p.state, p.state_id, p.applications , p.ip_address," + "	p.last_status, p.last_remarks, p.current_step, p.migration_status," + "	p.transfer_proposal_no, p.transfer_proposal_remarks, p.transfer_proposal_date," + "	p.last_submission_date, p.updated_by, p.moefccFileNumber,p.stateFileNumber, p.iroFileNumber, p.grant_date,p.grant_date1,p.proposal_duration ) from ProponentApplications p where p.proposal_id=?1")
    public ProponentApplications getAppById(Integer id);

    @Query(value = " select  cast(master.copy_tor_amendment_fnc(:proposal_id) as Integer)  ; ", nativeQuery = true)
    public Integer getProponentAppId(@Param("proposal_id") Integer proposal_id);

    @Query(value = " select  cast(master.copy_crz_amendment_fnc(:proposal_id) as Integer)  ; ", nativeQuery = true)
    public Integer getCrzProponentAppId(@Param("proposal_id") Integer proposal_id);

    @Query(value = " select  cast(master.copy_ec_partc_amendment_fnc(:proposal_id) as Integer)  ; ", nativeQuery = true)
    public Integer getProponentAppIdEcPartC(@Param("proposal_id") Integer proposal_id);

    @Query(value = "select count(*) from master.proponent_applications where moefcc_file_number =?1 ", nativeQuery = true)
    public Integer fileNameCheck(String fn);

    @Query(value = "select pa.proposal_id from master.proponent_applications pa where pa.id =?1", nativeQuery = true)
    public Optional<Integer> getProposalId(Integer id);

    @Query(value = "select pa.proposal_sequence from master.proponent_applications pa order by proposal_sequence desc limit 1", nativeQuery = true)
    public Integer getMaxProposalSequence();

    @Query(value = "select pa.caf_id_sequence from master.caf_details pa order by caf_id_sequence desc limit 1", nativeQuery = true)
    public Integer getMaxCafSequence();

    @Query(value = "select p.projectcategoryname from ua.projectcategoryentity as p where p.entityid=:entityId", nativeQuery = true)
    public String getProjectCategoryName(@Param(value = "entityId") Integer entityId);

    @Query(value = "select * from master.proponent_applications where id =?1 and proposal_no=?2 ", nativeQuery = true)
    public Optional<ProponentApplications> getProponentByIdAndProposalNo(Integer id, String proposal_no);

    @Query(value = " select pa.moefcc_file_number from master.proponent_applications pa where pa.proposal_id =?1", nativeQuery = true)
    public String getMoefccFileNo(Integer id);

    @Query(name = "ProponentApplicationRepository.findGeneralCondition", nativeQuery = true)
    List<CertificateConditionDTO> findGeneralCondition(Integer id);

    @Query(name = "ProponentApplicationRepository.findOtherSpecificCondition", nativeQuery = true)
    List<CertificateConditionDTO> findOtherSpecificCondition(Integer id);

    @Query(name = "ProponentApplicationRepository.findSpecificCondition", nativeQuery = true)
    List<CertificateConditionDTO> findSpecificCondition(Integer id);

    @Query(name = "ProponentApplicationRepository.findStandardCondition", nativeQuery = true)
    List<CertificateConditionDTO> findStandardCondition(Integer id);

    @Modifying
    @Query(value = " update master.proponent_applications set certificate_url=:url,grant_date= current_timestamp  where id =:id ", nativeQuery = true)
    Integer updateCertificateURL(@Param("id") Integer id, @Param("url") String url);

    @Modifying
    @Query(value = " update master.proponent_applications set certificate_url1=:url,grant_date1= current_timestamp where id =:id ", nativeQuery = true)
    Integer updateCertificateURL1(@Param("id") Integer id, @Param("url") String url);

    @Query(value = "Select clearance_id from master.proponent_applications where id =?1", nativeQuery = true)
    Integer getApplicationsById(int id, String proposal_no);

    @Query(value = "select * from master.proponent_applications p where p.id=:id limit 1", nativeQuery = true)
    public ProponentApplications getProponentApplicationById(@Param("id") Integer id);

    @Query(value = " select pa.* FROM master.proponent_applications pa, master.project_details pd where pd.id=pa.project_id and pd.created_by = :id and pa.last_status not in (:status) " +
            " and pa.proposal_no not in (select proposal_no  from master.proposal_withdraw_request pwr where status in ('WITHDRAW_REQUESTED','WITHDRAW_ACCEPTED') and pwr.proposal_no is not null) " +
            " and pa.proposal_no not in (select ap.proposal_no  from agenda_mom.agenda_proposal ap where is_active =true and is_deleted =false and ap.proposal_no is not null) " +
            " order by created_on OFFSET :page LIMIT :size ; ", nativeQuery = true)
    List<ProponentApplications> getProposalList(@Param("id") Integer id, @Param("status") List<String> status, @Param("page") Integer page, @Param("size") Integer size);

    @Query(value = "select distinct  pa.*,esdv.sector_id as sectorid,se.sector_name  from process_engine.application_process_history aph,master.proponent_applications pa,master.ec_sector_dtls_vw esdv,ua.sector_entity se where  pa.last_status NOT in  (:status) and aph.workgroup_id =1 and pa.proposal_id =esdv.proposal_id and esdv.sector_id =se.entityid and esdv.sector_id in (select  sector_id from ua.user_access_mapping uam where user_id = :user_id) and pa.id =aph.application_id and pa.proposal_no like 'IA%'and aph.workgroup_id =1 order by pa.last_submission_date  desc OFFSET :page LIMIT :size", nativeQuery = true)
    List<ProponentApplicationWithSector> getECProposalList(@Param("user_id") Integer user_id, @Param("status") List<String> status, @Param("page") Integer page, @Param("size") Integer size);

    @Modifying
    @Query(value = " update master.proponent_applications set last_status= coalesce(:status, last_status) , last_remarks=coalesce(:remarks,last_remarks) where proposal_no =:proposalNo ", nativeQuery = true)
    Integer updateProposal(@Param("proposalNo") String proposalNo, @Param("status") String status, @Param("remarks") String remarks);

    @Query(value = "select pa.proposal_no  from master.proponent_applications pa where pa.proposal_id= ?1 ", nativeQuery = true)
    String getProposalNo(Integer proposalNo);

    @Query(value = " select pa.moefcc_file_number from master.proponent_applications pa where pa.proposal_id=?1 ",
            nativeQuery = true)
    String getMoefccFileNoEcC(Integer id);

    @Modifying
    @Query(value = " update master.proponent_applications set moefcc_file_number=:file where id =:id ", nativeQuery = true)
    Integer updateFileNo(@Param("id") Integer id, @Param("file") String file);

    @Query(value = "Select cd.proposal_for,pd.project_name,pa.moefcc_file_number from master.caf_details cd join master.proponent_applications pa On cd.id=pa.caf_id "
            + "join master.project_details pd on pd.id = pa.project_id where pa.proposal_id = :proposal_id", nativeQuery = true)
    String getOtherPropertyValues(@Param("proposal_id") Integer proposal_id);

    @Query("Select p from ProponentApplications p where p.proposal_no=?1")
    public Optional<ProponentApplications> getApplicationByProposalNo1(String proposal);

    @Query(value = "select * from master.fc_compliance_general_condition (:applicationId)", nativeQuery = true)
    public List<ComplianceConditionDto> getGeneralCondition(@Param(value = "applicationId") Integer applicationId);

    @Query(value = "select * from master.fc_compliance_standard_condition (:applicationId)", nativeQuery = true)
    public List<ComplianceConditionDto> getStandardCondition(@Param(value = "applicationId") Integer applicationId);

    @Query(value = "select * from master.fc_compliance_specific_condition (:applicationId)", nativeQuery = true)
    public List<ComplianceConditionDto> getSpecificCondition(@Param(value = "applicationId") Integer applicationId);

    @Query(value = " select * from master.get_application_division(:application_id) ", nativeQuery = true)
    List<ApplicationDivisionDto> getApplicationDivision(Integer application_id);

    @Query(value = "select * from master.all_proposal_not_stage_2(:user)", nativeQuery = true)
    List<Stage1CompletedProposal> getProposalList(@Param(value = "user") Integer user);

    @Query(value = "select * from master.add_committee_member_fnc(:address,:districtCode,:emailId,:mobileNumber,:name,:password,:pin,:userType," +
            ":securityAnswer,:securityQuestion,:selectedSector,:selectedOffice,:selectedRole,:stateId,:ipAddress,:designationId,:officeId," +
            ":committeeType,:sectorId,:workgroupId,:entityId)", nativeQuery = true)
    String addCommittee(@Param(value = "address") String address, @Param(value = "districtCode") String districtCode, @Param(value = "emailId") String emailId,
                        @Param(value = "mobileNumber") String mobileNumber, @Param(value = "name") String name, @Param(value = "password") String password,
                        @Param(value = "pin") String pin, @Param(value = "userType") String userType, @Param(value = "securityAnswer") String securityAnswer,
                        @Param(value = "securityQuestion") String securityQuestion, @Param(value = "selectedSector") Integer selectedSector, @Param(value = "selectedOffice") Integer selectedOffice,
                        @Param(value = "selectedRole") Integer selectedRole, @Param(value = "stateId") Integer stateId, @Param(value = "ipAddress") String ipAddress,
                        @Param(value = "designationId") Integer designationId, @Param(value = "officeId") Integer officeId, @Param(value = "committeeType") String committeeType,
                        @Param(value = "sectorId") Integer sectorId, @Param(value = "workgroupId") Integer workgroupId, @Param(value = "entityId") Integer entityId);

    @Query(value = "select * from master.get_list_committee_member_fnc(:type, :id)", nativeQuery = true)
    List<CommitteeListDto> getCommitteeList(@Param(value = "type") String committeeType, @Param(value = "id") Integer id);

    @Query(value = "select * from master.delete_committee_member_fnc(:id)", nativeQuery = true)
    String deleteCommittee(@Param(value = "id") Integer id);


    @Query(value = "select * from master.update_proposal_duration_fnc()", nativeQuery = true)
    Boolean updateProposalDuration();

    @Query(value = " select  cast(master.copy_fc_forma_amendment_fnc(:proposal_id) as Integer)  ; ", nativeQuery = true)
    public Integer getProponentAppIdFcFormA(@Param("proposal_id") Integer proposal_id);

    @Modifying
    @Query(value = "update authentication.user_entity set selected_sector =:selectedSector where entityid=:entityId", nativeQuery = true)
    Integer updateSelectedSector(@Param(value = "entityId") Integer entityId, @Param(value = "selectedSector") Integer selectedSector);

    @Query(value = " select  cast(master.copy_fc_formb_amendment_fnc(:proposal_id) as Integer)  ; ", nativeQuery = true)
    public Integer getProponentAppIdFcFormB(@Param("proposal_id") Integer proposal_id);

    @Query(value = "select * from master.fetch_certificate_details(:applicationId,:type)", nativeQuery = true)
    FcCertificateConditionDto getConditions(@Param(value = "applicationId") Integer applicationId, @Param(value = "type") String type);

    @Query(value = "select * from master.proponent_applications pa where pa.clearance_id in (1,7,8,9,12) and pa.other_property isnull order by id limit 50",nativeQuery = true)
    List<ProponentApplications> getApplicationWithNullOtherProperty();

    @Query(value = "select * from master.project_proposal_details(:applicationId)",nativeQuery = true)
    List<ProjectProposalDetailsDto> getProjectProposalDetails(@Param(value = "applicationId") Integer applicationId);

    @Query(value = " select  cast(master.copy_fc_formc_amendment_fnc(:proposal_id) as Integer)", nativeQuery = true)
    public Integer getProponentAppIdFcFormC(@Param("proposal_id") Integer proposal_id);

    @Query(value = " select  cast(master.copy_fc_formd_amendment_fnc(:proposal_id) as Integer)", nativeQuery = true)
    public Integer getProponentAppIdFcFormD(@Param("proposal_id") Integer proposal_id);

    @Query(value = " select  cast(master.copy_fc_forme_amendment_fnc(:proposal_id) as Integer)", nativeQuery = true)
    public Integer getProponentAppIdFcFormE(@Param("proposal_id") Integer proposal_id);
}
