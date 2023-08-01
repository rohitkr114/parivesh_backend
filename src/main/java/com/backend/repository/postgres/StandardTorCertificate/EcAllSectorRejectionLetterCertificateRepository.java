package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcAllSectorRejectionLetterCertificate;
import com.backend.model.StandardTORCertificate.EcAmendmentCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcAllSectorRejectionLetterCertificateRepository extends JpaRepository<EcAllSectorRejectionLetterCertificate,Integer> {

    @Query(value = "select st from EcAllSectorRejectionLetterCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcAllSectorRejectionLetterCertificate ecAllSectorRejectionLetterCertificateInfoBytPropId (Integer proponent_id, String proposalNo);

}
