package com.backend.repository.postgres.ForestClearanceFormD;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormD.FcFormDProposedDiversion;

public interface FcFormDProposedDiversionRepository extends JpaRepository<FcFormDProposedDiversion, Integer> {

	@Query("SELECT fc from FcFormDProposedDiversion fc where fc.is_deleted ='false' and fc.fcFormD.id=?1")
	List<FcFormDProposedDiversion> findByFCFormDID(Integer id);
}
