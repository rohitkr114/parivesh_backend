package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcTorTransferTemplate; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcTorTransferTemplateRepository extends JpaRepository<EcTorTransferTemplate,Integer> {
    @Query(value="select st.* from master.ec_tor_transfer_template st where st.proponent_id=?1 and st.is_active = true and st.proposal_no=?2",nativeQuery = true)
    EcTorTransferTemplate getTorTransferTemplateInfoBytPropId (Integer proponent_id, String proposalNo);

	@Query(value = "select efu.identification_no from master.ec_form8_undertaking efu left join master.ec_form8_transfer_of_tor eftot  on efu.ec_form_8_id  = eftot.id where eftot.proposal_no =   ?1", nativeQuery = true)
	String getIdentificationNo(String proposal_no);
}
