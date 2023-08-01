package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.backend.model.ForestClearancePatchKmls;

public interface ForestClearancePatchKmlsRepository extends JpaRepository<ForestClearancePatchKmls, Integer>  {
	
	@Query("SELECT fc from ForestClearancePatchKmls fc where fc.is_deleted ='false' and fc.forestClearance.id=?1")
	List<ForestClearancePatchKmls> findByFCID(Integer id);
}
