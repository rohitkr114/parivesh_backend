package com.backend.repository.postgres.StandardTorCertificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.StandardTORCertificate.CrzGrantCertificate;

@Repository
public interface CrzGrantCertificateRepository extends JpaRepository<CrzGrantCertificate, Integer> {

	@Query(value = "select * from master.crz_grant_certificate st where st.proponent_id=?1 and st.is_active = true and proposal_no=?2  order by id desc limit 1", nativeQuery = true)
	CrzGrantCertificate getStandardCertificateInfoBytPropId(int propId, String proposalNo);

}
