package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.Employee;
import com.backend.model.ForestClearance;
import com.backend.model.ForestClearanceComponentDetail;

public interface ForestClearanceComponentDetailRepository extends JpaRepository<ForestClearanceComponentDetail, Integer>  {

	@Query("select fc from ForestClearanceComponentDetail fc where fc.isDelete = 'false' and fc.forestClearance.id=?1")
	public List<ForestClearanceComponentDetail> findDetailByFcId(Integer id);
}

