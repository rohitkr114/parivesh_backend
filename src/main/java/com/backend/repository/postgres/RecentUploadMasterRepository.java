package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.HomeRecentUploadsMaster;

@Repository
public interface RecentUploadMasterRepository extends JpaRepository<HomeRecentUploadsMaster, Integer>{

	@Query("SELECT h FROM HomeRecentUploadsMaster h where h.enabled=cast(?1 as boolean) ORDER BY h.order_no ASC")
	List<HomeRecentUploadsMaster> get_all_upload(String active);
	
	@Query("SELECT h FROM HomeRecentUploadsMaster h ORDER BY h.order_no ASC")
	List<HomeRecentUploadsMaster> get_all_uploads();
}
