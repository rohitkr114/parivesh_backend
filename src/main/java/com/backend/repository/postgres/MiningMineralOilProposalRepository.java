package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.MiningMineralOilProposal;

public interface MiningMineralOilProposalRepository extends JpaRepository<MiningMineralOilProposal, Integer>{

	@Modifying
	@Query(value = " Update master.mining_mineral_oil_reserves set is_deleted=true where id=?1 ", nativeQuery = true)
	Integer deleteReserve(Integer id);
	
	@Modifying
	@Query(value = " Update master.mining_mineral_oil_production_detail set is_deleted=true where id=?1 ", nativeQuery = true)
	Integer deleteProductionDetail(Integer id);
	
	@Modifying
	@Query(value = " Update master.mining_mineral_oil_extracted set is_deleted=true where id=?1 ", nativeQuery = true)
	Integer deleteExtracted(Integer id);
	
	@Modifying
	@Query(value = " Update master.mining_mineral_oil_estimated_reserve set is_deleted=true where id=?1 ", nativeQuery = true)
	Integer deleteEstimatedReserve(Integer id);

	@Query("select mm from MiningMineralOilProposal mm where mm.forestClearance.id =?1 ")
	MiningMineralOilProposal getByFc(Integer id);

}
