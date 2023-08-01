package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.CenterDepartments;


@Repository
public interface CenterDepartmentRepository extends JpaRepository<CenterDepartments, Integer>{

	@Query("SELECT c from CenterDepartments c where c.is_active='true' and c.is_deleted='false' order by c.dept_Name")
    List<CenterDepartments> findAllDepartments();
	
	@Query("SELECT c from CenterDepartments c where c.is_active='true' and c.is_deleted='false' and c.id=?1 order by c.dept_Name")
	CenterDepartments getDepartmentById(Integer id);
}
