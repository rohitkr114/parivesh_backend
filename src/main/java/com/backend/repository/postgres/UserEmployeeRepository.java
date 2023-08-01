package com.backend.repository.postgres;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.backend.model.Employee;
import com.backend.model.HomeRecentMaster;


@Repository
public interface UserEmployeeRepository extends UserRegistrationRepository<Employee>{

	@Query("SELECT e.organisation_id from Employee e where e.entityid=?1")
    Integer findOrganisationId(Integer id);
	
	@Query("SELECT e from Employee e where e.entityid=?1")
	Employee getEmployeeDetails(Integer id);
	
	@Query("SELECT e from Employee e where e.organisation_id=?1 and is_active='true' and is_deleted='false' ORDER BY entityid DESC")
	List<Employee> getEmployeeList(Integer id);
	
	@Query("select p from Employee p left join p.projectDetails pe on pe.id=?1 GROUP BY p ORDER BY count(*)")
	List<Employee> findEmployeeByProjectId(int id);
	
	
	@Query("SELECT e from Employee e where e.organisation_id=?1 and is_active=cast(?2 as boolean) and (e.verify_status = 'VERIFIED' or e.verify_status is null) ORDER BY entityid DESC")
	List<Employee> get_all_EmployeebyStatus(Integer id,String active);

	@Query("SELECT e from Employee e where e.organisation_id=?1 ORDER BY entityid DESC")
	List<Employee> get_all_Employees(Integer id);

	@Query("SELECT e from Employee e where e.organisation_id=?1 and is_active=cast(?2 as boolean) and (e.name_of_Entity like %?3% or e.email like %?3% or e.mobile LIKE %?3%) ORDER BY entityid DESC")
	List<Employee> get_all_EmployeebyStatusWithSearch(Integer orgId,String active,String param);

	@Query("SELECT e from Employee e where e.organisation_id=?1 and (e.name_of_Entity like %?2% or e.email like %?2% or e.mobile LIKE %?2%) ORDER BY entityid DESC")
	List<Employee> get_all_EmployeesWithSearch(Integer orgId,String param);
	
	@Query("select e from Employee e where e.name_of_Entity LIKE %?1%")
	Employee searchEmployee(String name);
	
	@Query("select e from Employee e where e.email LIKE %?1%")
	Employee searchEmployeebyEmail(String email);
	
	@Query("select e from Employee e where e.email=?1")
	Employee searchEmployeebyEmailForMigration(String email);
	
	@Query("select e from Employee e where e.mobile LIKE %?1%")
	Employee searchEmployeebyMobile(String mob);

	@Query("select e from Employee e where e.verify_status = ?1")
	List<Employee> findByVerify_status(String status);
	
	@Query("SELECT e from Employee e where e.entityid=?1")
	Optional<Employee> getEmployee(Integer id);
	
}
