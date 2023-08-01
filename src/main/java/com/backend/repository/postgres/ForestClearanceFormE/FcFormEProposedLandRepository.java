package com.backend.repository.postgres.ForestClearanceFormE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceE.FcFormEPriorProposal;
import com.backend.model.ForestClearanceE.FcFormEProposedLand;

public interface FcFormEProposedLandRepository extends JpaRepository<FcFormEProposedLand, Integer>{

	@Query("select new FcFormEProposedLand(id) from FcFormEProposedLand where fc_form_e_id=?1")
	FcFormEProposedLand getDataByFcFormEId(Integer id);

	@Query("Select pa from FcFormEProposedLand pa where pa.fcFormE.id=?1 and pa.is_delete='false'")
	FcFormEProposedLand getByFcID(Integer id); 
}
