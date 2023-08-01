package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcProdTransportDetails;



public interface EcProdTransportDetailsRepository extends JpaRepository<EcProdTransportDetails, Integer>  {

	@Query("select ec from EcProdTransportDetails ec where ec.isDelete = 'false' and ec.environmentClearence.id=?1")
	public List<EcProdTransportDetails> findDetailByEcId(Integer id);


}

