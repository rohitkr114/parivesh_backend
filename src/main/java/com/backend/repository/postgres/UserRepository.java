package com.backend.repository.postgres;

import com.backend.dto.SelectedRoleDto;
import com.backend.dto.SelectedSectorDto;
import com.backend.model.Organisation;
import com.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("update User set is_active='false', isActive= 'false' , is_delete='true' where entityid=?1")
    public void deleteEmployeeId(Integer id);

    @Modifying
    @Query("update User set password=?2 where entityid=?1")
    public void updatePassword(Integer id, String password);

    // @Query(value = "Select p from User p where LOWER(email) LIKE LOWER(concat(?1,
    // '%')) or LOWER(emailId) LIKE LOWER(concat(?1, '%'))")
    @Query(value = "Select p from User p where ((LOWER(email) = LOWER(?1)) or (LOWER(emailId) = LOWER(?1))) and isActive = true and is_delete = false")
    public List<User> findRecordByEmail(String email);
    
    @Query(value = "Select p from User p where ((LOWER(email) = LOWER(?1)) or (LOWER(emailId) = LOWER(?1))) and isActive = true and is_delete = false")
    public User findEmployeeByEmail(String email);
    
    @Query(value = "Select p from Employee p where ((LOWER(email) = LOWER(?1)) or (LOWER(emailId) = LOWER(?1))) and isActive = true and is_delete = false and (verify_status is null or  verify_status not like 'REJECTED')")
    public List<User> isDuplicateEmployee(String email);
    
//    @Query(value = "Select p from User p where ((LOWER(email) = LOWER(?1)) or (LOWER(emailId) = LOWER(?1))) and isActive = true and is_delete = false")
//    public List<User> findActiveRecordByEmail(String email);

    @Query(value = "Select new User(p.entityid,p.name_of_Entity,p.user_type) from User p where (LOWER(email) = LOWER(?1)) or (LOWER(emailId) = LOWER(?1))")
    public List<User> findRecordByEmailId(String email);

    @Query(value = "Select p from User p where p.name_of_Entity = cast(?1 as text) and p.state_ut is null")
    public Optional<User> findRecordByEntity(String entity);

    @Query(value = "Select p from User p where p.name_of_Entity = cast(?1 as text) and p.state_ut = cast(?2 as text)")
    public Optional<User> findRecordByEntityAndState(String entity, String state);

    @Query(value = "Select p from User p left join ProjectDetails pd on p.entityid=pd.created_by where pd.created_by=?1")
    public Optional<User> findCreatorById(Integer id);

    @Query(value = "Select new User(p.entityid,p.name_of_Entity,p.user_type) from User p where p.entityid=?1")
    public Optional<User> findAuthorById(Integer id);

    @Query(value = " select email from authentication.user_entity where entityid =?1", nativeQuery = true)
    public String getUserEmail(Integer created_by);

    @Query(value = " select mobilenumber from authentication.user_entity where entityid =?1", nativeQuery = true)
    public String getUserMobile(Integer created_by);

    @Query(value = " select ue.mobilenumber  from master.proponent_applications pa left join master.project_details pd on pd.id = pa.project_id " +
            " left join authentication.user_entity ue on ue.entityid =pd.created_by where pa.proposal_no =?1 ", nativeQuery = true)
    public String getMobileNoByProposalNo(String proposalNo);

    @Query("select u from User u where u.entityid=?1")
    public User getUserDetailsById(Integer id);

    @Query("select new User(u.is_test_user, u.entityid, u.name_of_Entity, u.name, u.firstName, u.middleName, "
            + " u.lastName,  u.address,  u.email, u.mobile, u.mobileNumber,  u.state_ut, "
            + "	u.district, u.pincode, u.password, u.user_type, u.userType, u.status, "
            + " u.ip_address, u.isActive, u.failedAttemptCount, "
            + "	u.emailId,  u.is_active, u.is_adhaar_verified, u.is_delete, u.security_question, "
            + "	u.security_answer, u.createdDate, u.selected_office, u.selected_role) from User u where u.entityid=?1 ")
    public User getUserDataById(Integer id);

    @Query(value = " select r.entityid from authentication.user_entity ue left join authentication.role r on r.entityid = ue.selected_role where ue.entityid =?1 ", nativeQuery = true)
    Integer getSelectedRoleId(Integer id);

    @Query(value = " select de.designation_name from authentication.employee e left join ua.designation_entity de on e.designation_id = de.entityid where e.entityid =?1 ", nativeQuery = true)
    String getDesignationOther(Integer id);

    @Query(value = " select e.designation from master.employee e where e.entityid =?1 ", nativeQuery = true)
    String getDesignationPP(Integer id);

    @Query(value = " select r.rolename  from authentication.user_entity ue " +
            " left join authentication.role r on r.entityid =  ue.selected_role " +
            " where ue.entityid = ?1 ", nativeQuery = true)
    String getSelectedRoleName(Integer id);

    @Query(value = " select ue.selected_sector , se.sector_name  from authentication.user_entity ue " +
            " left join ua.sector_entity se on se.entityid = ue.selected_sector " +
            " where selected_sector is not null and ue.entityid = ?1 ", nativeQuery = true)
    SelectedSectorDto getSelectedSector(Integer id);

    @Query(value = "select count(ue.*) from authentication.user_entity ue where (ue.emailid=:email or ue.email=:email) and ue.isactive =true and ue.is_delete=false ", nativeQuery = true)
    public Integer getEmailCount(@Param(value = "email") String email);

    @Query(value = " select * from master.get_logged_in_user_details_fnc(:id) ", nativeQuery = true)
    List<SelectedRoleDto> getLoggedinUserDetails(Integer id);

    @Query(value = " select * from master.get_logged_in_user_details_fnc(:id) ", nativeQuery = true)
    List<String> getLoggedinUserDetail(Integer id);
    
    @Query("select o from Organisation o where LOWER(o.name) like %:name% and LOWER(entity_type) not like '%individual%' and LOWER(entity_type) not like '%others%' ")
    List<Organisation> searchOrganization(String name);
    

}
