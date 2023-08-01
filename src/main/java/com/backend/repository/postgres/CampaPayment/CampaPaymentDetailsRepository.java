package com.backend.repository.postgres.CampaPayment;

import com.backend.dto.CampaDashboardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CampaPayment.CampaPaymentDetails;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CampaPaymentDetailsRepository extends JpaRepository<CampaPaymentDetails,Integer> {
	
	@Query("select cp from CampaPaymentDetails as cp where cp.id=?1")
	public CampaPaymentDetails getDetailsByCampaPaymentId(Integer id);

	@Query(value = "select * from authority.campa_payment_details cpd where cpd.fc_id =:fcId order by id desc limit 1",nativeQuery = true)
	Optional<CampaPaymentDetails> getByFcId(@Param(value = "fcId") Integer fcId);

	@Query("select cp from CampaPaymentDetails as cp where cp.fc_id=?1")
	List<CampaPaymentDetails> getDetailsByFcId(Integer proposalId);

	@Query(value = "select * from master.campa_dashboard_payment_details(:userId)",nativeQuery = true)
	public List<CampaDashboardDto> getCampaDashboardDetails(@Param(value = "userId") Integer userId);
}
