package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormC;
import org.springframework.data.repository.query.Param;

public interface FcFormCRepository extends JpaRepository<FcFormC, Integer>{

	
	@Query("select new FcFormC(fc.id) from FcFormC fc where fc.id=?1")
	public FcFormC getFcFormcById(Integer id);

	@Query("select fc from FcFormC fc where fc.id=?1")
	public FcFormC getDetailsByFormCId(Integer id);
	
	
	@Query("select new FcFormC(fc.id,fc.state,fc.is_project_falls_within_protected_area,"
			+ "fc.total_prospecting_lease_area,fc.grant_prospecting_lease_date,"
			+ "fc.is_proposal_seeking_prior_approval,fc.status_of_proposal,"
			+ "fc.proposal_no,fc.non_forest_proposed_exploration_area,fc.forest_proposed_exploration_area) from FcFormC fc where fc.id=?1")
	public Optional<FcFormC> getFcFormCDetailsById(Integer id);
	
	@Query(value = " select id,state,is_project_falls_within_protected_area,"
			+ "total_prospecting_lease_area,grant_prospecting_lease_date,is_proposal_seeking_prior_approval,status_of_proposal,"
			+ "proposal_no,non_forest_proposed_exploration_area,forest_proposed_exploration_area,caf_id,created_by,created_on,updated_by,updated_on,letter_of_intent_id,vers,is_active,is_deleted"
				 + " from master.fc_form_c where id=?1 ", nativeQuery = true)
	public Optional<FcFormC> getCAFByFcFormCId(Integer id);
	
	
	@Query(value = " select letter_of_intent_id from master.fc_form_c where id=?1 ", nativeQuery = true)
	public List<Object[]> getDocuments(Integer id);

	@Modifying
	@Query(value = "Update master.fc_form_c set proposal_no =:proposalNo where id =:id", nativeQuery = true)
	public Integer updateFcFormCProposal(@Param(value = "id") Integer id,@Param(value = "proposalNo") String proposalNo);
	
	
	
	/*
	@Query("select new FcFormC(fc.id,fc.state,fc.is_project_falls_within_protected_area,"
			+ "fc.total_prospecting_lease_area,fc.grant_prospecting_lease_date,"
			+ "fc.total_proposed_diversion_period,fc.legal_status_of_forest_land_other,fc.total_non_forest_land,fc.total_area_of_kmls,fc.legal_status_forest_land_area"
			+ ",fc.non_forest_proposed_exploration_area,fc.forest_proposed_exploration_area,proposal_no) from FcFormC fc where fc.id=?1")
	public Optional<FcFormC> getFcFormCDetailsById(Integer id);
	
	@Query(value = " select id,state,is_project_falls_within_protected_area,"
			+ "total_prospecting_lease_area,grant_prospecting_lease_date,total_proposed_diversion_period,legal_status_of_forest_land_other,total_non_forest_land,total_area_of_kmls,legal_status_forest_land_area"
			+ ",non_forest_proposed_exploration_area,forest_proposed_exploration_area,proposal_no,caf_id,created_by,created_on,updated_by,updated_on,letter_of_intent_id,vers,is_active,is_deleted"
				 + " from master.fc_form_c where id=?1 ", nativeQuery = true)
	public Optional<FcFormC> getCAFByFcFormCId(Integer id);
	*/
}
