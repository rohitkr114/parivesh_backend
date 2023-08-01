package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcSummaryAllocations;

public interface EcSummaryAllocationsRepository extends JpaRepository<EcSummaryAllocations, Integer> {

	@Modifying
	@Query("update EcSummaryAllocations set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateSummaryAllocation(Integer id);

	@Query("select new EcSummaryAllocations(ecb.id, ecb.emp, ecb.capital_cost, ecb.recurring_cost) from EcSummaryAllocations ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcSummaryAllocations> getDataByEcId(Integer id);

	@Query(value ="select * from master.ec_summary_allocations ecb where ecb.summary_allocation_id=?1 and ecb.is_deleted='false'",nativeQuery = true)
	public Set<EcSummaryAllocations> getDataByFormId(Integer id);
}
