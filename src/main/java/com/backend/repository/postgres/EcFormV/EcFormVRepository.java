package com.backend.repository.postgres.EcFormV;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcFormVModel.EcFormV;

public interface EcFormVRepository extends JpaRepository<EcFormV, Integer> {

	@Query("select new EcFormV( ec.id, ec.nature_of_tor, ec.date_of_issue_tor, ec.date_of_issue_additional_tor, ec.moef_file_no,"
			+ " ec.is_any_amendment_tor, ec.date_of_issue_amendment_tor, ec.amendment_details,"
			+ " ec.proposal_no, ec.public_hearing_number, ec.is_active, ec.is_delete ) from EcFormV ec where ec.id=?1")
	public Optional<EcFormV> findByFormId(Integer id);
	
	@Query(value = "select ec.caf_id from master.ec_form_v ec where ec.id=?1", nativeQuery = true)
	public List<Object[]> getCafByEcId(Integer id);
}
