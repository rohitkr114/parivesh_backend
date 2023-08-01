package com.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.backend.repository.postgres.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.client.NotificationTemplateClient;
import com.backend.dto.GetAcoStatus;
import com.backend.dto.UpdatedUser;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.Consultant;
import com.backend.model.Employee;
import com.backend.model.NotificationTemplate;
import com.backend.model.ProjectDetails;
import com.backend.model.User;
import com.backend.response.ResponseHandler;
import com.backend.util.ServerUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProjectDetailsService {

	@Autowired
	ProjectDetailRepository projectDetailRepository;

	@Autowired
	UserEmployeeRepository userEmployeeRepository;

	@Autowired
	ConsultantRepository consultantRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	DistrictRepository	districtRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PagingProjectDetailRepository pagingProjectDetailRepository;

	@Autowired
	private ServerUtil serverUtil;

	@Autowired
	private NotificationTemplateClient notificationTemplateClient;

	public ResponseEntity<Object> saveProject(ProjectDetails projectDetails, UserPrincipal user)
			throws PariveshException {
		try {
			List<ProjectDetails> temp = projectDetailRepository.findAll();
			if (temp.isEmpty()) {
				String singleWindowNumber = "SW/" + 100000 + "/" + String.valueOf(LocalDate.now().getYear());
				projectDetails.setSingle_window_seq(100000);
				projectDetails.setSw_no(singleWindowNumber);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getSingle_window_seq())
						.max(Comparator.comparing(Integer::valueOf)).get();
				if (maxCount != null) {
					projectDetails.setSingle_window_seq(Integer.valueOf(generateSequenceNumber(maxCount)));
					projectDetails.setSw_no(generateSingleWindow(maxCount));
				}
			}
			User user2 = userRepository.findById(user.getId())
					.orElseThrow(() -> new UserNotFoundException("user not found with id"));
			if (user2.getUser_type() != null && user2.getUser_type().equals("Consultant")) {
				Consultant consultant = consultantRepository.findById(user.getId())
						.orElseThrow(() -> new UserNotFoundException("user not found with id"));
				projectDetails.addConsultant(consultant);
			} else {
				Employee employee = userEmployeeRepository.findById(user.getId())
						.orElseThrow(() -> new UserNotFoundException("Employee not found with id"));
				projectDetails.addEmployee(employee);
			}
			ProjectDetails detail = projectDetailRepository.save(projectDetails);

			notify(projectDetails.getName(), projectDetails.getSw_no(), user.getEmail());

			return ResponseHandler.generateResponse("Save project", HttpStatus.OK, "", detail);
		} catch (Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Project for user id " + user.getId());
		}
	}

	public ProjectDetails getProjectbyId(Integer id) {

		return projectDetailRepository.findById(id).orElseThrow(null);
	}

	public ResponseEntity<Object> findAll(Integer id, Pageable pageable) throws PariveshException {
		try {
			Page<ProjectDetails> projectdetail = pagingProjectDetailRepository.findProjectsByUserId(id, pageable);
			if (!projectdetail.isEmpty()) {
				projectdetail.map(this::mapState);
				projectdetail.map(this::mapDistirct);
			}
//		log.info("INFO ------------ findAll PROJECT WITH USER_ID----> " + id + " ----RETRIEVING PROJECT - SUCCESS");
			return ResponseHandler.generateResponse("Find all Projects", HttpStatus.OK, "", projectdetail);
		} catch (Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in FindAll Project for id " + id);
		}
	}

	public ResponseEntity<Object> findAllWithSearch(Integer id, Pageable pageable, String Search)
			throws PariveshException {
		try {
			Page<ProjectDetails> projectdetail = pagingProjectDetailRepository.findProjectsByUserIdWithSearch(id,
					Search, pageable);
			if (!projectdetail.isEmpty()) {
				projectdetail.map(this::mapState);
			}
//		log.info("INFO ------------ findAll PROJECT WITH USER_ID----> " + id + " ----search string ---->" + Search
//				+ " ---- RETRIEVING PROJECT - SUCCESS");
			return ResponseHandler.generateResponse("Find and Search Project", HttpStatus.OK, "", projectdetail);
		} catch (Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in find All WithSearch for id " + id);
		}
	}

	public ProjectDetails mapState(ProjectDetails projectDetails) throws PariveshException {
		try {
			projectDetails.setState(stateRepository.getStateByCode(projectDetails.getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")));
			projectDetails.getState().setDistrictlist(new ArrayList<>());
			if (projectDetails.getUpdated_by() != 1 && projectDetails.getUpdated_by() != null) {
				projectDetails
						.setUpdatedByUser(new UpdatedUser(userService.getLoggedInUser(projectDetails.getUpdated_by())));
			}
//		log.info(
//				"INFO ------------ mapState WITH STATE----> " + projectDetails.getState() + " --- MAP_STATE - SUCCESS");
			return projectDetails;
		} catch (Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in mapState for state " + projectDetails.getState());
		}
	}
	public ProjectDetails mapDistirct(ProjectDetails projectDetails) throws PariveshException {
		try {
			projectDetails.setDistrict(districtRepository.getDistrictByCode(projectDetails.getMain_state(),projectDetails.getMain_district()));

			if (projectDetails.getUpdated_by() != 1 && projectDetails.getUpdated_by() != null) {
				projectDetails
						.setUpdatedByUser(new UpdatedUser(userService.getLoggedInUser(projectDetails.getUpdated_by())));
			}
//		log.info(
//				"INFO ------------ mapState WITH STATE----> " + projectDetails.getState() + " --- MAP_STATE - SUCCESS");
			return projectDetails;
		} catch (Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in mapDistrict for District " + projectDetails.getState());
		}
	}

	public ResponseEntity<Object> findProjectById(Integer id, Integer user_id) throws PariveshException {
		/*
		 * Get Projects by ID with Current User Logged In
		 */
		try {
			ProjectDetails details = projectDetailRepository.findProjectById(id, user_id).orElseThrow(
					() -> new ProjectNotFoundException("Project not found for ID " + id + "and user_id " + user_id));

			// ProjectDetails details =
			// projectDetailRepository.findProjectByIndependentId(id).orElseThrow(() -> new
			// ProjectNotFoundException("Project not found with ID"));
			details.setState(stateRepository.getStateByCode(details.getMain_state())
					.orElseThrow(() -> new ProjectNotFoundException("state not found with code")));
			details.setDistrict(districtRepository.getDistrictByCode(details.getMain_state(),details.getMain_district()));
			details.getState().setDistrictlist(new ArrayList<>());
			if (details.getUpdated_by() != 1 && details.getUpdated_by() != null) {
				details.setUpdatedByUser(new UpdatedUser(userService.getLoggedInUser(details.getUpdated_by())));
			}
//		log.info("INFO ------------ findProjectById PROJECT WITH PROJECT_ID ----> " + id + " ---- USER_ID ---->"
//				+ user_id + " ---- RETRIEVING PROJECT - SUCCESS");
			return ResponseHandler.generateResponse("Find Project by Id", HttpStatus.OK, "", details);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in find Project By Id for user id " + id, e);
		}
	}

	public ResponseEntity<Object> findProjectBySW(String sw) throws PariveshException {
//		log.info("INFO ------------ findProjectBySW WITH SW----> " + sw
//				+ " --- RETRIEVING PROJECT  BY SINGLE WINDOW - SUCCESS");
		try {
			return ResponseHandler.generateResponse("Find Project by SW", HttpStatus.OK, "",
					projectDetailRepository.findProjectBySW(sw));
		} catch (Exception e) {
			// log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in find Project By SW id " + sw);
		}
	}

	public ResponseEntity<Object> findProjectsByUserId(Integer id) throws PariveshException {
//		log.info("INFO ------------ findProjectsByUserId WITH USER_ID----> " + id
//				+ " --- RETRIEVING PROJECT  BY USER_ID - SUCCESS");
		try {
			return ResponseHandler.generateResponse("Find Project by UserId", HttpStatus.OK, "",
					projectDetailRepository.findProjectsByUserId(id));
		} catch (Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Find Project by UserId for id " + id);
		}
	}

	public ResponseEntity<Object> findOwnedProjectsByUserId(Integer id) throws PariveshException {
//		log.info("INFO ------------ findOwnedProjectsByUserId WITH USER_ID----> " + id
//				+ " --- RETRIEVING OWNED PROJECT  BY USER_ID - SUCCESS");
		try {
			return ResponseHandler.generateResponse("Find Owned Project by UserId", HttpStatus.OK, "",
					projectDetailRepository.findOwnedProjectsByUserId(id));
		} catch (Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in find Owned Projects By User Id for id " + id);
		}
	}

	public ResponseEntity<Object> findProjectByUserIdAndProjectId(Integer user_id, int project_id)
			throws PariveshException {
//		log.info("INFO ------------ findProjectByUserIdAndProjectId WITH USER_ID----> " + user_id
//				+ "---- PROJECT_ID---->" + project_id + " --- RETRIEVING PROJECT BY USER_ID AND PROJECT_ID - SUCCESS");
		try {
			return ResponseHandler.generateResponse("Find Owned Project by UserId and Project Id", HttpStatus.OK, "",
					projectDetailRepository.findProjectByUserIdAndProjectid(user_id, project_id));
		} catch (Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Find Owned Project by UserId and Project Id for user id " + user_id);
		}
	}

	public String generateSequenceNumber(Integer maxCount) {

		AtomicInteger sequence = new AtomicInteger(maxCount);
		return String.format("%06d", sequence.addAndGet(1));
	}

	private String generateSingleWindow(Integer maxCount) {
		String singleWindowNumber = "SW/" + generateSequenceNumber(maxCount) + "/"
				+ String.valueOf(LocalDate.now().getYear());
		return singleWindowNumber;

	}

	public void notify(String projectName, String SWNumber, String email) {
		try {
			log.info("=============>>>>>>>>>>>>>Sending Project Addition Mail");
			log.info("PROJECTNAME :{} SWNUMBER :{} EMAIL :{}", projectName, SWNumber, email);

			NotificationTemplate notificationTemplate = notificationTemplateClient.getNotificationTemplate(11);

			String tempMessage = notificationTemplate.getMessage();
			String subject = notificationTemplate.getMessage_type();
			String vmName = notificationTemplate.getTemplateId();

			String message = tempMessage.replace("ProjectName", projectName).replace("SWNumber", SWNumber);

			Map<String, String> body = new HashMap<>();
			body.put("simple_content", message);
			serverUtil.sendEmailNotification(email, body, subject, vmName);

			log.info("=============>>>>>>>>>>>>>Project Addition Mail Sent Successfully");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e);
		}
	}

	public ResponseEntity<Object> saveAcoStatus(Integer id, String status) {
		try {
			ProjectDetails projectdetail1 = pagingProjectDetailRepository.findProjectsByUserId1(id);
			if (status.matches("Accept")) {
				projectdetail1.setAcoProjectStatus("Accepted");
			} else if (status.matches("Reject")) {
				projectdetail1.setAcoProjectStatus("Rejected");
			}
			return ResponseHandler.generateResponse("ACO Project Updated", HttpStatus.OK, "", projectdetail1);
		} catch (

		Exception e) {
			log.error(e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in ACO Project " + id);
		}
	}

	public ResponseEntity<Object> getAcoStatus(Integer id) {
		try {
			ProjectDetails projectdetail1 = pagingProjectDetailRepository.findProjectsByUserId1(id);
			GetAcoStatus getAcoStatus = new GetAcoStatus();
			getAcoStatus.setSwNo(projectdetail1.getSw_no());
			getAcoStatus.setAcoStatus(projectdetail1.getAcoProjectStatus());
			return ResponseHandler.generateResponse("ACO Project Updated", HttpStatus.OK, "", getAcoStatus);
		}catch (

				Exception e) {
					log.error(e.toString() + " " + e.getStackTrace()[0]);
					throw new PariveshException("Error in ACO Project " + id);
				}
	}

}
