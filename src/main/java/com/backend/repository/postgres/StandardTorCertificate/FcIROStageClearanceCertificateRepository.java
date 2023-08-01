package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.FcIROStageClearanceCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FcIROStageClearanceCertificateRepository extends JpaRepository<FcIROStageClearanceCertificate,Integer> {


    @Query(value = "select st from FcIROStageClearanceCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    FcIROStageClearanceCertificate getFcIROStageClearanceCertificateInfoBytPropId (Integer proponent_id, String proposalNo);

    @Query(value = "select oe.officename from master.proponent_applications pa left join master.fc_proposal_division_vw fpdv on pa.proposal_id = fpdv.proposal_id join ua.office_entity oe on oe.entityid = fpdv.division_id  where pa.id = ?1", nativeQuery = true)
    List<String> getDivision(Integer propId);
    
}
