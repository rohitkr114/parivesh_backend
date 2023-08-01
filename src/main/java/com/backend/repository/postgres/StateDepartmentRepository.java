package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.StateDepartments;

@Repository
public interface StateDepartmentRepository extends JpaRepository<StateDepartments, Integer>{


	@Query("SELECT s from StateDepartments s where s.is_active='true' and s.is_deleted='false' order by s.Dept_Name")
    List<StateDepartments> findAllStateDepartments();
	
	@Query("SELECT s from StateDepartments s where s.is_active='true' and s.is_deleted='false' and s.state_code=?1 order by s.Dept_Name")
	List<StateDepartments> findStateDepartmentByCode(Integer state_code);
}
