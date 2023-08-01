package com.backend.repository.postgres;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.backend.dto.EDS_Details_Dto;
//
//public interface EDS_Details_Dto_Repo extends JpaRepository<EDS_Details_Dto, Integer>{
//	
//	@Query(value="select ed.id, "
//			+ "ed.proponent_application_id, "
//			+ "ed.remarks, "
//			+ "ed.document, "
//			+ "dt.proposal_no, "
//			+ "dt.proposal_id, "
//			+ "dt.applications, "
//			+ "dt.proponentApplications, "
//			+ "dt.clearanceHistory, "
//			+ "dt.ip_address, "
//			+ "dt.proposal_sequence, "
//			+ "dt.status from master.eds_details ed join master.department_application dt on dt.ref_id = ed.proponent_application_id where ed.id=:id", nativeQuery = true)
//	public EDS_Details_Dto getEdsDetails(@Param("id") Integer id);
//}
