package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.HomeUpdateMaster;

public interface UpdateMasterRepository extends JpaRepository<HomeUpdateMaster, Integer> {

	@Query("SELECT h FROM HomeUpdateMaster h where h.enabled=cast(?1 as boolean) ORDER BY h.order_no ASC")
	List<HomeUpdateMaster> get_all_update(String active);
	
	@Query("SELECT h FROM HomeUpdateMaster h ORDER BY h.order_no ASC")
	List<HomeUpdateMaster> get_all_updates();

}
