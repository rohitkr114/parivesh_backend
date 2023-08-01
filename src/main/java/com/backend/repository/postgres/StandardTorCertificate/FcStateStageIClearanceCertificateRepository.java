package com.backend.repository.postgres.StandardTorCertificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.StandardTORCertificate.FcStateStageIClearanceCertificate;

@Repository
public interface FcStateStageIClearanceCertificateRepository extends JpaRepository<FcStateStageIClearanceCertificate,Integer> {

    @Query(value = "select * from master.fc_state_stagei_clearance_certificate st where st.proponent_id=?1 and st.proposal_no=?2 and st.is_active = true order by id desc limit 1",nativeQuery = true)
    FcStateStageIClearanceCertificate getFcStateStageIClearanceCertificateInfoBytPropId (Integer proponent_id, String proposalNo);

}
