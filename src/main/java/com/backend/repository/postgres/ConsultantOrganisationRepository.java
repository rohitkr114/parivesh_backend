package com.backend.repository.postgres;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.ListOrganisationsDto;
import com.backend.model.ConsultantOrganisation;

public interface ConsultantOrganisationRepository extends JpaRepository<ConsultantOrganisation, Integer> {

	@Query("SELECT a from ConsultantOrganisation a where a.is_active='true' and a.org_id=?1 ")
	ConsultantOrganisation findByOrg_id(String org_id);
	
	@Transactional
	@Modifying
	@Query(value="update master.consultant_organisation set name=?1,"
			+ "accreditation_no =?2,address =?3,"
			+ "category =?4,validity_flag=?5,"
			+ "accreditation_sectors =?6,pan =?7,"
			+ "email=?8,head=?9,"
			+ "landline_no=?10,mobile_no=?11,"
			+ "designation=?12,accreditation_validity=?13 "
			+ "where org_id =?14",nativeQuery=true)
	public Integer UpdateConsultantorganisation(String name,String accrNo,String addr,String cat,String valFlag,
			String sector,String pan,String email,String head,String landline,String mob,String des,Date validity,String orgid);

	@Query(value = "SELECT New com.backend.dto.ListOrganisationsDto(c.Accreditation_No, " + "c.accreditation_sectors, "
			+ "c.accreditation_validity, " + "c.address, " + "c.category, " + "c.designation, " + "c.district, "
			+ "c.email, " + "c.head, " + "c.id, " + "c.landline_no, " + "c.mobile_no, " + "c.name, " + "c.org_id, "
			+ "c.pan, " + "c.pincode, " + "c.state_ut, " + "c.validity_flag, "
			+ "c.website) from ConsultantOrganisation c order by c.name")
	List<ListOrganisationsDto> findAllOrganisations();

	@Query(value= " select org_id from master.consultant_organisation where id in (select organisation_id from master.consultant_to_organisation where user_id=?1) ;" ,nativeQuery=true)
	List<String> findOrganisationId(Integer ConId);

}
