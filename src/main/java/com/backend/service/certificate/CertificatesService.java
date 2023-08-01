package com.backend.service.certificate;

import com.backend.dto.Clearance;
import com.backend.exceptions.PariveshException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.certificate.Certificates;
import com.backend.repository.postgres.certificate.CertificatesRepository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CertificatesService {

	@Autowired
	private CertificatesRepository certificatesRepository;

	@Autowired
	private EnvironmentClearenceRepository environmentClearenceRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ActivitySubActivitySectorRepository activitySectorRepository;

	@Autowired
	private CommonFormDetailRepository commonFormDetailRepository;


	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	public Certificates saveCertificates(Certificates certificates) {

		return certificatesRepository.save(certificates);
	}

	public EnvironmentClearence getStandTorDetailByPropId (int propId) {
		log.info("==============================getting EC detail by ProId===========================");
		Integer proposalId = proponentApplicationRepository.getProposalId(propId)
				.orElseThrow(() -> new PariveshException("No record found"));
		EnvironmentClearence environmentClearence = environmentClearenceRepository.findById(proposalId)
				.orElseThrow(() -> new PariveshException("Ec From not found with id."));
		environmentClearence.setProponentApplications(
						proponentApplicationRepository.getApplicationByProposalId(environmentClearence.getId()));
		environmentClearence.setCommonFormDetail(commonFormDetailRepository
				.findDetailByCafId(environmentClearence.getProponentApplications().getCaf_id()));
		return environmentClearence;

	}

	public String getIdentificationNo (int propId) {
		EnvironmentClearence environmentClearence = getStandTorDetailByPropId(propId);
		System.out.println(environmentClearence + "This is data" + propId );
		ProponentApplications proponentApplications = environmentClearence.getProponentApplications();
		Applications applications = proponentApplications.getApplications();

		CommonFormDetail formDetail = environmentClearence.getCommonFormDetail();
		String formId = applications.getAbbr();
		Integer clearanceId = proponentApplications.getClearance_id();
		String state = proponentApplications.getState();
		Date createdDate = proponentApplications.getCreated_on();
		String proposalNo = environmentClearence.getProposal_no();
		String[] propArray = proposalNo.split("/");

		//String stateCode = stateRepository.getStateAbbrByName(state);
		String propNo = propArray[3];
		String year = propArray[4];
		String formCode = getFromCode(formId);

		String torIdentificationNo = new StringBuilder().append("EC01")
				.append(formCode).append("IN").append(year.substring(1)).append(propNo).toString();

        return torIdentificationNo;
	}

	private String getFromCode (String fromName) {
		Map<String, String> formDetailMap = new HashMap<>();
		formDetailMap.put("EC_FORM_1", "00");
		formDetailMap.put("EC_FORM_2", "01");
		formDetailMap.put("EC_FORM_3", "02");
		formDetailMap.put("EC_FORM_4", "03");
		formDetailMap.put("EC_FORM_5", "04");
		formDetailMap.put("EC_FORM_6", "05");
		formDetailMap.put("EC_FORM_7", "06");
		formDetailMap.put("EC_FORM_8", "07");
		formDetailMap.put("EC_FORM_9", "08");
		formDetailMap.put("EC_FORM_10", "09");
		return formDetailMap.get(fromName);
	}

	private String getClearanceCode (String clearanceType) {
		Map<String, String> formDetailMap = new HashMap<>();
		formDetailMap.put("Fresh TOR/New TOR", "EC01");
		formDetailMap.put("FORM A-(Section 2 II)", "FR01");
		formDetailMap.put("FORM A-(Section 2 III)", "FR02");
		formDetailMap.put("Extension of TOR", "EC05");
		return formDetailMap.get(clearanceType);
	}




}
