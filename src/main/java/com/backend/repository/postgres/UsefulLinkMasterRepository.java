package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.HomeUsefulLinkMaster;

@Repository
public interface UsefulLinkMasterRepository extends JpaRepository<HomeUsefulLinkMaster, Integer>{

	@Query("SELECT h FROM HomeUsefulLinkMaster h where h.enabled=cast(?1 as boolean) ORDER BY h.order_no ASC")
	List<HomeUsefulLinkMaster> get_all_link(String active);
	
	@Query("SELECT h FROM HomeUsefulLinkMaster h ORDER BY h.order_no ASC")
	List<HomeUsefulLinkMaster> get_all_links();
}
