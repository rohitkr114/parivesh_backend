package com.backend.service.fcPriorApproval;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.controller.PriorApprovalController;
import com.backend.dto.UserPrincipal;
import com.backend.model.District;
import com.backend.model.PriorApprovals;
import com.backend.model.ProjectDetails;
import com.backend.model.State;
import com.backend.model.fcPriorApproval.FcPriorApproval;
import com.backend.repository.postgres.DistrictRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.fcPriorApproval.FcPriorApprovalRepository;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;

@Service
@Transactional
public class FcPriorApprovalService {

	@Autowired
	private FcPriorApprovalRepository listRepository;

	@Autowired
	private ProjectDetailRepository projectDetailRepository;

	@Autowired
	private StateRepository stateRepository;

//	@Autowired
//	private DistrictRepository districtRepository;

	public ResponseEntity<Object> save(FcPriorApproval listProject) {
		FcPriorApproval listProject2 = listRepository.save(listProject);
		return ResponseHandler.generateResponse("save List.", HttpStatus.OK, null, listRepository.save(listProject2));
	}

	public ResponseEntity<Object> changeStatus(FcPriorApproval listProject, Boolean status) {
		listProject.setSubmissionFlag(status);
		FcPriorApproval listProject2 = listRepository.save(listProject);
		return ResponseHandler.generateResponse("submission flag changed.", HttpStatus.OK, null,
				listRepository.save(listProject2));
	}

	public ResponseEntity<Object> getList(Integer id, Integer project_id, String sw_id, @CurrentUser UserPrincipal user) {
		List<FcPriorApproval> listProject;
		if (sw_id == null && (project_id == null || project_id == 0) && (id == null || id == 0)) {
			listProject = listRepository.findLoginAll(user.getId().toString());

			listProject.stream().map(approval -> {
				ProjectDetails project;
				Integer temp = approval.getProject().getId();
				project = projectDetailRepository.getById(temp);
				project.setState(stateRepository.getByStateCode(project.getMain_state()));
				project.getState().setDistrictlist(new ArrayList<>());
//			if(approval.getDistrict_id_lgd() == 0)
//				approval.setDistrict_details_by_lgd_id(null);
//			else {
//				District district = districtRepository.getById(approval.getDistrict_id_lgd());
//				approval.setDistrict_details_by_lgd_id(district);
//			}
//			if(approval.getState_lgd_id()==0)
//				approval.setState_details_by_lgd_id(null);
//			else {
//				State state = stateRepository.getById(approval.getState_lgd_id());
//				approval.setState_details_by_lgd_id(state);
//				approval.getState_details_by_lgd_id().setDistrictlist(new ArrayList<>());
//			}
				approval.setProject(project);
				return approval;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("retrieved all by str", HttpStatus.OK, null, listProject);
		} else {
			if (!(project_id == null) && project_id > 0) {

				listProject = listRepository.findAllByProject(project_id, user.getId().toString());

				listProject.stream().map(approval -> {
					ProjectDetails project;
					project = projectDetailRepository.getById(project_id);
					project.setState(stateRepository.getByStateCode(project.getMain_state()));
					project.getState().setDistrictlist(new ArrayList<>());
//				if(approval.getDistrict_id_lgd() == 0)
//					approval.setDistrict_details_by_lgd_id(null);
//				else {
//					District district = districtRepository.getById(approval.getDistrict_id_lgd());
//					approval.setDistrict_details_by_lgd_id(district);
//				}
//				if(approval.getState_lgd_id()==0)
//					approval.setState_details_by_lgd_id(null);
//				else {
//					State state = stateRepository.getById(approval.getState_lgd_id());
//					approval.setState_details_by_lgd_id(state);
//					approval.getState_details_by_lgd_id().setDistrictlist(new ArrayList<>());
//				}
					approval.setProject(project);
					return approval;
				}).collect(Collectors.toList());
				return ResponseHandler.generateResponse("retrieved all by project", HttpStatus.OK, null, listProject);
			} else if (sw_id != null) {
				listProject = listRepository.findAllBySw(sw_id, user.getId().toString());
				listProject.stream().map(approval -> {
					ProjectDetails project;
					Integer temp = approval.getProject().getId();
					project = projectDetailRepository.getById(temp);
					project.setState(stateRepository.getByStateCode(project.getMain_state()));
					project.getState().setDistrictlist(new ArrayList<>());
//					if(approval.getDistrict_id_lgd() == 0)
//						approval.setDistrict_details_by_lgd_id(null);
//					else {
//						District district = districtRepository.getById(approval.getDistrict_id_lgd());
//						approval.setDistrict_details_by_lgd_id(district);
//					}
//					if(approval.getState_lgd_id()==0)
//						approval.setState_details_by_lgd_id(null);
//					else {
//						State state = stateRepository.getById(approval.getState_lgd_id());
//						approval.setState_details_by_lgd_id(state);
//						approval.getState_details_by_lgd_id().setDistrictlist(new ArrayList<>());
//					}
					approval.setProject(project);
					return approval;
				}).collect(Collectors.toList());
				return ResponseHandler.generateResponse("retrieved all by sw_proposal_no", HttpStatus.OK, null,
						listProject);
			} else if (!(id == null) && id > 0) {
				FcPriorApproval approval;
				approval = listRepository.findWithId(id, user.getId().toString());
				ProjectDetails project;
				Integer temp = approval.getProject().getId();
				project = projectDetailRepository.getById(temp);
				project.setState(stateRepository.getByStateCode(project.getMain_state()));
				project.getState().setDistrictlist(new ArrayList<>());
//			if(approval.getDistrict_id_lgd() == 0)
//				approval.setDistrict_details_by_lgd_id(null);
//			else {
//				District district = districtRepository.getById(approval.getDistrict_id_lgd());
//				approval.setDistrict_details_by_lgd_id(district);
//			}
//			if(approval.getState_lgd_id()==0)
//				approval.setState_details_by_lgd_id(null);
//			else {
//				State state = stateRepository.getById(approval.getState_lgd_id());
//				approval.setState_details_by_lgd_id(state);
//				approval.getState_details_by_lgd_id().setDistrictlist(new ArrayList<>());
//			}
				approval.setProject(project);
				return ResponseHandler.generateResponse("retrieved by id(pk)", HttpStatus.OK, null, approval);
			}

		}
		return ResponseHandler.generateResponse("unsuccessful query", HttpStatus.OK, null,
				"------------- nothing to find --------------");
	}
	
	public ResponseEntity<Object> setProcessedFlag(Integer id, UserPrincipal user){
		FcPriorApproval approval = listRepository.findWithIdAll(id, user.getId().toString());
		approval.setProcessedFlag(true);
		listRepository.save(approval);
		return ResponseHandler.generateResponse("Processed status set  true for id ------>" + id, HttpStatus.OK, null, approval);
	}
	
	public ResponseEntity<Object> updatePriorApproval(Integer id, Integer fc_id, Integer caf_id, UserPrincipal user){
			FcPriorApproval approval = listRepository.findWithId(id, user.getId().toString());
			if(caf_id != null)
				approval.setCaf_id(caf_id);
			if(fc_id != null)
				approval.setFc_id(fc_id);
			listRepository.save(approval);
			return ResponseHandler.generateResponse("Updated FC prior approval with id ------->" + id, HttpStatus.OK, null, approval);
	}

}
