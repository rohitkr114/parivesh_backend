package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.ActivityDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Activities;
import com.backend.model.CommonFormDetail;
import com.backend.model.District;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProjectDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.State;
import com.backend.model.EcForm6V2.EcForm6ProjectActivityDetailsV2;
import com.backend.model.EcForm6V2.EcForm6ProjectDetailsV2;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.StandardTORCertificate.EcExpansionNIPLCertificate;
import com.backend.model.StandardTORCertificate.EcExtensionOfValidityCertificate;
import com.backend.model.StandardTORCertificate.EcFreshLetterTemplateCertificate;
import com.backend.model.StandardTORCertificate.StandardTorCertificatePlantEquipment;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.ActivitySubActivitySectorRepository;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DistrictRepository;
import com.backend.repository.postgres.EnvironmentClearanceProjectActivityDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.EcForm6V2.EcForm6ProjectActivityDetailsV2Repo;
import com.backend.repository.postgres.EcForm6V2.EcForm6ProjectDetailsV2Repo;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPartBRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcExtensionOfValidityCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcFreshLetterTemplateCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EcExtensionOfValidityCertificateService {

	@Autowired
	EcExtensionOfValidityCertificateRepository ecExtensionOfValidityCertificateRepository;

	@Autowired
	CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

	@Autowired
	Environment environment;

	@Autowired
	ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	EcPartBRepository ecPartBRepository;

	@Autowired
	EcPartCRepository ecPartCRepository;

	@Autowired
	CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	ProjectDetailRepository projectDetailRepository;

	@Autowired
	ApplicationsRepository applicationsRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearenceRepository;

	@Autowired
	ActivitySubActivitySectorRepository activitySectorRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	EcForm6ProjectDetailsV2Repo ecForm6ProjectDetailsV2Repo;
	
	@Autowired
	EcForm6ProjectActivityDetailsV2Repo ecForm6ProjectActivityDetailsV2Repo;

	@Autowired
	EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

	public Object saveEcExtensionOfValidityCertificate(UserPrincipal principal,
													   EcExtensionOfValidityCertificate ecExtensionOfValidityCertificate, HttpServletRequest request)
			throws IOException {

		log.info("==================Calling Save API for Standard tor Certificate========================");
		EcExtensionOfValidityCertificate ecExtensionOfValidityCertificateResult = null;
		// Maintaining History
		if (ecExtensionOfValidityCertificateRepository.existsById(ecExtensionOfValidityCertificate.getId())) {
			log.info(
					"==================One Record is already exist for Standard tor Certificate========================");
			Optional<EcExtensionOfValidityCertificate> ecExtensionOfValidityCertificate1 = ecExtensionOfValidityCertificateRepository
					.findById(ecExtensionOfValidityCertificate.getId());
			ecExtensionOfValidityCertificateResult = ecExtensionOfValidityCertificate1.get();
			if (ecExtensionOfValidityCertificateResult.getCreated_by().equals(principal.getId())) {
				ecExtensionOfValidityCertificate.setIsActive(true);
				ecExtensionOfValidityCertificate.setId(ecExtensionOfValidityCertificateResult.getId());
			} else {
				ecExtensionOfValidityCertificateResult.setIsActive(false);
				ecExtensionOfValidityCertificateRepository.save(ecExtensionOfValidityCertificateResult);
				ecExtensionOfValidityCertificate.setId(null);
				int version = ObjectUtils.isEmpty(ecExtensionOfValidityCertificate.getVersion()) ? 1 : ecExtensionOfValidityCertificate.getVersion() + 1;
				ecExtensionOfValidityCertificate.setIsActive(true);
				ecExtensionOfValidityCertificate.setVersion(version);
			}
		}
		log.info("==================Saved Standard tor Certificate========================");
		int version = ObjectUtils.isEmpty(ecExtensionOfValidityCertificate.getVersion()) ? 1
				: ecExtensionOfValidityCertificate.getVersion() + 1;
		ecExtensionOfValidityCertificate.setIsActive(true);
		ecExtensionOfValidityCertificate.setVersion(version);

		Optional.ofNullable(ecExtensionOfValidityCertificate.getProposal_No()).orElseThrow(
				() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

		// certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
		// EnvironmentClearence environmentClearence =
		// environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
		ProponentApplications proponentApplications = proponentApplicationRepository
				.getProponentByIdAndProposalNo(ecExtensionOfValidityCertificate.getProponentId(),
						ecExtensionOfValidityCertificate.getProposal_No())
				.orElseThrow(() -> new PariveshException(
						"Data is not found for proponent id: " + ecExtensionOfValidityCertificate.getProponentId()
								+ " proposal no: " + ecExtensionOfValidityCertificate.getProposal_No()));
		String folderDir = null;
		try {
			CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
			String clearanceTypeId = proponentApplications.getClearance_id().toString();
			String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); // this part still not
																								// clear
			String proposalId = proponentApplications.getProposal_id().toString();
			folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

		} catch (Exception e) {
			throw new PariveshException(
					"Data is not found for proponent id: " + ecExtensionOfValidityCertificate.getProponentId()
							+ " proposal no: " + ecExtensionOfValidityCertificate.getProposal_No());
		}

		String convertedProposalNo = FileUploadUtil
				.convertSlashIntoUnderscore(ecExtensionOfValidityCertificate.getProposal_No());
		String fileName = FileUploadUtil.fileNameMaker("ec_extension_of_validity" + convertedProposalNo + "_"
				+ ecExtensionOfValidityCertificate.getProponentId(), "pdf");

		// if
		// (ecExtensionOfValidityCertificate.getStatus().equalsIgnoreCase("complete")) {
		// File path generation code this could be common
		String filePath = FileUploadUtil.generatePdfFromHtml(ecExtensionOfValidityCertificate.getHtmlContent(),
				folderDir, fileName, ecExtensionOfValidityCertificate.getProposal_No());
		// }
		ecExtensionOfValidityCertificate
				.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
		ecExtensionOfValidityCertificate
				.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);

		return ecExtensionOfValidityCertificateRepository.save(ecExtensionOfValidityCertificate);
	}

	public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String proposalNo) {
		log.info("==============================getting EC detail by ProId===========================");
		CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
		EcExtensionOfValidityCertificate ecExtensionOfValidityCertificate = ecExtensionOfValidityCertificateRepository
				.getEcExtensionOfValidityCertificateInfoBytPropId(propId, proposalNo);

		if (!ObjectUtils.isEmpty(ecExtensionOfValidityCertificate)) {
			BeanUtils.copyProperties(ecExtensionOfValidityCertificate, proposalDetailInfo);
			
            ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo).get();
            if (proponentApplications != null) {
            	
            	CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
            	proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
            }
			
			return proposalDetailInfo;
		}

		/******** New Changes Start ************/

		String proposalNoParts[] = proposalNo.split("/");
		String authorityCode = proposalNoParts[0];

		if ("SIA".equals(authorityCode)) {
			proposalDetailInfo.setIssuingAuthority("SIAEE");
		} else {
			proposalDetailInfo.setIssuingAuthority("MoEF&CC");
		}

		// Populating using ProponentApplications
		Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(propId);
		ProponentApplications proponentApplication = proponentApplications
				.orElseThrow(() -> new PariveshException("proponent not found"));
		proposalDetailInfo.setFileNo(proponentApplication.getMoefccFileNumber());
		proposalDetailInfo
				.setClearanceType(applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name());
		proposalDetailInfo.setDateOfSubmission(proponentApplication.getLast_submission_date());
		
		// Populating using CommonFormDetail
		CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplication.getCaf_id());
		proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
		proposalDetailInfo.setCompanyname(CommonUtils.handleEmpty(commonFormDetail.getOrganization_name()));
		proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
		proposalDetailInfo.setCafId(proponentApplication.getCaf_id());
		proposalDetailInfo.setProponentId(propId);
		proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
    	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

		// Populating using ProjectDetails
		ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
		proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));

		// Populating using EcPartC
		Optional<EcForm6ProjectDetailsV2> ecForm6 = ecForm6ProjectDetailsV2Repo.getDataById(proponentApplication.getProposal_id());
		EcForm6ProjectDetailsV2 ecForm6Details = ecForm6.orElseThrow(() -> new PariveshException(
				"No record found in EC Form 6 for this proposal Id: " + proponentApplication.getProposal_id()));

		log.info(String.valueOf(ecForm6Details.getEc_id()));
		// Condition for Ec_id
		if (!(ObjectUtils.isEmpty(ecForm6Details.getEc_id()))) {
			
			Optional<EcPartC> ecPartC = ecPartCRepository.getStepOneForm(ecForm6Details.getEc_id());
			EcPartC ecPartCs = ecPartC.orElseThrow(() -> new PariveshException(
					"No record found in EC Part C for this proposal Id: " + proponentApplication.getProposal_id()));

			proposalDetailInfo.setEcId(ecForm6Details.getEc_id());
			proposalDetailInfo.setEcDate(ecPartCs.getDate_of_issue_tor() != null? ecPartCs.getDate_of_issue_tor().toString() : "NA");
			//throw new PariveshException("Invalid parameter : Ec Id must not be null");
		}

		// Populating using EnvironmentClearence
		//EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecPartCs.getEc_id());
		proposalDetailInfo.setCategory(ecForm6Details.getProjectCategory());
		//proposalDetailInfo.setForestLandB(ecForm6Details.getFc_applied_diversion());
		proposalDetailInfo.setProposal_No(ecForm6Details.getProposalNo());
		proposalDetailInfo.setReason(ecForm6Details.getAmendmentDetails() != null ? ecForm6Details.getAmendmentDetails().getValidityExtensionReason() : "NA");
		proposalDetailInfo.setStatusOfImplementationProject(ecForm6Details.getEcForm6ImplementationDetailsV2() != null ? ecForm6Details.getEcForm6ImplementationDetailsV2().getImplementationStatus() : "NA");

		
		List<EcForm6ProjectActivityDetailsV2> projectActivityDetails = ecForm6ProjectActivityDetailsV2Repo.getProjectActivityList(ecForm6Details.getId());
		List<String> projectIds = new ArrayList<>();
		List<String> projectNames = new ArrayList<>();

		projectActivityDetails.stream().forEach(item -> {
			Activities activities = item.getActivities();

			if (activities.getItem_no() != null)
				projectIds.add(activities.getItem_no() + " " + activities.getName());
			else
				projectIds.add(activities.getName());

			projectNames.add(activities.getName());
			System.out.println(
					"===================" + activities.getId() + "==========================" + activities.getName());
		});

		proposalDetailInfo.setProjectIncludedScheduleNo(String.join(",", projectIds));
		proposalDetailInfo.setProjectActivity(projectNames);
		proposalDetailInfo.setProjectID(projectDetails.getId());

		if (ecForm6Details.getMajorActivityId() != null || ecForm6Details.getMajorSubActivityId() != null)
			proposalDetailInfo.setSector(activitySectorRepository.getSectorName(ecForm6Details.getMajorActivityId(), ecForm6Details.getMajorSubActivityId()));

		// Populating using Address and Location
		District district = districtRepository.getDistrictByCode(projectDetails.getMain_state(),
				projectDetails.getMain_district());
		State state = stateRepository.getByStateCode(projectDetails.getMain_state());
		String districtName = district != null ? district.getName() : "NA";
		String stateName = state != null ? state.getName() : "NA";

		Integer stateId = commonFormDetail.getOrganization_state();
		State stateOrg = stateRepository.getByStateCode(stateId);
		String stateOrgName = stateOrg != null ? stateOrg.getName() + ", " : "";

		Integer districtId = commonFormDetail.getOrganization_district();
		District districtOrg = districtRepository.getDistrictByCode(stateId, districtId);
		String districtOrgName = districtOrg != null ? districtOrg.getName() + ", " : "";

		String streetOrg = commonFormDetail.getOrganization_street() != null
				? commonFormDetail.getOrganization_street() + ", "
				: "";
		String cityOrg = commonFormDetail.getOrganization_city() != null
				? commonFormDetail.getOrganization_city() + ", "
				: "";
		String landmarkOrg = commonFormDetail.getOrganization_landmark() != null
				? commonFormDetail.getOrganization_landmark() + ", "
				: "";
		String pinOrg = commonFormDetail.getOrganization_pincode() != null ? commonFormDetail.getOrganization_pincode()
				: "";

		String registeredAddress=null;
		if (cityOrg!=null){
			registeredAddress = streetOrg + cityOrg
					+ districtOrgName + stateOrgName
					+ landmarkOrg
					+ pinOrg;
		}else{
			registeredAddress = streetOrg + districtOrgName + stateOrgName
					+ landmarkOrg + pinOrg;
		}

		proposalDetailInfo.setRegisteredAddress(registeredAddress);
		proposalDetailInfo.setNameOfOrganization(commonFormDetail.getOrganization_name());
		proposalDetailInfo.setLocationOfProject(districtName + ", " + stateName);

		/******** New Changes End ************/

		//String certificateName = "ec_extension_of_validity";
		//return certProposalDetailInfoCommonService.getDetailsFromDTO(proposalDetailInfo, proposalNo, propId,
			//	certificateName);
		
		return proposalDetailInfo;
	}

	private EnvironmentClearence getEnvironmentClearanceByPropId(Integer id) {
		EnvironmentClearence environmentClearence = environmentClearenceRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("EnvironmentClearence form not found"));
		environmentClearence.setProponentApplications(
				proponentApplicationRepository.getApplicationByProposalId(environmentClearence.getId()));
		environmentClearence.setCommonFormDetail(commonFormDetailRepository
				.findDetailByCafId(environmentClearence.getProponentApplications().getCaf_id()));
		return environmentClearence;
	}

	public String getIdentificationNo(int propId) {

		log.info("=====================Identification No generation for %d================================", propId);

		// Populating using ProponentApplications
		Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(propId);
		ProponentApplications proponentApplication = proponentApplications
				.orElseThrow(() -> new PariveshException("proponent not found"));

		String identificationNo = ecExtensionOfValidityCertificateRepository
				.getIdentificationNo(proponentApplication.getProposal_no());

		return identificationNo;
	}
}
