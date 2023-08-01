package com.backend.repository.postgres.CampaPayment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CampaPayment.CampaPaymentOtherCharges;
import org.springframework.data.repository.query.Param;

public interface CampaPaymentOtherChargesRepository extends JpaRepository<CampaPaymentOtherCharges,Integer> {
	
	@Query("select cpoc from CampaPaymentOtherCharges as cpoc where cpoc.id=?1")
	public Optional<CampaPaymentOtherCharges> getDataById(Integer id);

	@Query(value = "select * from authority.campa_payment_other_charges cpoc where cpoc.campa_payment_id =:campaId and cpoc.is_active =true and cpoc.is_deleted =false",nativeQuery = true)
	List<CampaPaymentOtherCharges> getOtherChargesList(@Param(value = "campaId") Integer campaId);
}
