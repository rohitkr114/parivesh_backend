package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.ActivityDto;
import com.backend.dto.EcForm8.EcForm8ProjectActivityDetailsDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.*;
import com.backend.model.EcForm8TransferOfTOR.EcForm8ProjectActivityDetails;
import com.backend.model.EcForm8TransferOfTOR.EcForm8TransferOfTOR;
import com.backend.model.StandardTORCertificate.EcTorTransferTemplate;
import com.backend.model.StandardTORCertificate.SpecificTorCertificate;
import com.backend.model.StandardTORCertificate.StandardTorCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm8.EcForm8ProjectActivityDetailsRepository;
import com.backend.repository.postgres.EcForm8.EcForm8TransferOfTORRepository;
import com.backend.repository.postgres.EcForm9Repository.EcForm9TransferOfECRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcTorTransferTemplateRepository;
import com.backend.repository.postgres.StandardTorCertificate.SpecificTorCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.StandardTorCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import com.beust.jcommander.Strings;
import com.itextpdf.text.DocumentException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

import static com.backend.util.CommonUtils.addStrings;
import static com.backend.util.CommonUtils.replaceEmptyWithNA;

@Service
@Slf4j
public class EcTorTransferTemplateService {

	@Autowired
	EcTorTransferTemplateRepository ecTorTransferTemplateRepository;

	@Autowired
	EcForm8ProjectActivityDetailsRepository ecForm8ProjectActivityDetailsRepository;

	@Autowired
	CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

	@Autowired
	ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	ApplicationsRepository applicationsRepository;

	@Autowired
	CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	ProjectDetailRepository projectDetailRepository;

	@Autowired
	SpecificTorCertificateRepository specificTorCertificateRepository;

	@Autowired
	StandardTorCertificateRepository standardTorCertificateRepository;

	@Autowired
	EcForm8TransferOfTORRepository ecForm8TransferOfTORRepository;

	@Autowired
	EcForm9TransferOfECRepository ecForm9TransferOfECRepository;

	@Autowired
	EnvironmentClearenceRepository environmentClearenceRepository;

	@Autowired
	EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	private ActivitySubActivitySectorRepository activitySectorRepository;

	@Autowired
	Environment environment;

	@Autowired
	StateRepository stateRepository;

	@Transactional
	public Object saveEcTorTransferTemplate(UserPrincipal principal, EcTorTransferTemplate ecTorTransferTemplate, HttpServletRequest request,
											HttpServletResponse response) throws DocumentException, IOException, JRException {

		log.info("==================Calling Save API for Ec TOR Template Certificate========================");
		EcTorTransferTemplate ecTorTransferTemplateResult = null;
		// Maintaining History
		if (ecTorTransferTemplateRepository.existsById(ecTorTransferTemplate.getId())) {
			log.info(
					"==================One Record is already exist for TOR Amendment Certificate========================");
			Optional<EcTorTransferTemplate> ecTorTransferTemplate1 = ecTorTransferTemplateRepository
					.findById(ecTorTransferTemplate.getId());
			ecTorTransferTemplateResult = ecTorTransferTemplate1.get();

			ecTorTransferTemplate.setId(null);
			if (ecTorTransferTemplateResult.getCreated_by().equals(principal.getId())) {
				ecTorTransferTemplate.setIsActive(true);
				ecTorTransferTemplate.setId(ecTorTransferTemplateResult.getId());
			} else {
				ecTorTransferTemplateResult.setIsActive(false);
				ecTorTransferTemplateRepository.save(ecTorTransferTemplateResult);
				ecTorTransferTemplate.setId(null);
				int version = ObjectUtils.isEmpty(ecTorTransferTemplate.getVersion()) ? 1 : ecTorTransferTemplate.getVersion() + 1;
				ecTorTransferTemplate.setIsActive(true);
				ecTorTransferTemplate.setVersion(version);
			}
		}
		log.info("==================Saved Ec TOR Template Certificate========================");
		int version = ObjectUtils.isEmpty(ecTorTransferTemplate.getVersion()) ? 1
				: ecTorTransferTemplate.getVersion() + 1;
		ecTorTransferTemplate.setIsActive(true);
		ecTorTransferTemplate.setVersion(version);

		Optional.ofNullable(ecTorTransferTemplate.getProposal_No()).orElseThrow(
				() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

		// certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
		// EnvironmentClearence environmentClearence =
		// environmentClearenceRepository.findByProposalNo(ecTorTransferTemplate.getProposal_No());
		ProponentApplications proponentApplications = proponentApplicationRepository
				.getProponentByIdAndProposalNo(ecTorTransferTemplate.getProponentId(),
						ecTorTransferTemplate.getProposal_No())
				.orElseThrow(() -> new PariveshException(
						"Data is not found for proponent id: " + ecTorTransferTemplate.getProponentId()
								+ " proposal no: " + ecTorTransferTemplate.getProposal_No()));
		String folderDir = null;
		try {
			CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
			String clearanceTypeId = proponentApplications.getClearance_id().toString();
			String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); // this part still not
																								// clear
			String proposalId = proponentApplications.getProposal_id().toString();
			folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

		} catch (Exception e) {
			throw new PariveshException("Data is not found for proponent id: " + ecTorTransferTemplate.getProponentId()
					+ " proposal no: " + ecTorTransferTemplate.getProposal_No());
		}

		String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecTorTransferTemplate.getProposal_No());
		String fileName = FileUploadUtil.fileNameMaker(
				"ec_tor_transfer" + convertedProposalNo + "_" + ecTorTransferTemplate.getProponentId(), "pdf");

		// if (ecTorTransferTemplate.getStatus().equalsIgnoreCase("complete")) {
		// File path generation code this could be common
		String filePath = FileUploadUtil.generatePdfFromHtml(ecTorTransferTemplate.getHtmlContent(), folderDir,
				fileName, ecTorTransferTemplate.getProposal_No());
		// }
		ecTorTransferTemplate
				.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
		ecTorTransferTemplate
				.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
		// Commented by Ashish
		// proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL)
		// + folderDir + "/" + fileName);
		// proponentApplicationRepository.save(proponentApplications);

		return ecTorTransferTemplateRepository.save(ecTorTransferTemplate);
	}

	/*
	 * public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String
	 * proposalNo) { log.
	 * info("==============================getting EC detail by ProId==========================="
	 * ); CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
	 * EcTorTransferTemplate ecTorTransferTemplate = ecTorTransferTemplateRepository
	 * .getTorTransferTemplateInfoBytPropId(propId, proposalNo);
	 * 
	 * if (!ObjectUtils.isEmpty(ecTorTransferTemplate)) {
	 * BeanUtils.copyProperties(ecTorTransferTemplate, proposalDetailInfo); return
	 * proposalDetailInfo; } return
	 * certProposalDetailInfoCommonService.getDetailsFromDTO(proposalDetailInfo,
	 * proposalNo, propId); }
	 */

	@Transactional
	public CertProposalDetailInfo getStandTorDetailByPropId(int proponentApplicationId, String proposalNo) {
		CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();

		EcTorTransferTemplate ecTorTransferTemplate = ecTorTransferTemplateRepository
				.getTorTransferTemplateInfoBytPropId(proponentApplicationId, proposalNo);

		if (!ObjectUtils.isEmpty(ecTorTransferTemplate)) {
			log.info("copy to:{}", ecTorTransferTemplate.getCopyTo());

			BeanUtils.copyProperties(ecTorTransferTemplate, proposalDetailInfo);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getProponentByIdAndProposalNo(proponentApplicationId, proposalNo).get();
			if (proponentApplications != null) {

				CommonFormDetail commonFormDetail = commonFormDetailRepository
						.getById(proponentApplications.getCaf_id());
				proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

				proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
			}

			return proposalDetailInfo;
		}

		String proposalNoParts[] = proposalNo.split("/");
		String authorityCode = proposalNoParts[0];
		String sectorCodeFromPP = proposalNoParts[2];

		if ("SIA".equals(authorityCode)) {
			proposalDetailInfo.setIssuingAuthority("SIAEE");
		} else {
			proposalDetailInfo.setIssuingAuthority("MoEF&CC");
		}

		proposalDetailInfo.setStatusOfImplementationProject("");

		Optional<ProponentApplications> proponentApplications = proponentApplicationRepository
				.findById(proponentApplicationId);

		// Populating using ProponentApplications
		ProponentApplications proponentApplication = proponentApplications
				.orElseThrow(() -> new PariveshException("proponent not found"));
		proposalDetailInfo.setFileNo(proponentApplication.getMoefccFileNumber());
		proposalDetailInfo.setProposal_No(proposalNo);

		// Populating using CommonFormDetail
		CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplication.getCaf_id());
		proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
		proposalDetailInfo.setCompanyname(CommonUtils.handleEmpty(commonFormDetail.getOrganization_name()));
		proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
		proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
		// proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getApplicant_email()));
		proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
		proposalDetailInfo.setCafId(proponentApplication.getCaf_id());
		// Populating using ProjectDetails
		ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
		proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));

		// String ProposalFor= commonFormDetail.getProposal_for() != null ?
		// commonFormDetail.getProposal_for() : "";
		proposalDetailInfo.setClearanceType(
				applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name());

		// Populating using EcForm8
		Optional<EcForm8TransferOfTOR> ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
				.findById(proponentApplication.getProposal_id());
		EcForm8TransferOfTOR ecForm8TransferOfTORDetails = ecForm8TransferOfTOR.orElseThrow(() -> new PariveshException(
				"No record found in EC Form 8 for this proposal Id: " + proponentApplication.getProposal_id()));

		proposalDetailInfo.setDetails_of_transferee(ecForm8TransferOfTOR.get().getTransferee_name());
		proposalDetailInfo.setDetails_of_transferor(ecForm8TransferOfTOR.get().getTransferer_name());

		// Condition for Ec_id
		if (ObjectUtils.isEmpty(ecForm8TransferOfTOR.get().getEc_id())) {
			// throw new PariveshException("Invalid parameter : Ec Id must not be null");
			log.info("Legacy Form 8");
		}

		// Populating using EnvironmentClearence
		// EnvironmentClearence environmentClearence =
		// getEnvironmentClearanceByPropId(ecForm8TransferOfTORDetails.getEnvironmentClearence().getId());
		proposalDetailInfo.setCategory(ecForm8TransferOfTOR.get().getProject_category());
		proposalDetailInfo.setEcId(ecForm8TransferOfTOR.get().getEc_id());

		StandardTorCertificate standardTorCertificate = standardTorCertificateRepository
				.getStandardTorDateByEcId(ecForm8TransferOfTOR.get().getEc_id());
		SpecificTorCertificate specificTorCertificate = specificTorCertificateRepository
				.getSpecificTorDateByEcId(ecForm8TransferOfTOR.get().getEc_id());
		if (!ObjectUtils.isEmpty(standardTorCertificate)) {
			proposalDetailInfo.setTorDate(standardTorCertificate.getCreated_on().toString());
		} else if (!ObjectUtils.isEmpty(specificTorCertificate)) {
			proposalDetailInfo.setTorDate(specificTorCertificate.getCreated_on().toString());
		}
		/*
		 * StandardTorCertificate standTorCert =
		 * standardTorCertificateRepository.getStandardCertificateInfoBytEcId(
		 * environmentClearence.getId()); SpecificTorCertificate specificTorCertificate
		 * = specificTorCertificateRepository.getSpecificTorCertificateInfoBytEcId(
		 * environmentClearence.getId());
		 * 
		 * if (!ObjectUtils.isEmpty(standTorCert)) {
		 * proposalDetailInfo.setTorDate(standTorCert.getDate());
		 * proposalDetailInfo.setIdentificationNo(standTorCert.
		 * getTorIdentificationNumber()); } else if
		 * (!ObjectUtils.isEmpty(specificTorCertificate)) {
		 * proposalDetailInfo.setTorDate(specificTorCertificate.getDate());
		 * proposalDetailInfo.setIdentificationNo(specificTorCertificate.
		 * getTorIdentificationNumber()); }
		 */

		// Populating using EnvironmentClearanceProjectActivityDetails
		// List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails =
		// environmentClearence.getEnvironmentClearanceProjectActivityDetails();
		// List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails =
		// environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());

		List<EcForm8ProjectActivityDetailsDto> projectActivityDetails = ecForm8ProjectActivityDetailsRepository
				.findDetailByEcId(ecForm8TransferOfTOR.get().getId());

		/**
		 * Getting project activity id and name on behalf od sector code.
		 */
		List<String> projectIds = new ArrayList<>();
		List<String> projectNames = new ArrayList<>();
		/*
		 * projectActivityDetails.stream() .forEach(item -> { Activities activities =
		 * item.getActivities(); projectIds.add(activities.getItem_no() + "/"
		 * +activities.getName()); projectNames.add(activities.getName());
		 * System.out.println("===================" + activities.getId() +
		 * "==========================" + activities.getName()); });
		 */

		projectActivityDetails.stream().forEach(item -> {
			ActivityDto activities = item.getActivityDto();

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

		if (ecForm8TransferOfTOR.get().getMajor_sub_activity_id() != null
				|| ecForm8TransferOfTOR.get().getMajor_activity_id() != null)
			proposalDetailInfo
					.setSector(activitySectorRepository.getSectorName(ecForm8TransferOfTOR.get().getMajor_activity_id(),
							ecForm8TransferOfTOR.get().getMajor_sub_activity_id()));

		// Populating using District
		District district = districtRepository.getDistrictByCode(projectDetails.getMain_state(),
				projectDetails.getMain_district());
		State state = stateRepository.getByStateCode(projectDetails.getMain_state());
		String districtName = district != null ? district.getName() + ", " : "";
		String stateName = state != null ? state.getName() : "";
		String OrganizationStreet = commonFormDetail.getOrganization_street() != null
				? commonFormDetail.getOrganization_street() + ", "
				: "";
		String OrganizationCity = commonFormDetail.getOrganization_city() != null
				? commonFormDetail.getOrganization_city() + ", "
				: "";
		String OrganizationLandmark = commonFormDetail.getOrganization_landmark() != null
				? commonFormDetail.getOrganization_landmark() + ", "
				: "";
		String OrganizationPincode = commonFormDetail.getOrganization_pincode() != null
				? commonFormDetail.getOrganization_pincode()
				: "";

		Integer stateId = commonFormDetail.getOrganization_state();
		State stateOrg = stateRepository.getByStateCode(stateId);
		String stateOrgName = stateOrg != null ? stateOrg.getName() + ", " : "";

		Integer districtId = commonFormDetail.getOrganization_district();
		District districtOrg = districtRepository.getDistrictByCode(stateId, districtId);
		String districtOrgName = districtOrg != null ? districtOrg.getName() + ", " : "";

		String registeredAddress = null;
		if (OrganizationCity != null) {
			registeredAddress = OrganizationStreet + OrganizationCity + districtOrgName + stateOrgName
					+ OrganizationLandmark + OrganizationPincode;
		} else {
			registeredAddress = OrganizationStreet + districtOrgName + stateOrgName + OrganizationLandmark
					+ OrganizationPincode;
		}
		proposalDetailInfo.setRegisteredAddress(registeredAddress);
		proposalDetailInfo.setNameOfOrganization(commonFormDetail.getOrganization_name());
		proposalDetailInfo.setLocationOfProject(districtName + stateName);

		String folderDir = null;
		String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
		String fileName = FileUploadUtil
				.fileNameMaker("ec_tor_transfer" + convertedProposalNo + "_" + proponentApplicationId, "pdf");

		String clearanceTypeId = proponentApplication.getClearance_id().toString();
		String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); // this part still not clear
		String proposalId = proponentApplication.getProposal_id().toString();

		folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
		proposalDetailInfo
				.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
		proposalDetailInfo
				.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
		proposalDetailInfo.setVersion(0);
		proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplication.getMoefccFileNumber()));

		if (ecForm8TransferOfTOR.get().getEc_id() != null)
			proposalDetailInfo
					.setDateOfSubmission(ecForm8TransferOfTOR.get().getEnvironmentClearence().getCreated_on());
		else
			proposalDetailInfo.setDateOfSubmission(ecForm8TransferOfTOR.get().getCreated_on());

		// Transferor and Transferee
		List<String> transfereeRegisteredAddress = new LinkedList<>();

		Integer stateIdTransferee = ecForm8TransferOfTORDetails.getTransferee_state();
		State stateTransferee = stateRepository.getByStateCode(stateIdTransferee);
		String stateTransfereeName = stateTransferee != null ? stateTransferee.getName() + ", " : "";

		Integer districtIdTransferee = ecForm8TransferOfTORDetails.getTransferee_district();
		District districtTransferee = districtRepository.getDistrictByCode(stateIdTransferee, districtIdTransferee);
		String districtTransfereeName = districtTransferee != null ? districtTransferee.getName() + ", " : "";

		Integer stateIdTransferor = ecForm8TransferOfTORDetails.getTransferer_state();
		State stateTransferor = stateRepository.getByStateCode(stateIdTransferor);
		String stateTransferorName = stateTransferor != null ? stateTransferor.getName() + ", " : "";

		Integer districtIdTransferor = ecForm8TransferOfTORDetails.getTransferer_district();
		District districtTransferor = districtRepository.getDistrictByCode(stateIdTransferor, districtIdTransferor);
		String districtTransferorName = districtTransferor != null ? districtTransferor.getName() + ", " : "";

		addStrings(transfereeRegisteredAddress, ecForm8TransferOfTORDetails.getTransferee_name());

		addStrings(transfereeRegisteredAddress, ecForm8TransferOfTORDetails.getTransferee_street());
		addStrings(transfereeRegisteredAddress, districtTransfereeName);
		addStrings(transfereeRegisteredAddress, ecForm8TransferOfTORDetails.getTransferee_city());
		addStrings(transfereeRegisteredAddress, stateTransfereeName);
		addStrings(transfereeRegisteredAddress, ecForm8TransferOfTORDetails.getTransferee_landmark());
		addStrings(transfereeRegisteredAddress, ecForm8TransferOfTORDetails.getTransferee_pincode().toString());

		List<String> transferorRegisteredAddress = new LinkedList<>();
		addStrings(transferorRegisteredAddress, ecForm8TransferOfTORDetails.getTransferer_name());
		addStrings(transferorRegisteredAddress, ecForm8TransferOfTORDetails.getTransferer_street());
		addStrings(transferorRegisteredAddress, districtTransferorName);
		addStrings(transferorRegisteredAddress, ecForm8TransferOfTORDetails.getTransferer_city());
		addStrings(transferorRegisteredAddress, stateTransferorName);
		addStrings(transferorRegisteredAddress, ecForm8TransferOfTORDetails.getTransferer_landmark());
		addStrings(transferorRegisteredAddress, ecForm8TransferOfTORDetails.getTransferer_pincode().toString());

		String transfereeRegisteredAddressStr = Strings.join(",", transfereeRegisteredAddress);
		String transferorRegisteredAddressStr = Strings.join(",", transferorRegisteredAddress);
		// String detailsOfTransferee = "";
		// String detailsOfTransferor = "";
		/*
		 * if (!StringUtils.isEmpty(ecForm8TransferOfTORDetails.getTransferee_name())) {
		 * detailsOfTransferee.concat(ecForm8TransferOfTORDetails.getTransferee_name() +
		 * ", "); }
		 * 
		 * if (!StringUtils.isEmpty(ecForm8TransferOfTORDetails.getTransferer_name())) {
		 * detailsOfTransferor.concat(ecForm8TransferOfTORDetails.getTransferer_name() +
		 * ", "); }
		 */

		transfereeRegisteredAddressStr = transfereeRegisteredAddressStr.replaceAll(", ,", ", ");
		transferorRegisteredAddressStr = transferorRegisteredAddressStr.replaceAll(", ,", ", ");

		proposalDetailInfo.setDetails_of_transferee(transfereeRegisteredAddressStr);
		proposalDetailInfo.setDetails_of_transferor(transferorRegisteredAddressStr);
		log.info(transfereeRegisteredAddressStr);
		log.info(transferorRegisteredAddressStr);

		BeanUtils.copyProperties(ecForm8TransferOfTORDetails, proposalDetailInfo);
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
		// String sCode = null;
		Integer formCode = null;
		String clearanceTypeCode = null;
		log.info(propId + " This is prop Id ");
		log.info("=====================Identification No generation================================");
		Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(propId);

		String identificationNo = ecTorTransferTemplateRepository
				.getIdentificationNo(proponentApplications.get().getProposal_no());

		return identificationNo;
	}

}
