package com.backend.service.CrzValidityExtension;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.backend.model.CrzValidityExtension.CRZValidityExtensionStepI;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.CrzValidityExtension.CRZValidityExtensionStepIRepository;
import com.backend.response.ResponseHandler;

@Service
public class CrzValidityExtensionService {

	@Autowired
	private CommonFormDetailRepository commonFormDetailRepository;

	@Autowired
	private CRZValidityExtensionStepIRepository crzValidityExtensionStepIRepository;
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ApplicationsRepository applicationsRepository;

	public ResponseEntity<Object> saveCrzValidityExtensionI(CRZValidityExtensionStepI crzBasicDetails, Integer caf_id,
			HttpServletRequest request) throws PariveshException {
		CommonFormDetail temp = null;
		CRZValidityExtensionStepI temp2 = null;
		try {
			temp = new CommonFormDetail();
			temp = commonFormDetailRepository.findDetailByCafId(caf_id);
			crzBasicDetails.setCommonFormDetail(temp);
			if (crzBasicDetails.getId() != null && crzBasicDetails.getId() != 0) {
				CRZValidityExtensionStepI form = crzValidityExtensionStepIRepository.findById(crzBasicDetails.getId())
						.orElseThrow(() -> new ProjectNotFoundException("CRZ clearance form not found"));
				crzBasicDetails.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));

				return ResponseHandler.generateResponse("Save crz Basic Details form", HttpStatus.OK, null,
						crzValidityExtensionStepIRepository.save(crzBasicDetails));
			}
			List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
			ProponentApplications applications = new ProponentApplications();
			if (tempClearances.isEmpty()) {
				String proposal_no = "IA/" + stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
						.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
						+ "/" + "CRZ" + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
				applications.setProposal_sequence(400000);
				proposal_no = proposal_no.replaceAll("\\s", "");
				applications.setProposal_no(proposal_no);
				crzBasicDetails.setProposal_no(proposal_no);
			} else {
				Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
					applications.setProposal_no(generateProposalNo(maxCount, temp));
					crzBasicDetails.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
				}
			}
			temp2 = new CRZValidityExtensionStepI();
			temp2 = crzValidityExtensionStepIRepository.save(crzBasicDetails);
			Applications app = applicationsRepository.findById(4).get();
			applications.setCaf_id(caf_id);
			applications.setProposal_id(temp2.getId());
			applications.setState(stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());
			applications.setState_id(stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());

			applications.setProjectDetails(temp.getProjectDetails());
			applications.setApplications(app);
			applications.setLast_status(Caf_Status.DRAFT.toString());
			applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
			proponentApplicationRepository.save(applications);
		} catch (Exception e) {
			return ResponseHandler.generateResponse("------------------->>>>", HttpStatus.INTERNAL_SERVER_ERROR, "", e);

		}
		return ResponseHandler.generateResponse("Save crz Basic Details form", HttpStatus.OK, null, temp2);
	}
	
	public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail temp) {
        String cafId = "IA/"
                + stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
                .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                + "/" + "CRZ" + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }
}
