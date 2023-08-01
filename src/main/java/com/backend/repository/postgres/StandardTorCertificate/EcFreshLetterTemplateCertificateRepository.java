package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcFreshLetterTemplateCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcFreshLetterTemplateCertificateRepository extends JpaRepository<EcFreshLetterTemplateCertificate,Integer> {

    @Query(value = "select st.* from master.ec_fresh_letter_template_certificate st where st.proponent_id=?1  and st.proposal_no=?2 and st.is_active = true order by id desc limit 1",nativeQuery = true)
    EcFreshLetterTemplateCertificate getFreshLetterTemplateInfoBytPropId (Integer proponent_id, String proposalNo);

    @Query(value = "select epod.identification_no  from master.ec_partc_other_details epod left join master.ec_partc ep on epod.ec_id = ep.id where ep.proposal_no = ?1", nativeQuery = true)
	String getIdentificationNo(String proposal_no);
}
