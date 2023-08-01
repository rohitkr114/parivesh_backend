package com.backend.service.StandardTORCertificate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.clearance.LocalClearance;
import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.District;
import com.backend.model.ProjectDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.State;
import com.backend.model.StandardTORCertificate.CrzGrantCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DistrictRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.StandardTorCertificate.CrzGrantCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

import static com.backend.util.CommonUtils.handleEmpty;
import static com.backend.util.CommonUtils.parseDateInDDMMYYYY;
import static com.backend.util.CommonUtils.replaceEmptyWithNA;
import static com.backend.util.CommonUtils.addStrings;
import static com.backend.util.CommonUtils.throwException;

@Slf4j
@Service
public class CrzGrantCertificateService {

	@Autowired
	CrzGrantCertificateRepository crzGrantCertificateRepository;

	@Autowired
	ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	ProjectDetailRepository projectDetailRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	ApplicationsRepository applicationsRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	Environment environment;

	@Transactional
	public Object saveCrzGrantCertificate(CrzGrantCertificate crzGrantCertificate, HttpServletRequest request,
			HttpServletResponse response) throws IOException, PariveshException {
		log.info(
				"==================Calling Save API for Standard tor Certificate======================== for proposal: "
						+ crzGrantCertificate.getProposal_No());
		CrzGrantCertificate crzGrantCertificateResult = null;
		// Maintaining History
		if (crzGrantCertificateRepository.existsById(crzGrantCertificate.getId())) {
			log.info(
					"==================One Record is already exist for Standard tor Certificate========================");
			Optional<CrzGrantCertificate> crzGrantCertificate1 = crzGrantCertificateRepository
					.findById(crzGrantCertificate.getId());
			crzGrantCertificateResult = crzGrantCertificate1.get();
			crzGrantCertificateResult.setIsActive(false);
			crzGrantCertificateRepository.save(crzGrantCertificateResult);

			crzGrantCertificate.setId(null);
		}
		log.info("==================Saved Standard tor Certificate========================");
		int version = ObjectUtils.isEmpty(crzGrantCertificate.getVersion()) ? 1 : crzGrantCertificate.getVersion() + 1;
		crzGrantCertificate.setIsActive(true);
		crzGrantCertificate.setVersion(version);

		Optional.ofNullable(crzGrantCertificate.getProposal_No()).orElseThrow(
				() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

		// certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
//        EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(standardTorCertificate.getProposal_No());
		ProponentApplications proponentApplications = proponentApplicationRepository
				.getProponentByIdAndProposalNo(crzGrantCertificate.getProponentId(),
						crzGrantCertificate.getProposal_No())
				.orElseThrow(() -> new PariveshException(
						"Data is not found for proponent id: " + crzGrantCertificate.getProponentId() + " proposal no: "
								+ crzGrantCertificate.getProposal_No()));
		String folderDir = null;
		try {
			CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
			String clearanceTypeId = proponentApplications.getClearance_id().toString();
			String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); // this part still not
																								// clear
			String proposalId = proponentApplications.getProposal_id().toString();
			folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

		} catch (Exception e) {
			throw new PariveshException("Data is not found for proponent id: " + crzGrantCertificate.getProponentId()
					+ " proposal no: " + crzGrantCertificate.getProposal_No());
		}

		String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(crzGrantCertificate.getProposal_No());
		String fileName = FileUploadUtil
				.fileNameMaker("CRZ_GRANT" + convertedProposalNo + "_" + crzGrantCertificate.getProponentId(), "pdf");

		// if (standardTorCertificate.getStatus().equalsIgnoreCase("complete")) {
		// File path generation code this could be common
		String filePath = FileUploadUtil.generatePdfFromHtml(crzGrantCertificate.getHtmlContent(), folderDir, fileName,
				crzGrantCertificate.getProposal_No());
		// }
		crzGrantCertificate
				.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
		crzGrantCertificate
				.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
		// Commented by Ashish
		// proponentApplications
		// .setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) +
		// folderDir + "/" + fileName);
		// proponentApplicationRepository.save(proponentApplications);

		return crzGrantCertificateRepository.save(crzGrantCertificate);
	}

	@Transactional
	public CertProposalDetailInfo getCrzGrantByPropId(int propId, String proposalNo) throws ParseException {
		log.info("==============================getting EC detail by ProId===========================");
		String proposalNoParts[] = proposalNo.split("/");
		String authorityCode = proposalNoParts[0];
		String sectorCodeFromPP = proposalNoParts[2];
		CrzGrantCertificate crzGrantCert = null;
		crzGrantCert = crzGrantCertificateRepository.getStandardCertificateInfoBytPropId(propId, proposalNo);

		CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
		if (!ObjectUtils.isEmpty(crzGrantCert)) {
			BeanUtils.copyProperties(crzGrantCert, proposalDetailInfo);
			ProponentApplications proponentApplications = proponentApplicationRepository
					.getProponentByIdAndProposalNo(propId, proposalNo).get();
			if (proponentApplications != null) {
				CommonFormDetail commonFormDetail = commonFormDetailRepository
						.getById(proponentApplications.getCaf_id());
				proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
				proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
			}

		} else {
			java.util.Date date = new java.util.Date();
			DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String submissionDate = outputFormatter.format(date);
			proposalDetailInfo.setTorDate(submissionDate);

//			proposalDetailInfo.setApplicabilityOfGeneralConditions(
//					environmentClearence.isIs_general_condition_specified() ? "YES" : "NO");
//			proposalDetailInfo.setApplicabilityOfSpecificConditions(environmentClearence.getEcProjectDetail().isIs_specific_condition_specified() ? "YES" : "NO");

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getProponentByIdAndProposalNo(propId, proposalNo).get();
			if (proponentApplications != null) {

				CommonFormDetail commonFormDetail = commonFormDetailRepository
						.getById(proponentApplications.getCaf_id());
				proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

				proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());

				proposalDetailInfo.setFileNo(replaceEmptyWithNA(proponentApplications.getMoefccFileNumber()));
				ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id())
						.get();
				log.info("CRZ-Grant-certificate: project ID:- " + commonFormDetail.getProject_id());
				if (commonFormDetail != null) {
					proposalDetailInfo.setProponent(handleEmpty(commonFormDetail.getApplicant_name()));
					proposalDetailInfo.setNameOfProject(handleEmpty(projectDetails.getName()));
					proposalDetailInfo.setCompanyname(handleEmpty(commonFormDetail.getOrganization_name()));
					// proposalDetailInfo.setProponentEmail(handleEmpty(commonFormDetail.getApplicant_email()));
					proposalDetailInfo.setProponentEmail(handleEmpty(commonFormDetail.getOrganization_email()));

					Integer stateIdP = projectDetails.getMain_state();
					throwException(stateIdP, "state");
					State stateP = stateRepository.getByStateCode(stateIdP);
					String stateNameP = stateP != null ? stateP.getName() : "";

					District districtP = districtRepository.getDistrictByCode(stateIdP,
							projectDetails.getMain_district());
					String districtNameP = districtP != null ? districtP.getName() : "";

					Integer stateId = commonFormDetail.getOrganization_state();
					State state = stateRepository.getByStateCode(stateId);
					String stateName = state != null ? state.getName() + ", " : "";

					Integer districtId = commonFormDetail.getOrganization_district();
					District district = districtRepository.getDistrictByCode(stateId, districtId);
					String districtName = district != null ? district.getName() + ", " : "";

					String streetOrg = commonFormDetail.getOrganization_street() != null
							? commonFormDetail.getOrganization_street() + ", "
							: "";
					String cityOrg = commonFormDetail.getOrganization_city() != null
							? commonFormDetail.getOrganization_city() + ", "
							: "";
					String landmarkOrg = commonFormDetail.getOrganization_landmark() != null
							? commonFormDetail.getOrganization_landmark() + ", "
							: "";
					String pinOrg = commonFormDetail.getOrganization_pincode() != null
							? commonFormDetail.getOrganization_pincode()
							: "";

					String registeredAddress = streetOrg + cityOrg + districtName + stateName + landmarkOrg + pinOrg;

					proposalDetailInfo.setRegisteredAddress(registeredAddress);
					proposalDetailInfo.setNameOfOrganization(handleEmpty(commonFormDetail.getOrganization_name()));
					proposalDetailInfo
							.setLocationOfProject(handleEmpty(districtNameP) + ", " + handleEmpty(stateNameP));
					if ("SCZMA".equals(authorityCode)) {
						proposalDetailInfo.setIssuingAuthority("SCZMA");
					} else {
						proposalDetailInfo.setIssuingAuthority("MoEF&CC");
					}
					proposalDetailInfo.setProponentId(propId);
					proposalDetailInfo.setCafId(proponentApplications.getCaf_id());
					proposalDetailInfo.setProposal_No(proposalNo);
				}
				proposalDetailInfo.setClearanceType(
						applicationsRepository.getById(proponentApplications.getClearance_id()).getGeneral_name());

				String folderDir = null;
				String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
				String fileName = FileUploadUtil.fileNameMaker("CRZ_Grant_" + convertedProposalNo + "_" + propId,
						"pdf");

				String clearanceTypeId = proponentApplications.getClearance_id().toString();
				String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); // this part still
																									// not
																									// clear
				String proposalId = proponentApplications.getProposal_id().toString();

				folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
				proposalDetailInfo.setFolderDir(
						environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
				proposalDetailInfo
						.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
				proposalDetailInfo.setVersion(0);
				proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplications.getMoefccFileNumber()));
				proposalDetailInfo.setStateFileNo(CommonUtils.handleEmpty(proponentApplications.getStateFileNumber()));

			}
		}

		return proposalDetailInfo;
	}

}
