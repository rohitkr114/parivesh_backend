package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCProposedLandN;

public interface FcFormCProposedLandNRepository extends JpaRepository<FcFormCProposedLandN, Integer>{
	@Query(" select new FcFormCProposedLandN( id,"
			+ "is_proposal_seeking_prior_approval, status_of_proposal,proposal_no ) from FcFormCProposedLandN where fc_form_c_id=?1 ")
	FcFormCProposedLandN getFcFormCProposedLandById(Integer id);

	@Query("select new FcFormCProposedLandN(id) from FcFormCProposedLandN where fc_form_c_id=?1")
	FcFormCProposedLandN getDataByFcFormCId(Integer id);
}
