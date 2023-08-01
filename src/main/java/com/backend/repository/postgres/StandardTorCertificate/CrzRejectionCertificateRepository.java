package com.backend.repository.postgres.StandardTorCertificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.StandardTORCertificate.CrzRejectionCertificate;

public interface CrzRejectionCertificateRepository extends JpaRepository<CrzRejectionCertificate, Integer> {

	@Query(value = "select * from master.crz_rejection_certificate st where st.proponent_id=?1 and st.is_active = true and proposal_no=?2  order by id desc limit 1", nativeQuery = true)
	CrzRejectionCertificate getStandardCertificateInfoBytPropId(int propId, String proposalNo);

}
