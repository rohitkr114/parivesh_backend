package com.backend.repository.postgres.EcPartC;

import com.backend.model.Crz.CrzBasicDetails;
import com.backend.model.EcPartC.EcPartC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EcPartCRepository extends JpaRepository<EcPartC, Integer> {

    @Query("select new EcPartC(ec.id, ec.ec_id, ec.proposal_no,ec.major_activity_id,ec.major_sub_activity_id,ec.project_category,ec.is_proposed_required,ec.is_amendment,ec.parent_id, ec.is_legacy_proposal) from EcPartC ec where ec.id=?1")
    public Optional<EcPartC> getFormById(Integer id);

    @Query("select new EcPartC(ec.id, ec.ec_id, ec.proposal_no, ec.major_activity_id, ec.major_sub_activity_id,"
            + "	ec.project_category,  ec.is_proposed_required,  ec.central_application_reason,"
            + "	central_application_reason_other, date_of_issue_tor, date_of_issue_additional_tor,"
            + "	ec.moef_file_no,  ec.is_any_amendment_tor," + "	ec.date_of_issue_amendment_tor,  ec.amendment_details, "
            + "	ec.is_project_exempted,  ec.project_exempted_reason,  ec.reason_other_field_name,"
            + "	ec.reason_other_field, ec.nature_of_tor, ec.is_for_old_proposal, ec.no_of_written_comments,ec.is_amendment,ec.parent_id,ec.is_legacy_proposal) from EcPartC ec where ec.id=?1")
    public Optional<EcPartC> getStepOneForm(Integer id);

    @Query("select new EcPartC(ec.id, ec.ec_id, ec.proposal_no, ec.major_activity_id, ec.major_sub_activity_id,"
            + "	ec.project_category, ec.is_proposed_required, ec.central_application_reason,"
            + "	ec.central_application_reason_other, ec.amendment_details,ec.is_legacy_proposal, ec.is_for_old_proposal) from EcPartC ec where ec.id=?1")
    public Optional<EcPartC> getStepTwoForm(Integer id);

    @Query(value = "select ec.tor_letter_copy_id,ec.tor_letter_id,ec.action_plan_raised_id,ec.eac_recommendation_id,ec.additional_document_id from master.ec_partC ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getDocuments(Integer id);


    @Query("select ec from EcPartC ec where ec.id=?1")
    public EcPartC getFormPartC(Integer ec_id);

    @Query(value = "select a.name from master.activities a inner join master.ec_partc ep on ep.major_activity_id = a.id where ep.id=:id", nativeQuery = true)
    public String getMajorActivityName(@Param(value = "id") Integer id);

    @Query(value = "select a.item_no from master.activities a inner join master.ec_partc ep on ep.major_activity_id = a.id where ep.id=:id", nativeQuery = true)
    public String getItemNo(@Param(value = "id") Integer id);

    @Modifying
    @Query(value = "Update master.ec_partc set proposal_no=?2 where id=?1", nativeQuery = true)
    public void updateProposal(Integer id, String proposalNo);

    @Query("select crz from CrzBasicDetails crz where crz.id=?1")
	public CrzBasicDetails getBasicDetails(Integer crz_id);

}
