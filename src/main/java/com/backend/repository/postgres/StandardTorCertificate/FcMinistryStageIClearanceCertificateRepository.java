package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.FcIROStageIClearanceCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.StandardTORCertificate.FcMinistryStageIClearanceCertificate;

@Repository
public interface FcMinistryStageIClearanceCertificateRepository extends JpaRepository<FcMinistryStageIClearanceCertificate,Integer> {

    @Query(value = "select st from FcMinistryStageIClearanceCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    FcMinistryStageIClearanceCertificate getFcMinistryStageIClearanceCertificateInfoBytPropId (Integer proponent_id, String proposalNo);

    @Query(value = "select * from master.fc_Ministry_stagei_clearance_certificate st where st.proponent_id=:proponentId and st.is_active = true and st.proposal_no=:proposalNo order by id desc limit 1 ",nativeQuery = true)
    FcMinistryStageIClearanceCertificate getFcMinistryStageIClearanceCertificateInfoByPropId (@Param(value = "proponentId") Integer proponentId, @Param(value = "proposalNo") String proposalNo);
    
}
