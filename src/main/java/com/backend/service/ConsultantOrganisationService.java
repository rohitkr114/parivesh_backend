package com.backend.service;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.client.ConsultantAPI;
import com.backend.client.ConsultantOrganisationAPI;
import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant;
import com.backend.dto.ConsultOrganisation;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.Consultant;
import com.backend.model.ConsultantOrg;
import com.backend.model.ConsultantOrganisation;
import com.backend.model.ConsultantOrganisationQCIDump;
import com.backend.model.Employee;
import com.backend.model.MailContent;
import com.backend.model.User;
import com.backend.model.VerificationToken;
import com.backend.repository.postgres.ConsultantOrganisationQCIDumpRepository;
import com.backend.repository.postgres.ConsultantOrganisationRepository;
import com.backend.repository.postgres.ConsultantRepository;
import com.backend.repository.postgres.UserEmployeeRepository;
import com.backend.repository.postgres.UserRepository;
import com.backend.repository.postgres.VerificationRepository;
import com.backend.response.ResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Configuration
@EnableScheduling
public class ConsultantOrganisationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	ConsultantRepository consultantRepository;

	@Autowired
	UserEmployeeRepository userEmployeeRepository;

	@Autowired
	private Environment environment;

	@Autowired
	private UserEmployeeRepository employeeRepository;

	@Autowired
	private ConsultantOrganisationRepository consultantOrganisationRepository;

	@Autowired
	private ConsultantOrganisationQCIDumpRepository consultantOrganisationQCIDumpRepository;

	@Autowired
	private VerificationRepository verificationRepository;

	@Autowired
	ConsultantAPI consultantAPI;

	@Autowired
	ConsultantOrganisationAPI consultantOrganisationAPI;

	@Autowired
	private NotifyClient notifyClient;

	@Autowired
	private PasswordEncoder passEncoder;

	public ResponseEntity<Object> saveConsultantOrganisation(List<ConsultantOrganisation> consultantOrganisation) {

		try {
			List<ConsultantOrganisation> consultantOrgTemp = consultantOrganisationRepository
					.saveAll(consultantOrganisation);
			log.info("INFO ------------ saveConsultantOrganisation SAVED - SUCCESS");
			return ResponseHandler.generateResponse("Save Consultant Organisation", HttpStatus.OK, "",
					consultantOrgTemp);
		} catch (Exception ex) {
			log.info("INFO ------------ INTERNAL_SERVER_ERROR: saveConsultantOrganisation NOT SAVED - FAILURE");
			return ResponseHandler.generateResponse("Save Consultant Organisation", HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception", ex.getMessage());
		}
	}

	public ResponseEntity<Object> getQCIConsultantOrganisation(String ConsultantOrgId, Integer step,
			HttpServletRequest request) {
		try {

			/*
			 * Whether Exist in Database
			 */
			log.info("INFO ------------ INSIDE getQCIConsultantOrganisation ---- ConsultantOrgId---->"
					+ ConsultantOrgId);

			//IF CONSULTANT IS NOT MAPPED IN OUR DB
			if (consultantOrganisationRepository.findByOrg_id(ConsultantOrgId) == null) {

				/*
				 * Feign Client API Call For fetching the Organisation
				 */

				String OrgContent = consultantOrganisationAPI.getQCIConsutlant(ConsultantOrgId);

				ObjectMapper mapper = new ObjectMapper();
				List<ConsultOrganisation> organisations = mapper.readValue(OrgContent,
						new TypeReference<List<ConsultOrganisation>>() {
						});

				Iterator<ConsultOrganisation> itrConOrg = organisations.iterator();

				while (itrConOrg.hasNext()) {
					ConsultOrganisation con = itrConOrg.next();
					if (con.getOrgId().equals(ConsultantOrgId) && (con.getValididityFlag().equals("Active"))) {

						if (con.getEmailId().contains(";")) {
							con.setEmailId(con.getEmailId().split(";")[0]);
						} else if (con.getEmailId().contains(",")) {
							con.setEmailId(con.getEmailId().split(",")[0]);
						} else if (con.getEmailId().isEmpty()) {
							return ResponseHandler.generateResponse("Get Consultant Organisation",
									HttpStatus.BAD_REQUEST, "",
									"Email Id doesnot exist against the given Organisation");
						}
						if (con.getLandlineNo().contains(",")) {
							con.setLandlineNo(con.getLandlineNo().split(",")[0].replaceAll("\\s", ""));
						}
						ConsultantOrganisation conOrganisation = new ConsultantOrganisation(con);

						/*
						 * Checking For Step 1
						 */

						if (step == 1) {
							if (conOrganisation.getAccreditation_validity().compareTo(new Date()) < 0
									|| conOrganisation.getAccreditation_validity().compareTo(new Date()) == 0) {
								return ResponseHandler.generateResponse("Consultant Organisation expired",
										HttpStatus.NOT_ACCEPTABLE, "", conOrganisation);
							}
							return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.OK, "",
									conOrganisation);
						} else {
							ConsultantOrganisation organisation = consultantOrganisationRepository
									.save(conOrganisation);
							Consultant consultant = new Consultant(con, "Consultant", request);
							consultant.addConsultantOrganisation(organisation);
							Consultant consultant2 = new Consultant();
							String genPass = generateRandomPassword(7);
							if (userRepository.findRecordByEmail(consultant.getEmail()).isEmpty()) {

//								logger.info("--Email Id for Owner Consultant available--");
								log.info(
										"INFO ------------ getQCIConsultantOrganisation EMAIL for owner consultant---- ConsultantOrgId---->"
												+ ConsultantOrgId + "---- consultant.getEmail()----->"
												+ consultant.getEmail() + "----AVAILABLE ");
								consultant.setPassword(passEncoder.encode(genPass));
								if (consultant.getEmail().contains(";")) {
									consultant.setEmail(consultant.getEmail().split(";")[0]);
								} else if (consultant.getEmail().contains(",")) {
									consultant.setEmail(consultant.getEmail().split(",")[0]);
								}
								consultant.setEmailId(consultant.getEmail());
								consultant2 = consultantRepository.save(consultant);

								/*
								 * Sending Mail to the Owner
								 */

								String token = UUID.randomUUID().toString();
								createVerificationToken(consultant2, token);
//								String url = AppConstant.tokenUrl + token + "&email=" + consultant2.getEmail()
//								String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=sunil.yadav2@in.ey.com"
//										+ "&username=" + consultant2.getName_of_Entity();
								String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email="
										+ consultant2.getEmail() + "&username=" + consultant2.getName_of_Entity();
								MailContent ob1 = new MailContent();
								ob1.setSubject(AppConstant.confirmMailSubjectConsultant);
								ob1.setVmname(AppConstant.confirmTemplateConsultant);

								if (environment.getProperty("environment.prod").equals("yes")) {
									ob1.setTo(consultant.getEmail());
								} else {
									ob1.setTo("sunil.yadav2@in.ey.com");
								}
								Map<String, String> body = new HashMap<>();

								body.put("entityType", "Consultant");

								// body.put("recieverMail", consultant.getEmail());
								body.put("recieverMail", consultant.getEmail());
								body.put("password", genPass);
								body.put("url", url);
								ob1.setBody(body);

								/*
								 * Kafka Producer Publishes the Object on the Topic
								 */

								notifyClient.sendMail(ob1);
							} else {
								List<User> usertemp = userRepository.findRecordByEmail(consultant.getEmail());
								if (usertemp.size() >= 1) {
									User usertemp2 = usertemp.get(0);
									String type = usertemp2.getUser_type();
									Consultant conTemp = consultantRepository.findById(usertemp2.getEntityid())
											.orElseThrow(() -> new UserNotFoundException("user not found with id"));

//									logger.info("--User of type " + type + " present with the Email Id--");
									log.info("INFO ------------ getQCIConsultantOrganisation User of type " + type
											+ " present with the Email Id---- ConsultantOrgId---->" + ConsultantOrgId
											+ "---- consultant.getEmail()----->" + consultant.getEmail() + "----");

									if (type.equals("Consultant")) {
										conTemp.addConsultantOrganisation(organisation);
										conTemp.setEmailId(consultant.getEmail());
										consultant2 = consultantRepository.save(conTemp);
									}
								}
							}
							if (!(con.getIndividuals().isEmpty())) {
								con.getIndividuals().forEach(ind -> {
									Consultant consult = new Consultant(ind, "Consultant", request);
									if (!(consult.getEmail().isEmpty())) {
//										logger.info("--Email Id for Consultant is not empty--");
										log.info(
												"INFO ------------ getQCIConsultantOrganisation Email Id for Consultant is not empty ---- ConsultantOrgId---->"
														+ ConsultantOrgId + "---- consultant.getEmail()----->"
														+ consultant.getEmail() + "----:  EMAIL UNAVAILABLE");
										consult.addConsultantOrganisation(organisation);

										// consult.setConsultantOrganisation(organisation);

										if (consult.getEmail().contains(";")) {
											consult.setEmail(consult.getEmail().split(";")[0]);
										} else if (consult.getEmail().contains(",")) {
											consult.setEmail(consult.getEmail().split(",")[0]);
										}
										if (userRepository.findRecordByEmail(consult.getEmail()).isEmpty()) {
//											logger.info("--Email Id for Consultant available--");
											log.info(
													"INFO ------------ getQCIConsultantOrganisation Email Id for Consultant ---- ConsultantOrgId---->"
															+ ConsultantOrgId + "---- consultant.getEmail()----->"
															+ consultant.getEmail() + "----AVAILABLE");
											consult.setEmailId(consult.getEmail());
											consultantRepository.save(consult);
										} else {
											List<User> usertemp = userRepository.findRecordByEmail(consult.getEmail());
											if (usertemp.size() >= 1) {
												User usertemp2 = usertemp.get(0);
												String type = usertemp2.getUser_type();
												Consultant conTemp = consultantRepository
														.findById(usertemp2.getEntityid())
														.orElseThrow(() -> new UserNotFoundException(
																"user not found with id"));

//												logger.info("--For Registration of Consultant User of type " + type
//														+ " present with the Email Id--");
												log.info(
														"INFO ------------ getQCIConsultantOrganisation For Registration of Consultant User of type "
																+ type
																+ "present with the Email Id---- ConsultantOrgId---->"
																+ ConsultantOrgId + "---- consultant.getEmail()----->"
																+ consultant.getEmail() + "----");
												if (type.equals("Consultant")) {
													conTemp.addConsultantOrganisation(organisation);
													conTemp.setEmailId(consult.getEmail());
													consultantRepository.save(conTemp);
												}
											}
										}
									} else
										log.info(
												"INFO ------------ getQCIConsultantOrganisation Skipped Consultant as Email Id for Consultant Doesnot Exist---- ConsultantOrgId---->"
														+ ConsultantOrgId + "---- consultant.getEmail()----->"
														+ consultant.getEmail() + "----");
								});
							}
							Map<String, Object> user = new HashMap<>();
							user.put("consultantId", consultant2.getEntityid());
							user.put("is_owner", consultant2.getIs_owner());
							return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.OK, "",
									user);
						}

					}
				}

				return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.BAD_REQUEST, "",
						"Please enter the valid Organisation Id");
			} else {
				return ResponseHandler.generateResponse("Consultant Organisation already present", HttpStatus.CONFLICT,
						"", consultantOrganisationRepository.findByOrg_id(ConsultantOrgId));
			}
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception", "Please enter the valid ORGID");
		}
	}

	/*
	 * Sync Consultant Organisation
	 */
	public ResponseEntity<Object> syncQCIConsultantOrganisation(String ConsultantOrgId, UserPrincipal currentUser,
			HttpServletRequest request) {
		try {

			/*
			 * Whether Exist in Database
			 */
			log.info("INFO ------------ INSIDE SyncQCIConsultantOrganisation ---- ConsultantOrgId---->"
					+ ConsultantOrgId);
			if (ConsultantOrgId == null)
				ConsultantOrgId = getLoggedConsultantInOrganisation(currentUser.getId());

			ConsultantOrganisation ConOrg = consultantOrganisationRepository.findByOrg_id(ConsultantOrgId);
			if (ConOrg != null) {

				/*
				 * Feign Client API Call For fetching the Organisation
				 */
				log.info("INFO ------------ Consultant Organisation found ---->");
				String OrgContent = consultantOrganisationAPI.getQCIConsutlant(ConsultantOrgId);

				ObjectMapper mapper = new ObjectMapper();
				List<ConsultOrganisation> organisations = mapper.readValue(OrgContent,
						new TypeReference<List<ConsultOrganisation>>() {
						});

				Iterator<ConsultOrganisation> itrConOrg = organisations.iterator();

				while (itrConOrg.hasNext()) {
					log.info("INFO ------------Consultant Organisation While Loop");
					ConsultOrganisation con = itrConOrg.next();
					if (con.getOrgId().equals(ConsultantOrgId) && (con.getValididityFlag().equals("Active"))) {

						if (con.getEmailId().contains(";")) {
							con.setEmailId(con.getEmailId().split(";")[0]);
						} else if (con.getEmailId().contains(",")) {
							con.setEmailId(con.getEmailId().split(",")[0]);
						} else if (con.getEmailId().isEmpty()) {
							return ResponseHandler.generateResponse("Get Consultant Organisation",
									HttpStatus.BAD_REQUEST, "",
									"Email Id doesnot exist against the given Organisation");
						}
						if (con.getLandlineNo().contains(",")) {
							con.setLandlineNo(con.getLandlineNo().split(",")[0].replaceAll("\\s", ""));
						}

						/*
						 * Updating the Consultant Organisation
						 */
						log.info("INFO ------------Updating Consultant Organisation " + con.getOrgId());
						ConOrg.setName(con.getOrganizationName());
						ConOrg.setAccreditation_No(con.getAccreditationNo());
						ConOrg.setAddress(con.getAddress());
						ConOrg.setCategory(con.getCategory());
						ConOrg.setValidity_flag(con.getValididityFlag());
						ConOrg.setAccreditation_sectors(con.getSectorsOfAccreditation());
						ConOrg.setPan(con.getPanNo());
						ConOrg.setEmail(con.getEmailId());
						ConOrg.setHead(con.getOrganizationHead());
						ConOrg.setLandline_no(con.getLandlineNo());
						ConOrg.setMobile_no(con.getMobileNo());
						ConOrg.setDesignation(con.getDesignation());
						try {
							if (con.getValidityOfAccreditation().equals("NA")) {
								ConOrg.setAccreditation_validity(new Date());
							} else if (con.getValidityOfAccreditation().contains("AM")) {
								ConOrg.setAccreditation_validity(new Date(new SimpleDateFormat("MM/d/yyyy")
										.parse(con.getValidityOfAccreditation().split("\\s+")[0]).getTime()));
							} else {
								ConOrg.setAccreditation_validity(new Date(new SimpleDateFormat("MM/d/yyyy")
										.parse(con.getValidityOfAccreditation()).getTime()));
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}

						ConsultantOrganisation organisation = consultantOrganisationRepository.save(ConOrg);

						Consultant consultant = new Consultant(con, "Consultant", request);

						consultant.addConsultantOrganisation(organisation);
						Consultant consultant2 = new Consultant();
						String genPass = generateRandomPassword(7);
						List<User> usertemp = userRepository.findRecordByEmail(consultant.getEmail());
						if (usertemp.isEmpty()) {

							/*
							 * Consultant not exist: New Consultant
							 */
							log.info("INFO ------------ Case Owner Consultant doesnot exist: ---->");
							log.info("INFO ------------ for Owner consultant of Consultant Organisation---->"
									+ ConsultantOrgId);
							consultant.setPassword(passEncoder.encode(genPass));
							if (consultant.getEmail().contains(";")) {
								consultant.setEmail(consultant.getEmail().split(";")[0]);
							} else if (consultant.getEmail().contains(",")) {
								consultant.setEmail(consultant.getEmail().split(",")[0]);
							}
							consultant.setEmailId(consultant.getEmail());
							consultant2 = consultantRepository.save(consultant);

							/*
							 * Sending Mail to the Owner
							 */

							String token = UUID.randomUUID().toString();
							createVerificationToken(consultant2, token);
							String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email="
									+ consultant2.getEmail() + "&username=" + consultant2.getName_of_Entity();
							MailContent ob1 = new MailContent();
							ob1.setSubject(AppConstant.confirmMailSubjectConsultant);
							ob1.setVmname(AppConstant.confirmTemplateConsultant);

							if (environment.getProperty("environment.prod").equals("yes")) {
								ob1.setTo(consultant.getEmail());
							} else {
								ob1.setTo("sunil.yadav2@in.ey.com");
							}
							Map<String, String> body = new HashMap<>();

							body.put("entityType", "Consultant");

							// body.put("recieverMail", consultant.getEmail());
							body.put("recieverMail", consultant.getEmail());
							body.put("password", genPass);
							body.put("url", url);
							ob1.setBody(body);

							/*
							 * Kafka Producer Publishes the Object on the Topic
							 */

							notifyClient.sendMail(ob1);
						} else {
							// List<User> usertemp =
							// userRepository.findRecordByEmail(consultant.getEmail());
							log.info("INFO ------------Consultant Owner Already Present");
							if (usertemp.size() >= 1) {
								User usertemp2 = usertemp.get(0);
								String type = usertemp2.getUser_type();
								if (type.equals("Consultant")) {
									log.info("INFO ------------Consultant Type User");
									Consultant conTemp = consultantRepository.findById(usertemp2.getEntityid())
											.orElseThrow(() -> new UserNotFoundException("user not found with id"));
									log.info(
											"INFO ------------Owner Consultant already present ---- Updating the Owner Consultantfor Consultant Organisation ---->"
													+ ConsultantOrgId);

									/*
									 * Updating the Existing Consultant
									 */

									log.info(
											"INFO ------------User is of Type of Consultant --  Updating the Consultant ---->");
									conTemp.addConsultantOrganisation(organisation);
									conTemp.setName_of_Entity(consultant.getName_of_Entity());
									conTemp.setRole(consultant.getRole());
									conTemp.setCategory(consultant.getCategory());
									conTemp.setAccrediation_sectors(consultant.getAccrediation_sectors());
									conTemp.setAccrediation_validity(consultant.getAccrediation_validity());
									conTemp.setValidity_flag(consultant.getValidity_flag());
									conTemp.setPan(consultant.getPan());
									conTemp.setMobile(consultant.getMobile());
									conTemp.setMobileNumber(consultant.getMobile());
									conTemp.setIs_owner(true);

									// conTemp.setEmailId(consultant.getEmail());

									log.info(
											"INFO ------------Owner Consultant already present ---- Before Updating the Owner Consultant: ----> Owner Consultant"
													+ conTemp);
									// consultant2 = consultantRepository.save(conTemp);
									log.info(
											"INFO ------------Owner Consultant already present ---- After Updating the Owner Consultant:  ----> Owner Consultant"
													+ consultantRepository.save(conTemp));
								} else {
									log.info("INFO ------------Other Type User of type "
											+ usertemp.get(0).getUser_type());
									Integer entityID = usertemp.get(0).getEntityid();

									Employee emp = userEmployeeRepository.getById(entityID);

									Consultant consultantTemp = new Consultant();

									consultantTemp.setEntityid(emp.getEntityid());
									consultantTemp.addConsultantOrganisation(organisation);
									consultantTemp.setName_of_Entity(emp.getName_of_Entity());
									// consultantTemp.setRole();
									// conTemp.setCategory();
									// conTemp.setAccrediation_sectors(consultant.getAccrediation_sectors());
									// conTemp.setAccrediation_validity(consultant.getAccrediation_validity());
									// conTemp.setValidity_flag(consultant.getValidity_flag());
									consultantTemp.setPan(emp.getPan_no());
									consultantTemp.setMobile(emp.getMobile());
									consultantTemp.setMobileNumber(emp.getMobile());
									consultantTemp.setIs_owner(true);

									consultantRepository.save(consultantTemp);

								}
							}
						}

						if (!(con.getIndividuals().isEmpty())) {
							con.getIndividuals().forEach(ind -> {
								Consultant consult = new Consultant(ind, "Consultant", request);
								if (!(consult.getEmail().isEmpty())) {

									/*
									 * log.info(
									 * "INFO ------------ getQCIConsultantOrganisation Email Id for Consultant is not empty ---- ConsultantOrgId---->"
									 * + ConsultantOrgId + "---- consultant.getEmail()----->" +
									 * consultant.getEmail() + "----:  EMAIL UNAVAILABLE");
									 */
									consult.addConsultantOrganisation(organisation);

									if (consult.getEmail().contains(";")) {
										consult.setEmail(consult.getEmail().split(";")[0]);
									} else if (consult.getEmail().contains(",")) {
										consult.setEmail(consult.getEmail().split(",")[0]);
									}
									List<User> usertempCon = userRepository.findRecordByEmail(consult.getEmail());
									if (usertempCon.isEmpty()) {

										/*
										 * Case of Consultant is new
										 */
										log.info(
												"INFO ------------ Case of New Consultant when Individuals were not present ----->");
										consult.setEmailId(consult.getEmail());
										consultantRepository.save(consult);

									} else {
										// List<User> usertemp =
										// userRepository.findRecordByEmail(consult.getEmail());
										if (usertempCon.size() >= 1) {

											User usertemp2 = usertempCon.get(0);
											String type = usertemp2.getUser_type();
											Consultant conTemp = consultantRepository.findById(usertemp2.getEntityid())
													.orElseThrow(
															() -> new UserNotFoundException("user not found with id"));
											log.info(
													"INFO ------------ Case of New Consultant when Individuals were not present ----->");

											if (type.equals("Consultant")) {

												/*
												 * Updating the entry for Consultants if emailId found
												 */

												// Updating Mapping of ConsultantOrganisation to Consultant
												log.info(
														"INFO ------------Consultant already present ---- Updating the Consultant with email"
																+ consult.getEmail());
												log.info(
														"INFO ------------Consultant already present ---- Updating the Consultant "
																+ conTemp.getAccrediation_sectors() + " : "
																+ conTemp.getRole());
												conTemp.addConsultantOrganisation(organisation);
												conTemp.setName_of_Entity(consult.getName_of_Entity());
												conTemp.setPan(consult.getPan());
												conTemp.setValidity_flag(consult.getValidity_flag());
												conTemp.setAccrediation_sectors(consult.getAccrediation_sectors());
												conTemp.setEngagement(consult.getEngagement());
												conTemp.setCategory(consult.getCategory());
												conTemp.setRole(consult.getRole());
												conTemp.setConsultantId(consult.getConsultantId());
												conTemp.setMobile(consult.getMobile());
												conTemp.setMobileNumber(consult.getMobile());
												conTemp.setAccrediation_validity(consult.getAccrediation_validity());
												log.info(
														"INFO ------------Consultant already present ---- Updating the Consultant: Consultant Individual"
																+ conTemp);

												// conTemp.setEmailId(consult.getEmail());
												// consultantRepository.save(conTemp);

												log.info(
														"INFO ------------Consultant already present ---- After Updating the Owner Consultant:  ----> Consultant"
																+ consultantRepository.save(conTemp));
											}
										}
									}
								}
							});
						}
						Map<String, Object> user = new HashMap<>();
						user.put("consultantId", consultant2.getEntityid());
						user.put("is_owner", consultant2.getIs_owner());
						return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.OK, "", user);

					}
				}

				return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.BAD_REQUEST, "",
						"Please enter the valid Organisation Id");
			} else {

				log.info("INFO ------------ Consultant Organisation not found ---->");
				return ResponseHandler.generateResponse("No Consultant Organisation found against the Consultant",
						HttpStatus.BAD_REQUEST, "", ConOrg);
			}

		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception", "Please enter the valid ORGID");
		}
	}

	@Transactional
	public ResponseEntity<Object> updateQCIConsultantOrganisation(String ConsultantOrgId) {
		try {

			/*
			 * Whether Exist in Database
			 */
			log.info("INFO ------------ INSIDE updateQCIConsultantOrganisation ---- with ConsultantOrgId---->"
					+ ConsultantOrgId);

			ConsultantOrganisation ConOrg = consultantOrganisationRepository.findByOrg_id(ConsultantOrgId);
			if (ConOrg != null) {

				/*
				 * Feign Client API Call For fetching the Organisation
				 */
				log.info("INFO ------------ Consultant Organisation with org Id " + ConOrg + "  found ---->");
				String OrgContent = consultantOrganisationAPI.getQCIConsutlant(ConsultantOrgId);

				ObjectMapper mapper = new ObjectMapper();
				List<ConsultOrganisation> organisations = mapper.readValue(OrgContent,
						new TypeReference<List<ConsultOrganisation>>() {
						});

				Iterator<ConsultOrganisation> itrConOrg = organisations.iterator();

				while (itrConOrg.hasNext()) {

					ConsultOrganisation con = itrConOrg.next();
					if (con.getOrgId().equals(ConsultantOrgId) && (con.getValididityFlag().equals("Active"))) {

						if (con.getEmailId().contains(";")) {
							con.setEmailId(con.getEmailId().split(";")[0]);
						} else if (con.getEmailId().contains(",")) {
							con.setEmailId(con.getEmailId().split(",")[0]);
						} else if (con.getEmailId().isEmpty()) {
							return ResponseHandler.generateResponse("Get Consultant Organisation",
									HttpStatus.BAD_REQUEST, "",
									"Email Id doesnot exist against the given Organisation");
						}
						if (con.getLandlineNo().contains(",")) {
							con.setLandlineNo(con.getLandlineNo().split(",")[0].replaceAll("\\s", ""));
						}

						/*
						 * Updating the Consultant Organisation
						 */

						ConOrg.setName(con.getOrganizationName());
						ConOrg.setAccreditation_No(con.getAccreditationNo());
						ConOrg.setAddress(con.getAddress());
						ConOrg.setCategory(con.getCategory());
						ConOrg.setValidity_flag(con.getValididityFlag());
						ConOrg.setAccreditation_sectors(con.getSectorsOfAccreditation());
						ConOrg.setPan(con.getPanNo());
						ConOrg.setEmail(con.getEmailId());
						ConOrg.setHead(con.getOrganizationHead());
						ConOrg.setLandline_no(con.getLandlineNo());
						ConOrg.setMobile_no(con.getMobileNo());
						ConOrg.setDesignation(con.getDesignation());
						try {
							if (con.getValidityOfAccreditation().equals("NA")) {
								ConOrg.setAccreditation_validity(new Date());
							} else if (con.getValidityOfAccreditation().contains("AM")) {
								ConOrg.setAccreditation_validity(new Date(new SimpleDateFormat("MM/d/yyyy")
										.parse(con.getValidityOfAccreditation().split("\\s+")[0]).getTime()));
							} else {
								ConOrg.setAccreditation_validity(new Date(new SimpleDateFormat("MM/d/yyyy")
										.parse(con.getValidityOfAccreditation()).getTime()));
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}

						log.info("INFO ------------Saving Consultant Organisation with OrgId" + con.getOrgId());
						Integer updated = consultantOrganisationRepository.UpdateConsultantorganisation(
								ConOrg.getName(), ConOrg.getAccreditation_No(), ConOrg.getAddress(),
								ConOrg.getCategory(), ConOrg.getValidity_flag(), ConOrg.getAccreditation_sectors(),
								ConOrg.getPan(), ConOrg.getEmail(), ConOrg.getHead(), ConOrg.getLandline_no(),
								ConOrg.getMobile_no(), ConOrg.getDesignation(), ConOrg.getAccreditation_validity(),
								ConOrg.getOrg_id());
						log.info("INFO ------------Saving Consultant Organisation with OrgId with Update Value"
								+ updated);
						// ConsultantOrganisation organisation =
						// consultantOrganisationRepository.save(ConOrg);

						Consultant consultant = new Consultant(con, "Consultant");

						consultant.addConsultantOrganisation(ConOrg);
						log.info("INFO ------------Mapping Consultant with " + consultant.getEmail()
								+ " with Organisation " + con.getOrgId());
						Consultant consultant2 = new Consultant();
						String genPass = generateRandomPassword(7);
						List<User> usertemp = userRepository.findRecordByEmail(consultant.getEmail());
						if (usertemp.isEmpty()) {

							/*
							 * Consultant not exist: New Consultant
							 */

							log.info(
									"INFO ------------ Case Owner Consultant doesnot exist: Adding Owner Consultant with email "
											+ consultant.getEmail());

							consultant.setPassword(passEncoder.encode(genPass));
							if (consultant.getEmail().contains(";")) {
								consultant.setEmail(consultant.getEmail().split(";")[0]);
							} else if (consultant.getEmail().contains(",")) {
								consultant.setEmail(consultant.getEmail().split(",")[0]);
							}
							consultant.setEmailId(consultant.getEmail());

							consultant2 = consultantRepository.save(consultant);

							log.info("INFO ------------ Case Owner Consultant Saved Successfully with email "
									+ consultant2.getEmail());

							/*
							 * Sending Mail to the Owner
							 */

							String token = UUID.randomUUID().toString();
							createVerificationToken(consultant2, token);
							String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email="
									+ consultant2.getEmail() + "&username=" + consultant2.getName_of_Entity();
							MailContent ob1 = new MailContent();
							ob1.setSubject(AppConstant.confirmMailSubjectConsultant);
							ob1.setVmname(AppConstant.confirmTemplateConsultant);

							if (environment.getProperty("environment.prod").equals("yes")) {
								ob1.setTo(consultant.getEmail());
							} else {
								ob1.setTo("sunil.yadav2@in.ey.com");
							}
							Map<String, String> body = new HashMap<>();

							body.put("entityType", "Consultant");
							body.put("recieverMail", consultant.getEmail());
							body.put("password", genPass);
							body.put("url", url);
							ob1.setBody(body);

							/*
							 * Kafka Producer Publishes the Object on the Topic
							 */

							// notifyClient.sendMail(ob1);
						} else {
							log.info(
									"INFO ------------ Case Owner Consultant exist: Updating Owner Consultant with email "
											+ consultant.getEmail());
							if (usertemp.size() >= 1) {
								User usertemp2 = usertemp.get(0);
								String type = usertemp2.getUser_type();
								if (type.equals("Consultant")) {
									Consultant conTemp = consultantRepository.findById(usertemp2.getEntityid())
											.orElseThrow(() -> new UserNotFoundException("user not found with id"));
									/*
									 * Updating the Existing Consultant
									 */

									log.info(
											"INFO ------------User type is Consultant --  Updating the Consultant ---->");
									conTemp.addConsultantOrganisation(ConOrg);
									conTemp.setName_of_Entity(consultant.getName_of_Entity());
									conTemp.setRole(consultant.getRole());
									conTemp.setCategory(consultant.getCategory());
									conTemp.setAccrediation_sectors(consultant.getAccrediation_sectors());
									conTemp.setAccrediation_validity(consultant.getAccrediation_validity());
									conTemp.setValidity_flag(consultant.getValidity_flag());
									conTemp.setPan(consultant.getPan());
									conTemp.setMobile(consultant.getMobile());
									conTemp.setMobileNumber(consultant.getMobile());
									conTemp.setIs_owner(true);

									log.info(
											"INFO ------------Owner Consultant already present ---- After Updating the Owner Consultant:  ----> Owner Consultant"
													+ consultantRepository.save(conTemp));
								} else {
									log.info("INFO ------------User type is not consultant ---->");

									/*
									 * Integer entityID = usertemp.get(0).getEntityid();
									 * 
									 * Employee emp = userEmployeeRepository.getById(entityID);
									 * 
									 * Consultant consultantTemp = new Consultant();
									 * 
									 * consultantTemp.setEntityid(emp.getEntityid());
									 * consultantTemp.addConsultantOrganisation(organisation);
									 * consultantTemp.setName_of_Entity(emp.getName_of_Entity());
									 * consultantTemp.setPan(emp.getPan_no());
									 * consultantTemp.setMobile(emp.getMobile());
									 * consultantTemp.setMobileNumber(emp.getMobile());
									 * consultantTemp.setIs_owner(true);
									 * 
									 * consultantRepository.save(consultantTemp);
									 */
								}
							}
						}

						if (!(con.getIndividuals().isEmpty())) {
							log.info("INFO ------------Consultant individual list not empty ---->");
							con.getIndividuals().forEach(ind -> {
								Consultant consult = new Consultant(ind, "Consultant");
								if (!(consult.getEmail().isEmpty())) {

									log.info("INFO ------------Mapping Consultant with " + consult.getEmail()
											+ " with Organisation " + con.getOrgId());
									consult.addConsultantOrganisation(ConOrg);

									if (consult.getEmail().contains(";")) {
										consult.setEmail(consult.getEmail().split(";")[0]);
									} else if (consult.getEmail().contains(",")) {
										consult.setEmail(consult.getEmail().split(",")[0]);
									}
									List<User> usertempCon = userRepository.findRecordByEmail(consult.getEmail());
									if (usertempCon.isEmpty()) {

										/*
										 * Case of Consultant is new
										 */

										log.info("INFO ------------ Case of New Consultant when Individuals ----->");
										consult.setEmailId(consult.getEmail());
										consultantRepository.save(consult);

									} else {
										if (usertempCon.size() >= 1) {

											User usertemp2 = usertempCon.get(0);
											String type = usertemp2.getUser_type();
											Consultant conTemp = consultantRepository.findById(usertemp2.getEntityid())
													.orElseThrow(
															() -> new UserNotFoundException("user not found with id"));
											log.info(
													"INFO ------------ Case of New Consultant when Individuals were not present ----->");

											if (type.equals("Consultant")) {

												/*
												 * Updating the entry for Consultants if emailId found
												 */

												log.info(
														"INFO ------------Consultant already present ---- Updating the Consultant with email"
																+ consult.getEmail());
												log.info(
														"INFO ------------Consultant already present ---- Updating the Consultant "
																+ conTemp.getAccrediation_sectors() + " : "
																+ conTemp.getRole());
												conTemp.addConsultantOrganisation(ConOrg);
												conTemp.setName_of_Entity(consult.getName_of_Entity());
												conTemp.setPan(consult.getPan());
												conTemp.setValidity_flag(consult.getValidity_flag());
												conTemp.setAccrediation_sectors(consult.getAccrediation_sectors());
												conTemp.setEngagement(consult.getEngagement());
												conTemp.setCategory(consult.getCategory());
												conTemp.setRole(consult.getRole());
												conTemp.setConsultantId(consult.getConsultantId());
												conTemp.setMobile(consult.getMobile());
												conTemp.setMobileNumber(consult.getMobile());
												log.info(
														"INFO ------------Consultant already present ---- After Updating the Owner Consultant:  ----> Consultant"
																+ consultantRepository.save(conTemp));
											}
										}
									}
								}
							});
						}
						Map<String, Object> user = new HashMap<>();
						user.put("consultantId", consultant2.getEntityid());
						user.put("is_owner", consultant2.getIs_owner());
						return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.OK, "", user);

					}
				}

				return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.BAD_REQUEST, "",
						"Please enter the valid Organisation Id");
			} else {

				log.info("INFO ------------ Consultant Organisation with org Id " + ConOrg + " not found ---->");
				return ResponseHandler.generateResponse("No Consultant Organisation found against the Consultant",
						HttpStatus.BAD_REQUEST, "", ConOrg);
			}

		} catch (Exception ex) {
			log.info(ex.getMessage());
			//ex.printStackTrace();
			return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception", ex.getMessage());
		}
	}

	public ResponseEntity<Object> updateQCIConsultantOrganisationwithDump(String ConsultantOrgId) {
		try {

			/*
			 * Whether Exist in Database
			 */

			log.info("INFO ------------ INSIDE updateQCIConsultantOrganisation ---- with ConsultantOrgId---->"
					+ ConsultantOrgId);

			ConsultantOrganisation ConOrg = consultantOrganisationRepository.findByOrg_id(ConsultantOrgId);
			if (ConOrg != null) {

				/*
				 * Feign Client API Call For fetching the Organisation
				 */
				log.info("INFO ------------ Consultant Organisation with org Id " + ConOrg + "  found ---->");
				String OrgContent = consultantOrganisationAPI.getQCIConsutlant(ConsultantOrgId);

				ObjectMapper mapper = new ObjectMapper();
				List<ConsultOrganisation> organisations = mapper.readValue(OrgContent,
						new TypeReference<List<ConsultOrganisation>>() {
						});

				Iterator<ConsultOrganisation> itrConOrg = organisations.iterator();

				while (itrConOrg.hasNext()) {

					ConsultOrganisation con = itrConOrg.next();
					if (con.getOrgId().equals(ConsultantOrgId) && (con.getValididityFlag().equals("Active"))) {

						if (con.getEmailId().contains(";")) {
							con.setEmailId(con.getEmailId().split(";")[0]);
						} else if (con.getEmailId().contains(",")) {
							con.setEmailId(con.getEmailId().split(",")[0]);
						} else if (con.getEmailId().isEmpty()) {
							return ResponseHandler.generateResponse("Get Consultant Organisation",
									HttpStatus.BAD_REQUEST, "",
									"Email Id doesnot exist against the given Organisation");
						}
						if (con.getLandlineNo().contains(",")) {
							con.setLandlineNo(con.getLandlineNo().split(",")[0].replaceAll("\\s", ""));
						}

						/*
						 * Updating the Consultant Organisation
						 */

						ConOrg.setName(con.getOrganizationName());
						ConOrg.setAccreditation_No(con.getAccreditationNo());
						ConOrg.setAddress(con.getAddress());
						ConOrg.setCategory(con.getCategory());
						ConOrg.setValidity_flag(con.getValididityFlag());
						ConOrg.setAccreditation_sectors(con.getSectorsOfAccreditation());
						ConOrg.setPan(con.getPanNo());
						ConOrg.setEmail(con.getEmailId());
						ConOrg.setHead(con.getOrganizationHead());
						ConOrg.setLandline_no(con.getLandlineNo());
						ConOrg.setMobile_no(con.getMobileNo());
						ConOrg.setDesignation(con.getDesignation());
						try {
							if (con.getValidityOfAccreditation().equals("NA")) {
								ConOrg.setAccreditation_validity(new Date());
							} else if (con.getValidityOfAccreditation().contains("AM")) {
								ConOrg.setAccreditation_validity(new Date(new SimpleDateFormat("MM/d/yyyy")
										.parse(con.getValidityOfAccreditation().split("\\s+")[0]).getTime()));
							} else {
								ConOrg.setAccreditation_validity(new Date(new SimpleDateFormat("MM/d/yyyy")
										.parse(con.getValidityOfAccreditation()).getTime()));
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}

						log.info("INFO ------------Saving Consultant Organisation with OrgId" + con.getOrgId());
						ConsultantOrganisation organisation = consultantOrganisationRepository.save(ConOrg);

						Consultant consultant = new Consultant(con, "Consultant");

						consultant.addConsultantOrganisation(organisation);
						log.info("INFO ------------Mapping Consultant with " + consultant.getEmail()
								+ " with Organisation " + con.getOrgId());
						Consultant consultant2 = new Consultant();
						String genPass = generateRandomPassword(7);
						List<User> usertemp = userRepository.findRecordByEmail(consultant.getEmail());
						if (usertemp.isEmpty()) {

							/*
							 * Consultant not exist: New Consultant
							 */

							log.info(
									"INFO ------------ Case Owner Consultant doesnot exist: Adding Owner Consultant with email "
											+ consultant.getEmail());

							consultant.setPassword(passEncoder.encode(genPass));
							if (consultant.getEmail().contains(";")) {
								consultant.setEmail(consultant.getEmail().split(";")[0]);
							} else if (consultant.getEmail().contains(",")) {
								consultant.setEmail(consultant.getEmail().split(",")[0]);
							}
							consultant.setEmailId(consultant.getEmail());

							consultant2 = consultantRepository.save(consultant);

							log.info("INFO ------------ Case Owner Consultant Saved Successfully with email "
									+ consultant2.getEmail());

							/*
							 * Sending Mail to the Owner
							 */

							String token = UUID.randomUUID().toString();
							createVerificationToken(consultant2, token);
							String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email="
									+ consultant2.getEmail() + "&username=" + consultant2.getName_of_Entity();
							MailContent ob1 = new MailContent();
							ob1.setSubject(AppConstant.confirmMailSubjectConsultant);
							ob1.setVmname(AppConstant.confirmTemplateConsultant);

							if (environment.getProperty("environment.prod").equals("yes")) {
								ob1.setTo(consultant.getEmail());
							} else {
								ob1.setTo("sunil.yadav2@in.ey.com");
							}
							Map<String, String> body = new HashMap<>();

							body.put("entityType", "Consultant");
							body.put("recieverMail", consultant.getEmail());
							body.put("password", genPass);
							body.put("url", url);
							ob1.setBody(body);

							/*
							 * Kafka Producer Publishes the Object on the Topic
							 */

							// notifyClient.sendMail(ob1);
						} else {
							log.info(
									"INFO ------------ Case Owner Consultant exist: Updating Owner Consultant with email "
											+ consultant.getEmail());
							if (usertemp.size() >= 1) {
								User usertemp2 = usertemp.get(0);
								String type = usertemp2.getUser_type();
								if (type.equals("Consultant")) {
									Consultant conTemp = consultantRepository.findById(usertemp2.getEntityid())
											.orElseThrow(() -> new UserNotFoundException("user not found with id"));
									/*
									 * Updating the Existing Consultant
									 */

									log.info(
											"INFO ------------User type is Consultant --  Updating the Consultant ---->");
									conTemp.addConsultantOrganisation(organisation);
									conTemp.setName_of_Entity(consultant.getName_of_Entity());
									conTemp.setRole(consultant.getRole());
									conTemp.setCategory(consultant.getCategory());
									conTemp.setAccrediation_sectors(consultant.getAccrediation_sectors());
									conTemp.setAccrediation_validity(consultant.getAccrediation_validity());
									conTemp.setValidity_flag(consultant.getValidity_flag());
									conTemp.setPan(consultant.getPan());
									conTemp.setMobile(consultant.getMobile());
									conTemp.setMobileNumber(consultant.getMobile());
									conTemp.setIs_owner(true);

									log.info(
											"INFO ------------Owner Consultant already present ---- After Updating the Owner Consultant:  ----> Owner Consultant"
													+ consultantRepository.save(conTemp));
								} else {
									log.info("INFO ------------User type is not consultant ---->");

									/*
									 * Integer entityID = usertemp.get(0).getEntityid();
									 * 
									 * Employee emp = userEmployeeRepository.getById(entityID);
									 * 
									 * Consultant consultantTemp = new Consultant();
									 * 
									 * consultantTemp.setEntityid(emp.getEntityid());
									 * consultantTemp.addConsultantOrganisation(organisation);
									 * consultantTemp.setName_of_Entity(emp.getName_of_Entity());
									 * consultantTemp.setPan(emp.getPan_no());
									 * consultantTemp.setMobile(emp.getMobile());
									 * consultantTemp.setMobileNumber(emp.getMobile());
									 * consultantTemp.setIs_owner(true);
									 * 
									 * consultantRepository.save(consultantTemp);
									 */
								}
							}
						}

						if (!(con.getIndividuals().isEmpty())) {
							log.info("INFO ------------Consultant individual list not empty ---->");
							con.getIndividuals().forEach(ind -> {
								Consultant consult = new Consultant(ind, "Consultant");
								if (!(consult.getEmail().isEmpty())) {

									log.info("INFO ------------Mapping Consultant with " + consult.getEmail()
											+ " with Organisation " + con.getOrgId());
									consult.addConsultantOrganisation(organisation);

									if (consult.getEmail().contains(";")) {
										consult.setEmail(consult.getEmail().split(";")[0]);
									} else if (consult.getEmail().contains(",")) {
										consult.setEmail(consult.getEmail().split(",")[0]);
									}
									List<User> usertempCon = userRepository.findRecordByEmail(consult.getEmail());
									if (usertempCon.isEmpty()) {

										/*
										 * Case of Consultant is new
										 */

										log.info("INFO ------------ Case of New Consultant when Individuals ----->");
										consult.setEmailId(consult.getEmail());
										consultantRepository.save(consult);

									} else {
										if (usertempCon.size() >= 1) {

											User usertemp2 = usertempCon.get(0);
											String type = usertemp2.getUser_type();
											Consultant conTemp = consultantRepository.findById(usertemp2.getEntityid())
													.orElseThrow(
															() -> new UserNotFoundException("user not found with id"));
											log.info(
													"INFO ------------ Case of New Consultant when Individuals were not present ----->");

											if (type.equals("Consultant")) {

												/*
												 * Updating the entry for Consultants if emailId found
												 */

												log.info(
														"INFO ------------Consultant already present ---- Updating the Consultant with email"
																+ consult.getEmail());
												log.info(
														"INFO ------------Consultant already present ---- Updating the Consultant "
																+ conTemp.getAccrediation_sectors() + " : "
																+ conTemp.getRole());
												conTemp.addConsultantOrganisation(organisation);
												conTemp.setName_of_Entity(consult.getName_of_Entity());
												conTemp.setPan(consult.getPan());
												conTemp.setValidity_flag(consult.getValidity_flag());
												conTemp.setAccrediation_sectors(consult.getAccrediation_sectors());
												conTemp.setEngagement(consult.getEngagement());
												conTemp.setCategory(consult.getCategory());
												conTemp.setRole(consult.getRole());
												conTemp.setConsultantId(consult.getConsultantId());
												conTemp.setMobile(consult.getMobile());
												conTemp.setMobileNumber(consult.getMobile());
												log.info(
														"INFO ------------Consultant already present ---- After Updating the Owner Consultant:  ----> Consultant"
																+ consultantRepository.save(conTemp));
											}
										}
									}
								}
							});
						}
						Map<String, Object> user = new HashMap<>();
						user.put("consultantId", consultant2.getEntityid());
						user.put("is_owner", consultant2.getIs_owner());
						return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.OK, "", user);

					}
				}

				return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.BAD_REQUEST, "",
						"Please enter the valid Organisation Id");
			} else {

				log.info("INFO ------------ Consultant Organisation with org Id " + ConOrg + " not found ---->");
				/*
				 * Feign Client Call
				 */
				String OrgContent = consultantOrganisationAPI.getQCIConsutlant(ConsultantOrgId);
				log.info("INFO ------------ Consultant Organisation fetched from QCI for orgId" + ConsultantOrgId);

				ObjectMapper mapper = new ObjectMapper();

				List<ConsultOrganisation> organisations = mapper.readValue(OrgContent,
						new TypeReference<List<ConsultOrganisation>>() {
						});
				log.info("-----------------------Consultant Organsiation Object-----------");
				log.info(organisations.get(0).toString());
				// Integer maxCount = tempClearances.stream().map(e ->
				// e.getProposal_sequence()).max(Comparator.comparing(Integer::valueOf)).get();

				log.info("INFO ------------ Consultant Organisation mapped to Model ---->");

				// List<ConsultantOrganisationQCIDump>
				// savedOrg=consultantOrganisationQCIDumpRepository.saveAll(organisations);

				log.info("INFO ------------ Consultant Organisation saved ---->");
				return ResponseHandler.generateResponse("No Consultant Organisation found against the Consultant",
						HttpStatus.BAD_REQUEST, "", "");
			}

		} catch (Exception ex) {
			log.info("-----------> Exception is ----->" + ex.getMessage());
			return ResponseHandler.generateResponse("Get Consultant Organisation", HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception", ex.getMessage());
		}
	}

	public ResponseEntity<Object> deleteConsultantorganisation(Integer ConsultantOrgId) {
		try {
			ConsultantOrganisation consultantOrg = consultantOrganisationRepository.getById(ConsultantOrgId);
			consultantOrg.setIs_active(false);
			consultantOrg.setIs_deleted(true);
			ConsultantOrganisation temp = consultantOrganisationRepository.save(consultantOrg);
			return ResponseHandler.generateResponse("Delete Consultant Organisation", HttpStatus.OK, "", temp);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Delete Consultant Organisation", HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception", ex.getMessage());
		}
	}

	public String getLoggedConsultantInOrganisation(Integer id) {
		List<String> consultantOrganisationsId = null;
		User user = userRepository.getUserDetailsById(id);

		if (user.getUser_type().equals("Consultant")) {
			Consultant consultant = consultantRepository.UserIdIsPresent(id);
			consultantOrganisationsId = consultantOrganisationRepository.findOrganisationId(consultant.getEntityid());
			consultant.setOrgIds(consultantOrganisationsId);

		}
		return consultantOrganisationsId.get(0);
	}

	public ResponseEntity<Object> getAllOrganisation() {
		try {
			return ResponseHandler.generateResponse("Get All Consultant Organisation", HttpStatus.OK, "",
					consultantOrganisationRepository.findAllOrganisations());
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Get All Consultant Organisation", HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception", ex.getStackTrace());
		}
	}

	public ResponseEntity<Object> updateConsultantOrganisation(ConsultantOrganisation consultantOrganisation) {
		try {
			ConsultantOrganisation conTemp = consultantOrganisationRepository.save(consultantOrganisation);
			return ResponseHandler.generateResponse("Update Consultant", HttpStatus.OK, "", conTemp);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Update Consultant", HttpStatus.INTERNAL_SERVER_ERROR, "",
					ex.getMessage());
		}
	}

	@Scheduled(cron = "${qci.updateConsultant.job.corn}")
	public void getupdateConsultantsOrganisation() {
		try {

			 LocalDate date_of_today = LocalDate.now();
			//LocalDate date_of_today = LocalDate.of(2023, 02, 9);

			log.info(
					"--------getupdateConsultantsOrganisation Scheduler Function---------> with Date " + date_of_today);
			HttpServletRequest request = null;

			String UpdatedOrgContent = consultantOrganisationAPI.getQCIUpdatedConsutlant(date_of_today.toString());
			log.info("--------Consultant Organisation to be Updated are-------->" + UpdatedOrgContent);
			List<ConsultantOrg> organisations = new ArrayList<>();
			if (!(UpdatedOrgContent.equals("Not Data Found"))) {

				ObjectMapper mapper = new ObjectMapper();
				organisations = mapper.readValue(UpdatedOrgContent, new TypeReference<List<ConsultantOrg>>() {
				});

				for (ConsultantOrg org : organisations) {
					log.info("--------Iterating Consultant Organisation--------->");
					ResponseEntity<Object> res = updateQCIConsultantOrganisation(org.getOrgId());
				}
			} else {
				log.info("--------No Organisation to be updated--------->");
			}

			log.info(
					"---------------getupdateConsultantsOrganisation  Scheduler OrgContent ----------" + organisations);
		} catch (Exception ex) {
			log.info("In Exception " + ex.getMessage());
		}
	}

	public String generateRandomPassword(int len) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}
		return sb.toString();
	}

	public User getUser(String verificationToken) {
		User user = verificationRepository.findByToken(verificationToken).getUser();
		return user;
	}

	public VerificationToken getVerificationToken(String VerificationToken) {
		return verificationRepository.findByToken(VerificationToken);
	}

	public void saveRegisteredUser(User user) {
		userRepository.save(user);
	}

	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(user, token);
		verificationRepository.save(myToken);
	}

}
