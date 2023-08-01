package com.backend.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.backend.model.ProjectDetails;

public interface PagingProjectDetailRepository extends PagingAndSortingRepository<ProjectDetails, Integer> {

	@Query("select distinct p from ProjectDetails p left join p.employees pa left join p.consultants con where pa.entityid=?1 or con.entityid=?1 or p.created_by=?1")
	Page<ProjectDetails> findProjectsByUserId(Integer id, Pageable pageable);
	
	@Query("select distinct p from ProjectDetails p left join p.employees pa left join p.consultants con where pa.entityid=?1 or con.entityid=?1 or p.created_by=?1")
	ProjectDetails findProjectsByUserId1(Integer id);
	
	@Query("select distinct p from ProjectDetails p left join p.employees pa left join p.consultants con where p.created_by=?1  or con.entityid=?1 or (p.sw_no LIKE %?2% or p.name LIKE %?2%) and pa.entityid=?1")
	Page<ProjectDetails> findProjectsByUserIdWithSearch(Integer id,String Search,Pageable pageable);
}
