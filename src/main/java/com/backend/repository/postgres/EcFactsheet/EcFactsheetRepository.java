package com.backend.repository.postgres.EcFactsheet;

import java.util.List;
import java.util.Optional;

import com.backend.dto.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcFactsheet.EcFactsheet;

public interface EcFactsheetRepository extends JpaRepository<EcFactsheet,Integer> {
	
	@Query("Select ecf from EcFactsheet ecf where ecf.id=?1 ")
	public EcFactsheet getDetailsByFactsheetId(Integer id);
	
	@Query("Select ecf from EcFactsheet ecf where ecf.id=?1")
	public Optional<EcFactsheet> getFormById(Integer id);

	@Query("Select emp_cost_capital from EcFactsheet ecf where ecf.ec_id=?1")
	public Double getEmpCostByEcId(Integer id);

	@Query(value = "Select * from master.seac_ms_chairman_dtls(?1,?2)",nativeQuery = true)
	AuthorityName getAuthorityDetails(Integer proposalId, String roleId);
}
