package com.backend.repository.postgres.EcForm8;

import com.backend.dto.EcForm8.EcForm8OrganisationByNameDTO;
import com.backend.dto.EcForm8.EcForm8OrganisationDTO;
import com.backend.dto.EcForm8.EcForm8dto;
import com.backend.model.EcForm7.EcForm7;
import com.backend.model.EcForm8TransferOfTOR.EcForm8TransferOfTOR;
import com.backend.model.EcFormVModel.EcFormV;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm8TransferOfTORRepository extends
    JpaRepository<EcForm8TransferOfTOR, Integer> {

  Optional<EcForm8TransferOfTOR> getEcForm8TransferOfTorByid(Integer id);

  @Query(value = "select ec.caf_id from master.ec_form8_transfer_of_tor ec where ec.id=?1", nativeQuery = true)
  public List<Object[]> getCafByEcId(Integer id);

  @Query("select new EcForm8TransferOfTOR( ec.id,"
      + " ec.proposal_no ) from EcForm8TransferOfTOR ec where ec.id=?1")
  public Optional<EcForm8TransferOfTOR> getFormById(Integer id);

/*  @Query("select ec from EcForm8TransferOfTOR ec where ec.id=?1")
  public Optional<EcForm8TransferOfTOR> getFormByIdDet(Integer id);*/


  @Query(value = "select ec.id, "
      + " ec.single_window_number as project_sw_no, "
      + " ec.project_name, "
      + " ec.ec_id, "
      + " ec.proposal_no, "
      + "	ec.transferer_name, "
      + " ec.transferer_street, "
      + " ec.transferer_city, "
      + "	ec.transferer_district, "
      + " ec.transferer_state, "
      + " ec.transferer_pin_code as transferer_pincode, "
      + " ec.transferer_landmarks as transferer_landmark, "
      + "	ec.transferer_email_address as transferer_email, "
      + " ec.transferer_landline_address as transferer_landline_no, "
      + " ec.transferer_mobile_number as transferer_mobile_no, "
      + " ec.transferer_legal_status_of_company as transferer_legal_status, "
      + " ec.transferee_organisation_name as transferee_name, "
      + " ec.transferee_street_number_and_name as transferee_street, "
      + " ec.transferee_village as transferee_city, "
      + " ec.transferee_district, "
      + " ec.transferee_state, "
      + "	ec.transferee_pincode, "
      + " ec.transferee_landmarks as transferee_landmark, "
      + " ec.transferee_email_address as transferee_email, "
      + "	ec.transferee_landline_address as transferee_landline_no, "
      + " ec.transferee_mobile_number as transferee_mobile_no, "
      + " ec.transferee_legal_status_of_company as transferee_legal_status, "
      + " ec.applicant_name, "
      + "	ec.applicant_designation, "
      + " ec.applicant_street, "
      + " ec.applicant_city, "
      + " ec.applicant_district, "
      + " ec.applicant_state, "
      + " ec.applicant_pincode, "
      + " ec.applicant_landmark, "
      + " ec.applicant_email, "
      + " ec.applicant_landline_no, "
      + " ec.applicant_mobile_no, "
      + " ec.application_made_by, "
      + " ec.is_multiple_items_involved, "
      + " ec.is_proposed_required, "
	  + " ec.major_activity_id, "
	  + " ec.major_sub_activity_id, "
	  + " ec.project_category, "
	  + " ec.ec_proposal_no "
      + "from master.ec_form8_transfer_of_tor ec where ec.id=?1" , nativeQuery = true)
  public Optional<EcForm8dto> getFormByIdDet(Integer id);

  @Query(value = "select ec.caf_id from master.ec_form8_transfer_of_tor ec where ec.id=?1", nativeQuery = true)
  Integer getCafIdByFormId(Integer id);

  @Query(value = "select  org.name as transferee_name,org.address as transferee_street,\n"
      + "org.city as transferee_city,dis.id as transferee_district,st.id as transferee_state,org.pincode as transferee_pincode,org.landmark as transferee_landmark,org.email as transferee_email,org.mobile_no as transferee_mobile_no\n"
      + "from master.organisation org left join master.district as dis on CAST(dis.id AS CHAR)=org.district left join master.state st on CAST(st.id AS CHAR)=org.state_ut  WHERE LOWER(org.name) LIKE LOWER( ?1 ) or UPPER(org.name) LIKE UPPER( ?1 ) LIMIT 200", nativeQuery = true)
  List<EcForm8OrganisationDTO> getOrganizationDetails(String name);
  
  @Query(value = "select org.name as transferee_name,org.address as transferee_street,\n"
  		+ "org.city as transferee_city,dis.code as transferee_district,dis.name as transferee_district_name,st.name as transferee_state_name,\n"
  		+ "st.code  as transferee_state,org.entity_type as transferee_legal_status,\n"
  		+ "org.pincode as transferee_pincode,org.landmark as transferee_landmark,e.landline_no as transferee_landline_no,org.email as transferee_email,\n"
  		+ "org.mobile_no as transferee_mobile_no from master.organisation org  left join master.district dis on dis.code=cast(org.district as integer)\n"
  		+ " left join master.state st on CAST(st.code AS CHAR)=org.state_ut left join master.employee e on org.id =e.organisation_id\n"
  		+ " WHERE LOWER(org.name) LIKE LOWER(?1) or UPPER(org.name) LIKE UPPER(?1) LIMIT 200", nativeQuery = true)
	  List<EcForm8OrganisationByNameDTO> getOrganizationDetailsByName(String name);
  
  @Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
  String getIdentificationNo(@Param("proposal_no") String  proposal_no);
  
  @Query(value="select a.name from master.activities a inner join master.ec_form8_transfer_of_tor ef on ef.major_activity_id = a.id where ef.id=:id", nativeQuery = true)
  public String getMajorActivityName(@Param(value="id") Integer id);
  
  @Query(value="select a.item_no from master.activities a inner join master.ec_form8_transfer_of_tor ef on ef.major_activity_id = a.id where ef.id=:id", nativeQuery = true)
  public String getItemNo(@Param(value="id") Integer id);
  
  
}
