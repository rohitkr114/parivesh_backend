package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcProjectImplementationDetails;


public interface EcProjectImplementationDetailsRepository  extends JpaRepository<EcProjectImplementationDetails, Integer>  {

	@Query("select ec from EcProjectImplementationDetails ec where ec.isDelete = 'false' and ec.environmentClearence.id=?1")
	public List<EcProjectImplementationDetails> findDetailByEcId(Integer id);


}
