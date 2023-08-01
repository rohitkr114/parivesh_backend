package com.backend.repository.postgres.FcFormB;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBPaymentDetails;

public interface FcFormBPaymentDetailsRepository extends JpaRepository<FcFormBPaymentDetails, Integer>{

	@Query("Select new FcFormBPaymentDetails(fc.id, "
			+ "fc.proposal_no, "
			+ "fc.moef_file_no, "
			+ "fc.item_nature, "
			+ "fc.amount_paid, "
			+ "fc.payment_date) from FcFormBPaymentDetails fc where fc.fcFormBProjectDetails.id=?1 and fc.is_deleted='false'")
	List<FcFormBPaymentDetails> getByFcID(Integer id);

	@Modifying
	@Query("update FcFormBPaymentDetails set is_deleted='true' where id=?1")
	Integer updateFcFormBById(int id);
}
