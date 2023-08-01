package com.backend.repository.postgres.StandardTorCertificate;



import com.backend.model.StandardTORCertificate.FcMinistryStageClearanceCertificate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface FcMinistryStageClearanceCertificateRepository extends JpaRepository<FcMinistryStageClearanceCertificate,Integer> {

    @Query(value = "select st from FcMinistryStageClearanceCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    FcMinistryStageClearanceCertificate getFcMinistryStageClearanceCertificateInfoBytPropId (Integer proponent_id, String proposalNo);
    
    @Query(value = "select oe.officename from master.proponent_applications pa left join master.fc_proposal_division_vw fpdv on pa.proposal_id = fpdv.proposal_id join ua.office_entity oe on oe.entityid = fpdv.division_id  where pa.id = ?1", nativeQuery = true)
    List<String> getDivision(Integer propId);
    
	//@Query(value = "select fpsgbde.recommended_area  from ua.forma_partiv_state_govt_basic_dtl_entity fpsgbde where fpsgbde.proposal_id = ?1 and fpsgbde.proposal_no = ?2 and fpsgbde.status = ?3 limit 1", nativeQuery= true)
//	@Query(value = "select fpsge.recommended_area  from authority.form_A_part_IV_basic_details fpsge where fpsge.fc_id = ?1 and fpsge.status = ?3", nativeQuery= true)
//    Double getFormAArea(Integer proposalId, String proposalNo,String submit);
//	
//	@Query(value = "select fpsgbde.state_file_no  from authority.form_A_part_IV_basic_details fpsgbde where fpsgbde.proposal_id = ?1 and fpsgbde.proposal_no = ?2 and fpsgbde.status = ?3 limit 1", nativeQuery= true)
//	String getFormAFileNo(Integer proposalId, String proposalNo,String submit);
    
	@Query(value = "select fpsge.recommended_area  from authority.form_A_part_IV_basic_details fpsge where fpsge.fc_id = ?1 order by id desc limit 1", nativeQuery= true)
    Double getFormAArea(Integer proposalId);
	
	@Query(value = "select fpsgbde.state_file_no  from authority.form_A_part_IV_basic_details fpsgbde where fpsgbde.fc_id = ?1 order by id desc limit 1", nativeQuery= true)
	String getFormAFileNo(Integer proposalId);
	
	@Query(value = "select fpsgbde.recommended_area  from authority.fc_form_B_part_IV_basic_details fpsgbde where fpsgbde.fc_id = ?1 order by id desc limit 1", nativeQuery= true)
	Double getFormBArea(Integer proposalId);
	
	@Query(value = "select fpsgbde.state_file_no  from authority.fc_form_B_part_IV_basic_details fpsgbde where fpsgbde.fc_id = ?1 order by id desc limit 1", nativeQuery= true)
	String getFormBFileNo(Integer proposalId);
	
	//@Query(value = "select fpsgbde.recommended_area  from ua.formc_partiv_state_govt_basic_dtl_entity fpsgbde where fpsgbde.proposal_id = ?1 and fpsgbde.proposal_no = ?2 and fpsgbde.status = ?3 limit 1", nativeQuery= true)
	@Query(value = "select ffcpibd.recommended_area from authority.fc_form_c_part_iv_basic_details ffcpibd where ffcpibd.fc_id = ?1 order by id desc limit 1", nativeQuery = true)
	Double getFormCArea(Integer proposalId);
	
	//@Query(value = "select fpsgbde.state_file_no  from ua.formc_partiv_state_govt_basic_dtl_entity fpsgbde where fpsgbde.proposal_id = ?1 and fpsgbde.proposal_no = ?2 and fpsgbde.status = ?3 limit 1", nativeQuery= true)
	@Query(value = "select ffcpibd.state_file_no from authority.fc_form_c_part_iv_basic_details ffcpibd where ffcpibd.fc_id = ?1 order by id desc limit 1", nativeQuery = true)
	String getFormCFileNo(Integer proposalId);
	
	//@Query(value = "select fpsgbde.recommended_area  from ua.formd_partiv_state_govt_basic_dtl_entity fpsgbde where fpsgbde.proposal_id = ?1 and fpsgbde.proposal_no = ?2 and fpsgbde.status = ?3 limit 1", nativeQuery= true)
	@Query(value = "select ffdpibd.recommended_area from authority.fc_form_d_part_iv_basic_details ffdpibd where ffdpibd.fc_id =  ?1 order by id desc limit 1", nativeQuery = true)
	Double getFormDArea(Integer proposalId);
	
	//@Query(value = "select fpsgbde.state_file_no  from ua.formd_partiv_state_govt_basic_dtl_entity fpsgbde where fpsgbde.proposal_id = ?1 and fpsgbde.proposal_no = ?2 and fpsgbde.status = ?3 limit 1", nativeQuery= true)
	@Query(value = "select ffdpibd.state_file_no from authority.fc_form_d_part_iv_basic_details ffdpibd where ffdpibd.fc_id =  ?1 order by id desc limit 1", nativeQuery = true)
	String getFormDFileNo(Integer proposalId);
	
	@Query(value = "select ffapibd.recommended_area from master.fc_form_a_part_iv_basic_details ffapibd where ffapibd.project_id = ?1 and ffapibd.proposal_number = ?2 limit 1", nativeQuery= true)
	Double getFormEArea(Integer proposalId, String proposalNo);
	
	@Query(value = "select ffapinbd.recommended_area  from authority.fc_form_a_part_iii_nodal_basic_details ffapinbd inner join authority.fc_form_a_part_iii_nodal_checklist_details ffapincd \r\n"
			+ "on ffapincd.id=ffapinbd.fc_form_a_part_iii_nodal_ckecklist_details_id where ffapincd.fc_id = ?1  order by ffapincd.id desc limit 1;", nativeQuery= true)
	Double getArea(Integer proposalId);
	
	@Query(value = "select oe.officeaddress_line1 ||','|| oe.officeaddress_line2 ||','|| coalesce( cast(oe.tahsil as varchar),'') ||','|| s.name  ||','||  coalesce( cast(oe.pin as varchar),'') as address from ua.office_entity oe left join master.state s on oe.address_state = s.id  left join ua.officetype_entity oe2 on oe2.entityid = oe.office_type where oe.address_state = ?1 and oe2.abbreviation= ?2  and oe.isactive= true", nativeQuery = true)
	public String getStateOffice(Integer stateId, String abbr);
	
	@Query(value = "select psp.value  from process_engine.process_step_parameter psp where psp.application_id = ?1 and psp.parameter_name = 'threshold' and psp.is_active =true order by psp.id desc limit 1", nativeQuery = true)
	Double getThreshold(Integer propId);
	
}
