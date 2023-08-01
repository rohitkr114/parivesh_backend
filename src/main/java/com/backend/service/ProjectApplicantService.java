package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.constant.AppConstant;
import com.backend.dto.CAFMap;
import com.backend.dto.ProjectCafMapping;
import com.backend.dto.ProponentApplicant;

import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.response.ResponseHandler;

@Service
@Transactional
public class ProjectApplicantService {

	@Autowired
	ProjectDetailRepository projectDetailRepository;

	public ResponseEntity<Object> getApplicantsByProject(Integer id) {

		/*
		 * GetApplicant Projects without Logged In User Id
		 */
		List<Object[]> queryData = projectDetailRepository.getprojectCafData(id);
		
		ProjectCafMapping projectCafMapping = new ProjectCafMapping();
		projectCafMapping.setProjectName((String) queryData.get(0)[0]);
		projectCafMapping.setSwNo((String) queryData.get(0)[1]);
		projectCafMapping.setMainState((String) queryData.get(0)[2]);
		projectCafMapping.setProjectId(id);
		List<CAFMap> cafList = new ArrayList();

		for (int i = 0; i < queryData.size(); i++) {
			Object[] data = queryData.get(i);
			if (i != 0) {
				int previousCafId = (Integer) queryData.get(i - 1)[4];
				int currentCafId = (Integer) data[4];
				if(data[4] != null) {
				if (previousCafId != currentCafId) {

					CAFMap caf = new CAFMap();
					List<ProponentApplicant> applicants = new ArrayList();
					caf.setCafId((Integer) data[4]);
					caf.setCafNo((String) data[5]);
					caf.setUpdatedOn((Date) data[6]);
					List<Object[]> proponentData = queryData.stream()
							.filter(objects -> (Integer) objects[4] == (Integer) data[4]).collect(Collectors.toList());
					if(proponentData.get(0)[7]!=null) {
						
						for (Object[] applicantData : proponentData) {
							ProponentApplicant proponentApplicant = new ProponentApplicant();
							proponentApplicant.setProposalNo((String) applicantData[7]);
							proponentApplicant.setLastStatus(AppConstant.Caf_Status
									.values()[(Integer) applicantData[8] != null ? (Integer) applicantData[8] : 0].name());

							proponentApplicant.setUpdatedOn((Date) applicantData[9]);
							proponentApplicant.setUpdatedBy((Integer) applicantData[10]);
							proponentApplicant.setState((String) applicantData[11]);
							proponentApplicant.setClearanceName((String) applicantData[12]);
							applicants.add(proponentApplicant);

						}
						
						
				 }
					caf.setProponentApplicants(applicants);
					cafList.add(caf);
				} 
				else {
				CAFMap caf = new CAFMap();
				List<ProponentApplicant> applicants = new ArrayList();
				caf.setCafId((Integer) data[4]);
				caf.setCafNo((String) data[5]);
				caf.setUpdatedOn((Date) data[6]);
				if ((Integer) data[4] != null) {
					List<Object[]> proponentData = queryData.stream()
							.filter(objects -> (Integer) objects[4] == (Integer) data[4]).collect(Collectors.toList());
					if(proponentData.get(0)[7] != null) {
						for (Object[] applicantData : proponentData) {
							if(applicantData[7] !=null) {							
								ProponentApplicant proponentApplicant = new ProponentApplicant();
								proponentApplicant.setProposalNo((String) applicantData[7]);
								proponentApplicant.setLastStatus(AppConstant.Caf_Status
										.values()[(Integer) applicantData[8] != null ? (Integer) applicantData[8] : 0].name());
								proponentApplicant.setUpdatedOn((Date) applicantData[9]);
								proponentApplicant.setUpdatedBy((Integer)applicantData[10]);
								proponentApplicant.setState((String) applicantData[11]);
								proponentApplicant.setClearanceName((String) applicantData[12]);
								applicants.add(proponentApplicant);
							}
						}
						
						
						
					}
					
			

				}
				caf.setProponentApplicants(applicants);
				cafList.add(caf);

			 }
				}
			}
		}

		projectCafMapping.setCafs(cafList);

		return ResponseHandler.generateResponse("get project caf mapping", HttpStatus.OK, "", projectCafMapping);

	}

}
