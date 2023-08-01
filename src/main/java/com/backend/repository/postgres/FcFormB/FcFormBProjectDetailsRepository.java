package com.backend.repository.postgres.FcFormB;

import com.backend.model.ForestClearanceFormB.FcFormBProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FcFormBProjectDetailsRepository extends JpaRepository<FcFormBProjectDetails, Integer> {

    @Query("select new FcFormBProjectDetails("
            + "fc.id,"
            + "fc.fc_id,"
            + "fc.state_code,"
            + "fc.project_activity_id_other,"
            + "fc.project_category_code,"
            + "fc.project_category_id,"
            + "fc.proposal_no) from FcFormBProjectDetails fc where fc.id=?1")
    public FcFormBProjectDetails getFormById(Integer id);

    @Query("select fc from FcFormBProjectDetails fc where fc.id=?1")
    public FcFormBProjectDetails getDetailsByFcId2(Integer id);

    @Query("select new FcFormBProjectDetails(fc.id,fc.project_category_code) from FcFormBProjectDetails fc where fc.id=?1")
    public FcFormBProjectDetails getDetailsByFcId(Integer id);

    @Query("select new FcFormBProjectDetails(fc.id,fc.proposal_no) from FcFormBProjectDetails fc where fc.id=?1")
    public FcFormBProjectDetails getDetailsByFcIdProp(Integer id);

    @Query(value = " select letter_of_intent_id from master.fc_form_c where id=?1 ", nativeQuery = true)
    public List<Object[]> getDocuments(Integer id);

    @Modifying
    @Query(value = "Update master.fc_form_b set proposal_no =?2 where id =?1", nativeQuery = true)
    public Integer updateFcFormAProposal(Integer fc_fromB_id_new, String proposal_no);
}
