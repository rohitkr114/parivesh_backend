package com.backend.repository.postgres;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.ProjectDetails;

@Transactional
public interface ProjectDetailRepository extends JpaRepository<ProjectDetails, Integer> {

	@Query("SELECT distinct p from ProjectDetails p left join p.employees pa left join p.consultants con where (pa.entityid=?2 or con.entityid=?2 or p.created_by=?2) and (p.is_active='true' and p.id=?1)")
    Optional<ProjectDetails> findProjectById(Integer id, Integer user_id);
	
	@Query("SELECT distinct p from ProjectDetails p left join p.employees pa left join p.consultants con where (pa.entityid=?2 or con.entityid=?2) and (p.is_active='true' and p.id=?1)")
    Optional<ProjectDetails> findProjectByIndependentId(Integer id);
	
	@Query("SELECT p from ProjectDetails p where p.is_active='true' and p.sw_no=?1")
    ProjectDetails findProjectBySW(String sw);
	
	@Query("select p from ProjectDetails p left join p.employees pa left join p.consultants con where pa.entityid=?1 or con.entityid=?1 GROUP BY p ORDER BY count(*)")
	List<ProjectDetails> findProjectsByUserId(int id);
	
	@Query("select p from ProjectDetails p left join Employee pa on pa.entityid=?1 and p.created_by=?1 GROUP BY p ORDER BY count(*)")
	List<ProjectDetails> findOwnedProjectsByUserId(int id);
	
	@Query("select p from ProjectDetails p left join p.employees pa left join p.consultants con where (pa.entityid=?1 or con.entityid=?1) and p.id=?2")
	ProjectDetails findProjectByUserIdAndProjectid(int user_id, int project_id);
	
	@Query(value="select user_id from master.consultant_to_project  where project_id =?1",nativeQuery = true)
	List<Integer> findProjectByProjectid(int project_id);
	
	/*
	@Modifying
	@Query(value="delete from master.consultant_to_project where project_id =?1",nativeQuery = true)
	Integer deleteProjectMappingWithConsultant(Integer project_id);*/
	
	@Query("select p from ProjectDetails p where p.sw_no LIKE %?1%")
	ProjectDetails searchProjectbySW(String sw);
	
	@Query("select p from ProjectDetails p where p.name LIKE %?1%")
	ProjectDetails searchProjectbyName(String name);
	
	@Query("select p from ProjectDetails p inner join CommonFormDetail caf on p.id = caf.project_id where p.id =?1 and p.is_active = true")
	List<ProjectDetails> findCommonApplicantsByProjectId(int id);
	
	
	@Query(value = "SELECT a.project_name, a.sw_no, "
			+ "(select h.name from master.state h where h.code=a.main_state)as main_state, a.id, b.id as caf_id, b.caf_id as caf_no, b.updated_on, "
			+ "c.proposal_no,c.last_status, c.updated_on  as app_updated_on, "
			+  "c.updated_by as app_updated_by, "
			+ "(select h.name from master.state h where  h.code = CASE WHEN c.clearance_id = 1  THEN  (select f.state from master.forest_clearance f where f.id = c.proposal_id) "
			+  " ELSE 0 END) as state, "
			+ "(select apl.name from master.applications apl where apl.id = c.clearance_id ) as clearance_name "
			+ " FROM master.project_details a left join master.caf_details b on b.project_id = a.id "
			+ " left join master.proponent_applications c on c.caf_id = b.id where a.id = ?1", nativeQuery = true)
	public List<Object[]> getprojectCafData(int id);
	
	@Modifying
	@Query(value="delete from master.consultant_to_project where project_id =?1",nativeQuery = true)
	Integer deleteProjectMappingWithConsultant(Integer project_id);
	
	
	
}
