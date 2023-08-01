package com.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant;
import com.backend.dto.ProjectDetailDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.DataAlreadyFoundException;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserAlreadyExistException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.Consultant;
import com.backend.model.ConsultantOrganisation;
import com.backend.model.ForestClearancePatchKmls;
import com.backend.model.MailContent;
import com.backend.model.ProjectDetails;
import com.backend.model.VerificationToken;
import com.backend.repository.postgres.ConsultantOrganisationRepository;
import com.backend.repository.postgres.ConsultantRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.VerificationRepository;
import com.backend.response.ResponseHandler;
import com.backend.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ConsultantService {

	@Autowired
	ConsultantRepository consultantRepository;

	@Autowired
	private Environment environment;

	@Autowired
	ConsultantOrganisationRepository consultantOrganisationRepository;

	@Autowired
	ProjectDetailRepository projectDetailRepository;

	@Autowired
	VerificationRepository verificationRepository;

	@Autowired
	private NotifyClient notifyClient;

	@Autowired
	private PasswordEncoder passEncoder;

	public ResponseEntity<Object> saveConsultant(List<Consultant> consultant, Integer ConsultantOrgansationId) {

		try {
			ConsultantOrganisation temp = consultantOrganisationRepository.getById(ConsultantOrgansationId);
			Set<ConsultantOrganisation> tempOrg = new HashSet<>();
			tempOrg.add(temp);

			List<Consultant> ConsultantTemp = consultant.stream().map(val -> {
				// val.setConsultantOrganisation(temp);

				val.setConsultantOrganisations(tempOrg);
				return val;

			}).collect(Collectors.toList());
			log.info("INFO ------------ saveConsultant consultant found by ID---- ConsultantOrgansationId ----> "
					+ ConsultantOrgansationId + " and SAVED -- SUCCESS");
			List<Consultant> conTemp = consultantRepository.saveAll(ConsultantTemp);

			return ResponseHandler.generateResponse("Save Consultants ", HttpStatus.OK, "", conTemp);
		} catch (Exception ex) {
			log.info(
					"ERROR ------------ INTERNAL_SERVER_ERROR : saveConsultant consultant not found by ID ---- ConsultantOrgansationId ---->"
							+ ConsultantOrgansationId + "and NOT SAVED - FAILURE");
			return ResponseHandler.generateResponse("Save Consultants ", HttpStatus.INTERNAL_SERVER_ERROR, "",
					ex.getMessage());
		}

	}

	public ResponseEntity<Object> getConsultant(Integer ConsultantId) {
		try {
			Consultant consultant = consultantRepository.getById(ConsultantId);
			log.info("INFO ------------ getConsultant consultant retrieved ---- ConsultantId ----> " + ConsultantId
					+ "- SUCCESS");
			return ResponseHandler.generateResponse("Get Consultant", HttpStatus.OK, "", consultant);
		} catch (Exception ex) {
			log.info(
					"ERROR ------------ INTERNAL_SERVER_ERROR : getConsultant consultant not found by ID ---- ConsultantId ----> "
							+ ConsultantId + "- FAILURE");
			return ResponseHandler.generateResponse("Get Consultant ", HttpStatus.INTERNAL_SERVER_ERROR, "",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> deleteConsultantorganisation(Integer ConsultantId) {
		try {
			Consultant consultant = consultantRepository.getById(ConsultantId);
			consultant.setIs_active(false);
			consultant.setIs_delete(true);
			log.info("INFO ------------ deleteConsultantorganisation consultant ---- ConsultantId ----> " + ConsultantId
					+ "- DELETED - SUCCESS");
			Consultant temp = consultantRepository.save(consultant);
			return ResponseHandler.generateResponse("Delete Consultant", HttpStatus.OK, "", temp);
		} catch (Exception ex) {
			log.info(
					"ERROR ------------ INTERNAL_SERVER_ERROR : deleteConsultantorganisation consultant not found by ID ---- ConsultantId ----> "
							+ ConsultantId + "- and NOT DELETED - FAILURE");
			return ResponseHandler.generateResponse("Delete Consultant", HttpStatus.INTERNAL_SERVER_ERROR, "",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> activateConsultant(List<Integer> consultants) {
		try {
			List<Consultant> Temp = new ArrayList<Consultant>();
			List<Integer> ConsultantTemp = consultants.stream().map(val -> {
				Consultant conTemp = consultantRepository.findById(val)
						.orElseThrow(() -> new UserNotFoundException("Consultant Not Found"));
				log.info(
						"ERROR ------------ activateConsultant consultant not found by ID and NOT ACTIVATED - FAILURE");
				conTemp.setIs_active(true);
				conTemp.setIs_delete(false);
				/*
				 * String genPass = StringUtil.generateRandomPassword(7);
				 * conTemp.setPassword(passEncoder.encode(genPass));
				 * 
				 * 
				 * String token = UUID.randomUUID().toString(); createVerificationToken(conTemp,
				 * token); String url = AppConstant.tokenUrl + token +
				 * "&email=Sunil.Yadav2@in.ey.com"; MailContent ob1 = new MailContent();
				 * 
				 * ob1.setSubject(AppConstant.confirmMailSubject);
				 * 
				 * ob1.setVmname(AppConstant.confirmTemplate);
				 * 
				 * ob1.setTo("Sunil.Yadav2@in.ey.com"); // ob1.setTo(val.getEmail());
				 * 
				 * Map<String, String> body = new HashMap<>(); body.put("entityType",
				 * "Consultant"); // body.put("recieverMail", val.getEmail());
				 * body.put("recieverMail", conTemp.getEmail()); body.put("password", genPass);
				 * body.put("url", url); ob1.setBody(body);
				 * 
				 * // Kafka Producer Publishes the Object on the Topic
				 * notifyClient.sendMail(ob1);
				 */

				Temp.add(conTemp);

				return val;
			}).collect(Collectors.toList());
			log.info("INFO ------------ activateConsultant consultant found by ID and ACTIVATED - SUCCESS");
			List<Consultant> conTemp = consultantRepository.saveAll(Temp);

			for (Consultant consultant : conTemp) {

				String genPass = StringUtil.generateRandomPassword(7);
				consultant.setPassword(passEncoder.encode(genPass));

				/*
				 * Email For Activation
				 */

				String token = UUID.randomUUID().toString();
				createVerificationToken(consultant, token);
				String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + consultant.getEmail()
						+ "&username=" + consultant.getName_of_Entity();
				// String url = environment.getProperty(AppConstant.tokenUrl) + token +
				// "&email=Sunil.Yadav2@in.ey.com" + "&username=" +
				// consultant.getName_of_Entity();

				// String url = AppConstant.tokenUrl + token + "&email=" + val.getEmail();

				MailContent ob1 = new MailContent();

				ob1.setSubject(AppConstant.confirmMailSubjectConsultant);

				ob1.setVmname(AppConstant.confirmTemplateConsultant);

				if (environment.getProperty("environment.prod").equals("yes")) {
					ob1.setTo(consultant.getEmail());
				} else {
					ob1.setTo("Sunil.Yadav2@in.ey.com");
				}

				Map<String, String> body = new HashMap<>();
				body.put("entityType", "Consultant"); // body.put("recieverMail", val.getEmail());
				body.put("recieverMail", consultant.getEmail());
				body.put("password", genPass);
				body.put("url", url);
				ob1.setBody(body);

				// Kafka Producer Publishes the Object on the Topic
				log.info(
						"INFO ------------ activateConsultant Sending ACTIVATION MAIL to consultant ----consultant.getEmail()---->"
								+ consultant.getEmail() + " - SUCCESS");

				notifyClient.sendMail(ob1);

			}
			log.info("INFO ------------ activateConsultant ACTIVATE consultant - SUCCESS");
			return ResponseHandler.generateResponse("Activate Consultant", HttpStatus.OK, "", conTemp);
		} catch (Exception ex) {
			log.info("ERROR ------------ activateConsultant consultant NOT ACTIVATED - FAILURE");
			return ResponseHandler.generateResponse("Activate Consultant", HttpStatus.INTERNAL_SERVER_ERROR, "",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> updateConsultant(Consultant consultant) {
		try {
			log.info("INFO ------------ updateConsultant UPDATE ----consultant ----> " + consultant + "- SUCCESS");
			Consultant conTemp = consultantRepository.save(consultant);
			return ResponseHandler.generateResponse("Update Consultant", HttpStatus.OK, "", conTemp);
		} catch (Exception ex) {
			log.info(
					"ERROR ------------ updateConsultant ----consultant ----> " + consultant + "NOT UPDATED - FAILURE");
			return ResponseHandler.generateResponse("Update Consultant", HttpStatus.INTERNAL_SERVER_ERROR, "",
					ex.getMessage());
		}
	}

	/*
	 * public ResponseEntity<Object> getConsultantFromLoggedInUser(){
	 * 
	 * // Get All the Organisations Integer userId; try {
	 * 
	 * List<ConsultantOrganisation> organisations=new ArrayList<>();
	 * List<Consultant> consultants=new ArrayList<>();
	 * 
	 * userId=getLoggedInUserId(); Consultant
	 * con=consultantRepository.getById(userId);
	 * organisations.add(con.getConsultantOrganisation());
	 * 
	 * Iterator<ConsultantOrganisation> itrConOrg=organisations.iterator();
	 * 
	 * while(itrConOrg.hasNext()) { ConsultantOrganisation contemp=itrConOrg.next();
	 * consultants.addAll(consultantRepository.findAllConsultantsbyOrgId(contemp.
	 * getId())); }
	 * 
	 * return ResponseHandler.
	 * generateResponse("Get Consultants from LoggedIn User Organisation",
	 * HttpStatus.OK, "", consultants); } catch(Exception ex) { return
	 * ResponseHandler.generateResponse("Update Consultant",
	 * HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage()); } }
	 */

	public ResponseEntity<Object> getConsultantList(String active, UserPrincipal currentuser) {
		Integer UserId = currentuser.getId();

		if (consultantRepository.UserIdIsPresent(UserId) == null) {
			log.info("ERROR ------------ BAD_REQUEST: getConsultantList consultant WITH userID ----> " + UserId
					+ " ----not found - FAILURE");
			return ResponseHandler.generateResponse("No Consultant Exists", HttpStatus.BAD_REQUEST, "", "");
		} else {
			log.info("INFO ------------ getConsultantList consultant WITH userID ----> " + UserId
					+ " ---- FOUND - SUCCESS");
			String orgId = consultantRepository.findOrganisationId(UserId);
			if (active != null) {
				log.info("INFO ------------ getConsultantList Get Consultant List From Logged In User userID ----> "
						+ UserId + " ----- orgId ---->" + orgId + "-SUCCESS");
				List<Consultant> consultants;
				consultants = consultantRepository.get_all_ConsultantsByStatus(orgId, active);

				consultants.forEach(value -> {
					value.setProjectDetailDto(value.getProjectDetails().stream().map(ele -> {
						ProjectDetailDto dto = new ProjectDetailDto(ele.getId(), ele.getName());
						return dto;
					}).collect(Collectors.toSet()));
					value.setProjectDetails(new HashSet<>());
				});
				return ResponseHandler.generateResponse("Get Consultant List From Logged In User", HttpStatus.OK, "",
						consultants);
			} else {
				log.info("INFO ------------ getConsultantList Get Employee List From Logged In userID ----> " + UserId
						+ " ----- orgId ---->" + orgId + " - SUCCESS");
				// return ResponseHandler.generateResponse("Get Employee List From Logged In
				// User", HttpStatus.OK, "",
				// consultantRepository.get_all_Employees(orgId));

				List<Consultant> consultants;
				consultants = consultantRepository.get_all_Employees(orgId);

				consultants.forEach(value -> {
					value.setProjectDetailDto(value.getProjectDetails().stream().map(ele -> {
						ProjectDetailDto dto = new ProjectDetailDto(ele.getId(), ele.getName());
						return dto;
					}).collect(Collectors.toSet()));
					value.setProjectDetails(new HashSet<>());
				});

				return ResponseHandler.generateResponse("Get Employee List From Logged In User", HttpStatus.OK, "",
						consultants);
			}
			
		}
	}

	public ResponseEntity<Object> getAllConsultants(Integer orgId) {
		try {
			if (orgId == null) {
				log.info("INFO ----------- getAllConsultants Get Consultant List WITH OrgID---->" + orgId
						+ " ---- NULL - SUCCESS");
				return ResponseHandler.generateResponse("Get Consultant Lists ", HttpStatus.OK, "",
						consultantRepository.findAll());
			} else {
				log.info("INFO ------------ getAllConsultants Get Consultant List WITH OrgID---->" + orgId
						+ " ----  not NULL - SUCCESS");
				return ResponseHandler.generateResponse("Get Consultant Lists ", HttpStatus.OK, "",
						consultantRepository.getConsultantByOrgId(orgId));
			}
		} catch (Exception ex) {
			log.info(
					"ERROR ------------ EXPECTATION_FAILED: getAllConsultants Consultant List not RETRIEVED for OrgID---->"
							+ orgId + " ----  - FAILURE");
			return ResponseHandler.generateResponse("Get Consultant Lists ", HttpStatus.EXPECTATION_FAILED, "",
					ex.getMessage());
		}

	}

	public Consultant addConsultant(Integer id, Integer employeeId) {
		Consultant temp = projectDetailRepository.findById(id).map(project -> {
			Consultant temp2 = consultantRepository.findById(employeeId)
					.orElseThrow(() -> new UserNotFoundException("Consultant not found"));
			log.info("ERROR ------------ addConsultant Consultant WITH projectID ---->" + id
					+ " ---- NOT FOUND for employeeId ---->" + employeeId + " - FAILURE");
			ProjectDetails projectDetails = projectDetailRepository.findProjectByUserIdAndProjectid(temp2.getEntityid(),
					id);
			if (projectDetails != null) {
				log.info("ERROR ------------ addConsultant Consultant WITH projectID ---->" + id
						+ " ---- for employeeId ---->" + employeeId + "ALREADY MAPPED - REDUNDANCY");

				throw new UserAlreadyExistException("Consultant already mapped with Project");
			}
			project.addConsultant(temp2);
			log.info("INFO ------------ addConsultant ProjectID assigned to CONSULTANT ---- projectID ---->" + id
					+ " ----for employeeId ---->" + employeeId + " - SUCCESS");
			projectDetailRepository.save(project);
			return temp2;
		}).orElseThrow(() -> new ProjectNotFoundException("project not found with project id"));
		log.info("ERROR ------------ addConsultant PROJECT WITH projectID ---->" + id + " ---- NOT FOUND - FAILURE");
		return temp;
	}

	public ProjectDetails deleteConsultant(Integer id, Integer userId) {
		ProjectDetails temp = projectDetailRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("project details not found with id"));
		temp.removeConsultant(userId);
		return projectDetailRepository.save(temp);
	}

	public List<Consultant> addConsultants(Integer id, List<Integer> consultantIds) {
		try {
			List<Consultant> temp = consultantIds.stream().map(e -> addConsultant(id, e)).collect(Collectors.toList());

			return temp;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new DataAlreadyFoundException("Consultant already mapped with Project id- " + id, e);
		}
	}

	public ResponseEntity<Object> addConsultantToProject(Integer id, Integer consultantId) {
		try {
			Consultant tempConsultant = consultantRepository.findById(consultantId)
					.orElseThrow(() -> new UserNotFoundException("Consultant Not Found"));
			log.info("------------In addConsultantToProject ------------");

			ProjectDetails project = projectDetailRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("Project Not found the Given id"));

			Integer count = projectDetailRepository.deleteProjectMappingWithConsultant(id);
			log.info("------------Deleted Records are ------------" + count);

			project.addConsultant(tempConsultant);
			projectDetailRepository.save(project);
			// projectDetailRepository.save(project);
			return ResponseHandler.generateResponse("New Consultant Mapped To Project", HttpStatus.OK, "",
					tempConsultant);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new DataAlreadyFoundException("addConsultantToProject- " + id, e);
		}
	}

	public List<ProjectDetails> deleteConsultants(Integer id, List<Integer> employeeIds) {
		List<ProjectDetails> temp = employeeIds.stream().map(e -> deleteConsultant(id, e)).collect(Collectors.toList());
		return temp;
	}

	public void createVerificationToken(Consultant user, String token) {
		VerificationToken myToken = new VerificationToken(user, token);
		verificationRepository.save(myToken);
	}

	public ResponseEntity<Object> getConsultantByProjectId(Integer project_id) {
		return ResponseHandler.generateResponse("Get Consultant Lists ", HttpStatus.OK, "",
				consultantRepository.findConsultantByProjectId(project_id));
	}

}
