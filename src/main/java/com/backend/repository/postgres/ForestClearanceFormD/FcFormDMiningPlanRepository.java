package com.backend.repository.postgres.ForestClearanceFormD;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormD.FcFormDMiningPlan;

public interface FcFormDMiningPlanRepository extends JpaRepository<FcFormDMiningPlan, Integer>{

	@Query(" select new FcFormDMiningPlan( id, is_mining_approved,"
			+ "mining_nature,date_of_approval,"
			+ "approval_authority,life_of_mine,"
			+ "dpr_details"
			+ ") from FcFormDMiningPlan where fc_form_d_id=?1")
	FcFormDMiningPlan getFcFormDMiningPlan(Integer id);

	@Query(value = " select dpr_copy_id,copy_approved_mining_plan_id from master.fc_form_d_mining_plan where fc_form_d_id=?1 ", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);
	
	@Query("select new FcFormDMiningPlan(id) from FcFormDMiningPlan where fc_form_d_id=?1")
	FcFormDMiningPlan getDataByFcFormDId(Integer id);
}
