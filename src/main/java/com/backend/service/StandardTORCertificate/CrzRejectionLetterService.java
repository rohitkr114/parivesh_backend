package com.backend.service.StandardTORCertificate;

import static com.backend.util.CommonUtils.handleEmpty;
import static com.backend.util.CommonUtils.replaceEmptyWithNA;
import static com.backend.util.CommonUtils.throwException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.model.CommonFormDetail;
import com.backend.model.District;
import com.backend.model.ProjectDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.State;
import com.backend.model.StandardTORCertificate.CrzRejectionCertificate;
import com.backend.model.StandardTORCertificate.CrzRejectionCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DistrictRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.StandardTorCertificate.CrzRejectionCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CrzRejectionLetterService {

	@Autowired
	CrzRejectionCertificateRepository crzRejectionCertificateRepository;

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
	public Object saveCrzRejectionCertificate(CrzRejectionCertificate crzRejectionCertificate,
			HttpServletRequest request, HttpServletResponse response) throws IOException, PariveshException {
		log.info(
				"==================Calling Save API for Standard tor Certificate======================== for proposal: "
						+ crzRejectionCertificate.getProposal_No());
		CrzRejectionCertificate crzRejectionCertificateResult = null;
		// Maintaining History
		if (crzRejectionCertificateRepository.existsById(crzRejectionCertificate.getId())) {
			log.info("==================One Record is already exist for Standard tor Certificate========================");
			Optional<CrzRejectionCertificate> crzRejectionCertificate1 = crzRejectionCertificateRepository.findById(crzRejectionCertificate.getId());
			crzRejectionCertificateResult = crzRejectionCertificate1.get();
			crzRejectionCertificateResult.setIsActive(false);
			crzRejectionCertificateRepository.save(crzRejectionCertificateResult);

			crzRejectionCertificate.setId(null);
		}
		log.info("==================Saved Standard tor Certificate========================");
		int version = ObjectUtils.isEmpty(crzRejectionCertificate.getVersion()) ? 1 : crzRejectionCertificate.getVersion() + 1;
		crzRejectionCertificate.setIsActive(true);
		crzRejectionCertificate.setVersion(version);

		Optional.ofNullable(crzRejectionCertificate.getProposal_No()).orElseThrow(
				() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

		ProponentApplications proponentApplications = proponentApplicationRepository
				.getProponentByIdAndProposalNo(crzRejectionCertificate.getProponentId(),
						crzRejectionCertificate.getProposal_No())
				.orElseThrow(() -> new PariveshException(
						"Data is not found for proponent id: " + crzRejectionCertificate.getProponentId() + " proposal no: "
								+ crzRejectionCertificate.getProposal_No()));
		String folderDir = null;
		try {
			CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
			String clearanceTypeId = proponentApplications.getClearance_id().toString();
			String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); // this part still not
																								// clear
			String proposalId = proponentApplications.getProposal_id().toString();
			folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

		} catch (Exception e) {
			throw new PariveshException("Data is not found for proponent id: " + crzRejectionCertificate.getProponentId()
					+ " proposal no: " + crzRejectionCertificate.getProposal_No());
		}

		String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(crzRejectionCertificate.getProposal_No());
		String fileName = FileUploadUtil
				.fileNameMaker("CRZ_REJECTION" + convertedProposalNo + "_" + crzRejectionCertificate.getProponentId(), "pdf");

		String filePath = FileUploadUtil.generatePdfFromHtml(crzRejectionCertificate.getHtmlContent(), folderDir, fileName,
				crzRejectionCertificate.getProposal_No());
		// }
		crzRejectionCertificate
				.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
		crzRejectionCertificate
				.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
     
		return crzRejectionCertificateRepository.save(crzRejectionCertificate);
	}
	
	public CertProposalDetailInfo getCrzRejectionByPropId(int propId, String proposalNo, String authority) throws ParseException {
		log.info("==============================getting EC detail by ProId===========================");
		String proposalNoParts[] = proposalNo.split("/");
		String authorityCode = proposalNoParts[0];
		String sectorCodeFromPP = proposalNoParts[2];
		CrzRejectionCertificate crzRejectionCert = null;
		crzRejectionCert = crzRejectionCertificateRepository.getStandardCertificateInfoBytPropId(propId, proposalNo);

		CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
		if (!ObjectUtils.isEmpty(crzRejectionCert)) {
			BeanUtils.copyProperties(crzRejectionCert, proposalDetailInfo);
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
				log.info("CRZ-Rejection-certificate: project ID:- " + commonFormDetail.getProject_id());
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
					proposalDetailInfo.setIssuingAuthority(authority);
					proposalDetailInfo.setProponentId(propId);
					proposalDetailInfo.setCafId(proponentApplications.getCaf_id());
					proposalDetailInfo.setProposal_No(proposalNo);
				}
				proposalDetailInfo.setClearanceType(
						applicationsRepository.getById(proponentApplications.getClearance_id()).getGeneral_name());

				String folderDir = null;
				String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
				String fileName = FileUploadUtil.fileNameMaker("CRZ_Rejection_" + convertedProposalNo + "_" + propId,
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

			}
		}

		return proposalDetailInfo;
	}
}
