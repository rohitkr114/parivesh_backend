//package com.backend.repository.postgres;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.backend.model.FCProposalDivisionView;
//
//
//public interface FCProposalDivisionViewRepository extends JpaRepository<FCProposalDivisionView, Long> {
//
//
//	@Query(value="SELECT total_forest_land_for_division FROM ua.fc_proposal_division_vw where proposal_id=?1 limit 1",nativeQuery=true)
//	Double findTotalForestLandByProposal_id(int proposal_id);
//}
