package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.HomePageMedia;

public interface HomePageMediaRepository extends JpaRepository<HomePageMedia, Integer>{

	@Query("SELECT h FROM HomePageMedia h where h.enabled=cast(?1 as boolean) ORDER BY h.order_no ASC")
	List<HomePageMedia> get_all_Media(String active);

	@Query("SELECT h FROM HomePageMedia h ORDER BY h.order_no ASC")
	List<HomePageMedia> get_all_Media();
}
