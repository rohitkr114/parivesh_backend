package com.backend.repository.postgres.ForestClearanceFormE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceE.FcFormEPriorProposal;
import com.backend.model.ForestClearanceFormB.FcFormBMiningProposals;

public interface FcFormEPriorProposalRepository extends JpaRepository<FcFormEPriorProposal, Integer> {

	@Query("select new FcFormEPriorProposal(id) from FcFormEPriorProposal where fc_form_e_id=?1")
	List<FcFormEPriorProposal> getDataByFcFormEId(Integer id);

	@Query("Select pa from FcFormEPriorProposal pa where pa.fcFormE.id=?1 and pa.is_delete='false'")
	List<FcFormEPriorProposal> getByFcID(Integer id);

	@Modifying
	@Query("update FcFormEPriorProposal set is_delete='true' where id=?1")
	Integer deletePriorProposals(Integer id);
}
