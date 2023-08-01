package com.backend.repository.postgres.EcForm10Repository;

import com.backend.dto.EcForm10.EcForm10dto;
import com.backend.dto.EcForm9.EcForm9dto;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EcForm10ProjectDetailRepository extends JpaRepository<EcForm10ProjectDetails,Integer> {



    Optional<EcForm10ProjectDetails> getEcForm10NoIncreaseInPlByid(Integer id);

    @Query(value = "select * from master.ec_form10_project_details ec where ec.caf_id=?1", nativeQuery = true)
    public List<EcForm10ProjectDetails> getEcForm10ProjectDetailsByCafId(Integer id);

    @Query("select new EcForm10ProjectDetails( ec.id,"
            + " ec.proposal_no ) from EcForm10ProjectDetails ec where ec.id=?1")
    public Optional<EcForm10ProjectDetails> getFormById(Integer id);

    @Query(value = "select ec.caf_id from master.ec_form10_project_details ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getCafByEcId(Integer id);

    @Query(value = "select ec.id, "
            + " ec.project_sw_no as project_sw_no, "
            + " ec.project_name, "
            + " ec.expansion_under_seven as expansion_under_seven, "
            + " ec.proposal_no, "
            + " ec.nature_of_project as nature_of_project, "
            + " ec.project_proposal_for as project_proposal_for, "
            + "	ec.name as name, "
            + " ec.street as street, "
            + " ec.city as city, "
            + "	ec.district as district, "
            + " ec.state as state, "
            + " ec.pincode as pincode, "
            + " ec.landmark as landmark, "
            + "	ec.email as email, "
            + " ec.landline_no as landline_no, "
            + " ec.mobile_no as mobile_no, "
            + " ec.legal_status as legal_status, "
            + " ec.applicant_name, "
            + "	ec.applicant_designation, "
            + " ec.applicant_street, "
            + " ec.applicant_city, "
            + " ec.applicant_district, "
            + " ec.applicant_state, "
            + " ec.applicant_pincode, "
            + " ec.applicant_landmark, "
            + " ec.applicant_email, "
            + " ec.applicant_landline_no, "
            + "	ec.applicant_mobile_no"
            + " from master.ec_form10_project_details ec where ec.id=?1" , nativeQuery = true)
    public Optional<EcForm10dto> getForm10ByIdDet(Integer id);


    @Query(value = "select ec.caf_id from master.ec_form10_project_details ec where ec.id=?1", nativeQuery = true)
    Integer getCafIdByFormId(Integer id);

}
