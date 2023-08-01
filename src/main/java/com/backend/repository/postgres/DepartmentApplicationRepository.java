package com.backend.repository.postgres;

import java.util.List;
import java.util.Optional;

import com.backend.dto.UserDashboardCountDto;
import com.backend.dto.ViewProposalCountDto;
import com.backend.dto.ViewProposalsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.DepartmentApplication;
import org.springframework.data.repository.query.Param;

public interface DepartmentApplicationRepository extends JpaRepository<DepartmentApplication, Integer>{

	@Query("Select d from DepartmentApplication d where d.proposal_no=?1")
	public DepartmentApplication getApplicationByProposalNo(String proposal);
	
	@Query("Select d from DepartmentApplication d where d.proposal_no=?1")
	public List<DepartmentApplication> getApplicationsByProposalNo(String proposal);
	
	@Query("Select d from DepartmentApplication d where d.proposal_id=?1")
	public DepartmentApplication getApplicationByProposalId(Integer proposal);

	@Query("Select d from DepartmentApplication d where d.proposal_id=?1")
	public Optional<DepartmentApplication> getApplicationByProposalId2(Integer proposal);
	
	@Query(value="Select * from master.department_application d where d.proposal_id=?1 limit 1",nativeQuery = true)
	public DepartmentApplication getDepartmentApplicationByProposalId(Integer proposalId);
	
	@Query("SELECT d from DepartmentApplication d where d.proposal_no=?1 and d.created_by=?2 and d.applications.id=6")
	public List<DepartmentApplication> findByLoggedInUser(String proposalNo,Integer user_id);

	@Query(value = "select * from master.view_proposal_list(:userId,:authority);",nativeQuery = true)
	List<ViewProposalsDto> getProposalList(@Param(value = "userId") Integer userId,@Param(value = "authority") String authority);

	@Query(value = "select * from master.user_dashboard_count_fnc(:userId) order by workgroup_id",nativeQuery = true)
	List<UserDashboardCountDto> getUserDashboardCount(@Param(value = "userId") Integer userId);

	@Query(value = "select * from master.count_of_view_proposal_authority(:userId)",nativeQuery = true)
	List<ViewProposalCountDto> getViewProposalCountByAuthority(@Param("userId") Integer userId);

}
