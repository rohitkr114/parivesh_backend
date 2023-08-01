package com.backend.repository.postgres.EcForm6Part2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.EcForm6Part2.EcForm6Productdetails;

@Repository
@Transactional
public interface EcForm6ProductdetailsRepository extends JpaRepository<EcForm6Productdetails, Integer> {

	@Query(value = "select ec.proposal_no from master.ec_form6_impl_product_detail ec where ec.id=?1", nativeQuery = true)
	public String getProposalNo(Integer id);

	@Modifying(clearAutomatically = true)
	@Query("update EcForm6Productdetails ec set ec.is_delete=true , ec.is_active=false where ec.id=?1")
	Integer delete(Integer id);
	
	// changed here
	@Query(value = "Select * from master.ec_form6_impl_product_detail ec where ec.ec_form6_basic_detail_id=?1", nativeQuery = true)
	public EcForm6Productdetails getProductDetailsById(Integer ecId);

}
