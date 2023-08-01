package com.backend.repository.postgres.ForestClearanceFormD;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.backend.model.ForestClearanceFormD.FcFormD;
import org.springframework.data.repository.query.Param;

public interface FcFormDRepository extends JpaRepository<FcFormD, Integer> {
	
	@Query("select fc from FcFormD fc where fc.id=?1")
	public Optional<FcFormD> getFcFormDById(Integer id);


	@Query("select fc from FcFormD fc where fc.id=?1")
	public FcFormD getFormDDetailsById(Integer id);
	
	@Query("select new FcFormD(fc.id, fc.project_category, "
			+ " fc.state_code,fc.state, "
			+ " fc.proposal_justification, "
			+ "	fc.proposal_no ) from FcFormD fc where fc.id=?1 ")
	public Optional<FcFormD> getFcFormDDetailsById(Integer id);

	@Modifying
	@Query(value = "Update master.fc_form_d set proposal_no =:proposalNo where id =:id", nativeQuery = true)
	public Integer updateFcFormDProposal(@Param(value = "id") Integer id, @Param(value = "proposalNo") String proposalNo);
}
