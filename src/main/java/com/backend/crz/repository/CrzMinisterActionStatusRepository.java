package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.backend.model.Crz.CrzMinisterActionStatus;

@Repository
public interface CrzMinisterActionStatusRepository extends JpaRepository<CrzMinisterActionStatus, Integer>{

	@Query(value = "SELECT * FROM master.crz_minister_action_status order by 1 asc", nativeQuery = true)
	List<CrzMinisterActionStatus> findAllMinisterActionStatus();
	

}
