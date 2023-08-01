package com.backend.repository.postgres.EcForm6Part3;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.EcForm6Part3.EcForm6EiaConsultantDetails;

@Transactional
@Repository
public interface EcForm6EiaConsultantDetailsRepository extends JpaRepository<EcForm6EiaConsultantDetails, Integer> {

	// This is changed today : id replaced by ec_form6_basic_detail_id
	@Query(value="Select * from master.ec_form6_eia_consultant_details ec where ec.ec_form6_basic_detail_id=?1",nativeQuery = true)
	EcForm6EiaConsultantDetails getConsultantDetailsById(Integer ecId);
}
