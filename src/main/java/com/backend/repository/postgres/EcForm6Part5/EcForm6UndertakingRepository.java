package com.backend.repository.postgres.EcForm6Part5;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcForm6Part5.EcForm6Undertaking;

public interface EcForm6UndertakingRepository extends JpaRepository<EcForm6Undertaking, Integer> {

//	@Query(" select new EcForm6Undertaking( id, i_agree,"
//			+ "undertaking_person_name,undertaking_person_designation,"
//			+ "undertaking_person_company,undertaking_person_address,"
//			+ "undertaking_person_esign,undertaking_date"
//			+ ") from EcForm6Undertaking where ecForm6BasicDetails.id=?1")
	//@Query(value=" select id, i_agree,undertaking_person_name,undertaking_person_designation,undertaking_person_company,undertaking_person_address,undertaking_person_esign,undertaking_date from master.ec_form6_undertaking where ec_form6_impl_basic_detail_id=?1",nativeQuery = true)
	@Query(value=" select * from master.ec_form6_undertaking where ec_form6_impl_basic_detail_id=?1",nativeQuery = true)
	public  Optional<EcForm6Undertaking> getRecordExist(Integer id);
	
	
	// view
	@Query(value=" select * from master.ec_form6_undertaking ec where ec.ec_form6_impl_basic_detail_id=?1",nativeQuery = true)
	public  EcForm6Undertaking getUndertaking(Integer ecId);

}
