package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceMaps;


public interface ForestClearanceMapsRepository extends JpaRepository<ForestClearanceMaps, Integer> {

	@Query("SELECT fc from ForestClearanceMaps fc where fc.isDelete ='false' and fc.forestClearance.id=?1")
	List<ForestClearanceMaps> findByFCID(Integer id);

}
