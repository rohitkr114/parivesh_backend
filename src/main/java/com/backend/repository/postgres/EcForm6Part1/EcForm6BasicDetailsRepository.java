package com.backend.repository.postgres.EcForm6Part1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcForm6Part1.EcForm6BasicDetails;

public interface EcForm6BasicDetailsRepository extends JpaRepository<EcForm6BasicDetails, Integer> {
	
	@Query(value = "select ec.proposal_no from master.ec_form6_basic_detail ec where ec.id=?1", nativeQuery = true)
	public String getProposalNo(Integer id);
	
	@Query("Select ec from EcForm6BasicDetails ec where ec.id=?1")
	public Optional<EcForm6BasicDetails> getFormById(Integer id);

	@Query("Select ec from EcForm6BasicDetails ec where ec.id=?1")
	public EcForm6BasicDetails getBasicDetailsById(Integer ecId);
}
