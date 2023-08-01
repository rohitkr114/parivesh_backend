package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcPartC.EcOtherDetails;

public interface EcOtherDetailsRepository extends JpaRepository<EcOtherDetails, Integer> {

	@Query("select new EcOtherDetails(eco.id, eco.environment_mgmt_funds, eco.corporate_environment_funds,"
			+ "	eco.environment_mgmt_plan_funds, eco.total_capital_cost, eco.total_recurring_cost,"
			+ "	eco.land_acquisition_status, eco.acquired_land, eco.land_to_be_acquired,"
			+ "	eco.is_environmental_cell_proposed, eco.env_organisational_structure,"
			+ "	eco.details_of_responsibilities, eco.procedure_to_report, eco.env_cell_reason,"
			+ "	eco.procedure_to_review, eco.is_compliance_report, eco.compliance_date_of_site,"
			+ "	eco.compliance_final_observation, eco.compliance_reason) from EcOtherDetails eco where eco.ecPartC.id=?1")
	public Optional<EcOtherDetails> getDataByEcId(Integer id);
	
	@Query(value = "select ec.compliance_report_id from master.ec_partc_other_details ec where ec.id=?1", nativeQuery = true)
	public List<Object[]> getDocuments(Integer id);
	
	@Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
	public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
	
	@Query(value = "select pa.proposal_no  from master.proponent_applications pa left join master.ec_partc_other_details epod on epod.ec_id = pa.proposal_id where epod.ec_id = ?1 ", nativeQuery = true)
	public String getProposalNo(Integer ec_id);
}
