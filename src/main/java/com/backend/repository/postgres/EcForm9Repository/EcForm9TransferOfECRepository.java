package com.backend.repository.postgres.EcForm9Repository;

import com.backend.dto.EcForm9.EcForm9dto;
import com.backend.model.EcForm8TransferOfTOR.EcForm8TransferOfTOR;
import com.backend.model.EcForm9TransferOfEC.EcForm9TransferOfEC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EcForm9TransferOfECRepository extends JpaRepository<EcForm9TransferOfEC,Integer> {

    Optional<EcForm9TransferOfEC> getEcForm9TransferOfEcByid(Integer id);


    @Query("select new EcForm9TransferOfEC( ec.id,"
            + " ec.proposal_no ) from EcForm9TransferOfEC ec where ec.id=?1")
    public Optional<EcForm9TransferOfEC> getFormById(Integer id);

    @Query(value = "select ec.caf_id from master.ec_form9_transfer_of_ec ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getCafByEcId(Integer id);

    @Query(value = "select * from master.ec_form9_transfer_of_ec where id =?1 limit 1", nativeQuery = true)
    public Optional<EcForm9TransferOfEC> getEcForm9TransferOfECByID(Integer id);

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
            + " ec.transferer_pincode, "
            + " ec.transferer_landmark, "
            + "	ec.transferer_email, "
            + " ec.transferer_landline_no, "
            + " ec.transferer_mobile_no, "
            + " ec.transferer_legal_status, "
            + " ec.transferee_name, "
            + " ec.transferee_street, "
            + " ec.transferee_city, "
            + " ec.transferee_district, "
            + " ec.transferee_state, "
            + "	ec.transferee_pincode, "
            + " ec.transferee_landmark, "
            + " ec.transferee_email, "
            + "	ec.transferee_landline_no, "
            + " ec.transferee_mobile_no, "
            + " ec.transferee_legal_status, "
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
            + " ec.application_made_by, "
            + "	ec.applicant_mobile_no, "
            + "	ec.applicant_i_agree "
            + " from master.ec_form9_transfer_of_ec ec where ec.id=?1" , nativeQuery = true)
    public Optional<EcForm9dto> getForm9ByIdDet(Integer id);

    @Query(value = "select ec.caf_id from master.ec_form9_transfer_of_ec ec where ec.id=?1", nativeQuery = true)
    Integer getCafIdByFormId(Integer id);
    
	@Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
	public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
	
    @Query(value = "select a.name from master.activities a where a.id=:id", nativeQuery = true)
    public String getMajorActivityNameById(@Param(value = "id") Integer id);
    
    @Query(value = "select a.item_no from master.activities a where a.id=:id", nativeQuery = true)
    public String getItemNoById(@Param(value = "id") Integer id);
}
