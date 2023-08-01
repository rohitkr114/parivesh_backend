package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.dto.ConsultantByProject;
import com.backend.model.Consultant;

public interface ConsultantRepository extends JpaRepository<Consultant, Integer>{

	/*@Query("SELECT c.consultant_organisation_id from Consultant c where c.entityid=?1")
    Integer findOrganisationId(Integer id);*/
	
	@Query("SELECT ca.org_id from Consultant c join c.consultantOrganisations ca where c.entityid=?1")
    String findOrganisationId(Integer id);
	
	@Query("SELECT c from Consultant c where c.entityid=?1")
	Consultant UserIdIsPresent(Integer id);
	
	@Query("SELECT c from Consultant c where c.is_active='true' and c.is_delete='false'")
	Consultant findAllConsultants();
	
	/*
	@Query("SELECT c from Consultant c where c.consultant_organisation_id=?1 and is_active=cast(?2 as boolean) ORDER BY entityid DESC")
	List<Consultant> get_all_ConsultantsByStatus(Integer id,String active);
	*/
	@Query("SELECT c from Consultant c left join c.consultantOrganisations ca where ca.org_id=?1 and is_active=cast(?2 as boolean) and c.is_owner='false'")
	List<Consultant> get_all_ConsultantsByStatus(String id,String active);
	
//	@Query("select p from Consultant p left join p.projectDetails pe on pe.id=?1 GROUP BY p.id ORDER BY count(*)")
//	List<Consultant> findConsultantByProjectId(int id);

//	@Query(value="SELECT c.*,co.* from master.consultant c join master.consultant_to_project ctp on c.entityid=ctp.user_id and ctp.project_id =:id join master.consultant_to_organisation cto on c.entityid = cto.user_id join master.consultant_organisation co on organisation_id=co.id",
//			nativeQuery = true)
	@Query(value="SELECT new com.backend.dto.ConsultantByProject(c.accrediation_sectors,c.accrediation_validity, co.org_id, c.name, c.email, c.category, c.address, c.mobile) from Consultant c left join c.projectDetails pro left join c.consultantOrganisations co where pro.id=?1 order by pro.updated_on desc")
	List<ConsultantByProject> findConsultantByProjectId(Integer id);
//	
//	@Query("select DISTINCT p from Consultant p left join ProjectDetails pd on pd.id=?1")
//	List<Consultant> findConsultantByProjectId(int id);
	
	/*
	@Query("SELECT c from Consultant c where c.consultant_organisation_id=?1 ORDER BY entityid DESC")
	List<Consultant> get_all_Employees(Integer id);
	*/
	@Query("SELECT c from Consultant c left join c.consultantOrganisations ca where ca.org_id=?1 and c.is_owner='false'")
	List<Consultant> get_all_Employees(String id);
	
	@Query("SELECT c from Consultant c left join c.consultantOrganisations ca where ca.id=?1")
	List<Consultant> getConsultantByOrgId(Integer id);
	
	
	@Query("SELECT c from Consultant c where c.email like ?1 or c.emailId like ?1")
	Consultant getbyEmailId(String email);
}
