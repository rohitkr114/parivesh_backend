package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearanceProjectAreaDetails;



public interface EnvironmentClearanceProjectAreaDetailsRepository extends JpaRepository<EnvironmentClearanceProjectAreaDetails, Integer>  {

	@Query("select ec from EnvironmentClearanceProjectAreaDetails ec where ec.isDelete = 'false' and ec.environmentClearence.id=?1")
	public List<EnvironmentClearanceProjectAreaDetails> findDetailByEcId(Integer id);


}
