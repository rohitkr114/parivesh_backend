package com.backend.repository.postgres.ForestClearanceFormE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceE.FcFormEApprovalDetails;
import com.backend.model.ForestClearanceE.FcFormEPriorProposal;

public interface FcFormEApprovalDetailsRepository extends JpaRepository<FcFormEApprovalDetails, Integer> {

	@Query("select new FcFormEApprovalDetails(id) from FcFormEApprovalDetails where fc_form_e_id=?1")
	List<FcFormEApprovalDetails> getDataByFcFormEId(Integer id);

//	@Query("Select pa from FcFormEApprovalDetails pa where pa.fcFormEKmls.id=?1 and pa.is_delete='false'")
//	List<FcFormEApprovalDetails> getByFcID(Integer id);

	@Modifying
	@Query("update FcFormEApprovalDetails set is_delete='true' where id=?1")
	Integer deleteApprovalDetails(Integer fc_form_e_id);
}
