package com.backend.repository.postgres.EcForm9;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcForm9.EcForm9Basicdetails;

public interface EcForm9BasicdetailsRepository extends JpaRepository<EcForm9Basicdetails, Integer> {

//	@Query("select new EcForm9Basicdetails( ec.id, ec.proposal_for, ec.proposal_catg, ec.moefcc_file_no,"
//			+ "	ec.date_of_ec_issue, ec.is_ec_issue_under_eia, ec.mine_lease_area,"
//			+ "	ec.mine_production_capacity, ec.kml, ec.vesting_ref_no, ec.lease_allocation_date,"
//			+ "	ec.lease_area, ec.lease_validity_end_date, ec.vesting_production_capacity,"
//			+ "	ec.vesting_copy, ec.proposal_no, ec.is_active, ec.is_delete) from EcForm9Basicdetails ec where ec.id=?1")
//	public Optional<EcForm9Basicdetails> findByFormId(Integer id);

	@Query(value = "select ec.proposal_no from master.ec_form_9 ec where ec.id=?1", nativeQuery = true)
	public String getProposalNo(Integer id);

	@Query(value = "select ec.caf_id from master.ec_form_9 ec where ec.id=?1", nativeQuery = true)
	public List<Object[]> getCafByEcId(Integer id);
}
