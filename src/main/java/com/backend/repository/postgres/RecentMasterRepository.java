package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.HomeRecentMaster;

public interface RecentMasterRepository extends JpaRepository<HomeRecentMaster, Integer> {
	
	@Query("SELECT h FROM HomeRecentMaster h where h.enabled=cast(?1 as boolean) ORDER BY h.order_no ASC")
	List<HomeRecentMaster> get_all_recent(String active);

	@Query("SELECT h FROM HomeRecentMaster h ORDER BY h.order_no ASC")
	List<HomeRecentMaster> get_all_Recents();
}
