package com.backend.service;


import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant;
import com.backend.dto.ChangePassword;
import com.backend.dto.SelectedRoleDto;
import com.backend.dto.SelectedSectorDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.EmailException;
import com.backend.exceptions.UserAlreadyExistException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.*;
import com.backend.parivesh.nic.UserRegistrationService;
import com.backend.repository.postgres.*;
import com.backend.response.ResponseHandler;
import com.backend.util.StringUtil;
import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    private UserIndividualRepository userIndividualRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserGovernmentRepository userGovernmentRepository;

    @Autowired
    private UserOtherRepository userOtherRepository;

    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectDetailRepository projectDetailRepository;

    @Autowired
    private UserEmployeeRepository userEmployeeRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    private GenCodeMasterRepository genCodeMasterRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private ConsultantOrganisationRepository consultantOrganisationRepository;

    @Autowired
    private NotifyClient notifyClient;

    @Autowired
    private PasswordEncoder passEncoder;

    @Autowired
    private Environment environment;

    @Autowired
    private UserRegistrationService registrationService;

    @Autowired
    private DistrictService districtService;

    public ResponseEntity<Object> addUserRegistration(ProjectProponentIndividual individual, String type, HttpServletRequest request) {
        if (userIndividualRepository.findRecordByPan(individual.getPan_no()).isPresent() || userOtherRepository.findRecordByPan(individual.getPan_no()).isPresent()) {
            log.info("User(PAN number duplicate entry) already exist exception raised");
            throw new UserAlreadyExistException("User Already exist in the registration process with Pan number");
        } else if (userRepository.findRecordByEmail(individual.getEmail()).size() >= 1) {
            log.info("User(email duplicate entry) already exist exception raised");
            throw new UserAlreadyExistException("User Already exist in the registration process with email ID.");
        } else {
            Organisation organisation = new Organisation();
            ProjectProponentIndividual individual2 = userIndividualRepository.save(individual);

            /*
             * Getting the State Code by District Code and match with state code in state_ut
             * column
             */

            District d = districtService.getStatebyDistrictCode(Integer.valueOf(individual.getDistrict()));
            if (!(Integer.valueOf(individual.getState_ut()) == d.getState_code())) {
                return ResponseHandler.generateResponse("User Registration Individual", HttpStatus.PRECONDITION_FAILED, "", "State not Same");
            }

            if (individual.getIs_organisation() == true) {
                Organisation organisationtemp = new Organisation();
                organisationtemp.setName(individual.getName_of_Entity());
                organisationtemp.setState_ut(individual.getOrg_state_ut());
                organisationtemp.setEntity_type(AppConstant.typeIndividual);
                organisationtemp.setDistrict(individual.getOrg_district());
                organisationtemp.setPincode(individual.getOrg_pincode());
                organisationtemp.setAddress(individual.getOrg_address());
                organisationtemp.setEmail(individual.getEmail().trim());
                organisationtemp.setMobile_no(individual.getMobile());

                organisation = organisationRepository.save(organisationtemp);
            } else {
                organisation = organisationRepository.save(new Organisation(individual2, AppConstant.typeIndividual));
            }
            Employee employee = new Employee(individual2, AppConstant.typeIndividual, request);
            String genPass = StringUtil.generateRandomPassword(7);
            employee.setPassword(passEncoder.encode(genPass));
            employee.setEmailId(employee.getEmail().trim());
            employee.setOrganisation(organisation);
            Employee employee2 = userEmployeeRepository.save(employee);
            registrationService.UserRegistration(employee2, organisation, genPass);
            String token = UUID.randomUUID().toString();
            createVerificationToken(employee2, token);
            String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + individual2.getEmail().trim() + "&username=" + individual2.getName_of_Entity();
            MailContent ob1 = new MailContent();
            ob1.setSubject(AppConstant.confirmMailSubject);
            ob1.setVmname(AppConstant.confirmTemplate);
            ob1.setTo(individual2.getEmail());
            Map<String, String> body = new HashMap<>();
            body.put("entityType", AppConstant.typeIndividual);
            body.put("recieverMail", individual2.getEmail().trim());
            body.put("password", genPass);
            body.put("url", url);
            ob1.setBody(body);

            // Kafka Producer Publishes the Object on the Topic
            log.info("Sending Registration Mail to client.");
            notifyClient.sendMail(ob1);
            Map<String, Integer> userId = new HashMap<>();
            userId.put("UserId", employee2.getEntityid());
            log.info("User registaration individual successful");
            return ResponseHandler.generateResponse("User Registration Individual", HttpStatus.OK, "", userId);
        }
    }

    public ResponseEntity<Object> addOtherRegistration(ProjectProponentOther entity, String type, HttpServletRequest request) throws IOException {
        if (userIndividualRepository.findRecordByPan(entity.getPan_no()).isPresent() || userOtherRepository.findRecordByPan(entity.getPan_no()).isPresent()) {
            log.info("User(PAN number duplicate entry) already exist exception raised");
            throw new UserAlreadyExistException("User Already exist in the registration process with Pan number");
        } else if (userRepository.findRecordByEmail(entity.getEmail()).size() >= 1) {
            log.info("User(email duplicate entry) already exist exception raised");
            throw new UserAlreadyExistException("User Already exist in the registration process with email ID.");
        } else {

            ProjectProponentOther entity2 = userOtherRepository.save(entity);
            Organisation organisation = new Organisation();

            /*
             * Getting the State Code by District Code and match with state code in state_ut
             * column
             */

            District d = districtService.getStatebyDistrictCode(Integer.valueOf(entity.getDistrict()));
            if (!(Integer.valueOf(entity.getState_ut()) == d.getState_code())) {
                return ResponseHandler.generateResponse("User Registration Others", HttpStatus.PRECONDITION_FAILED, "", "State not Same");
            }

            if (entity.getIs_organisation() == true) {
                Organisation organisationtemp = new Organisation();
                organisationtemp.setName(entity.getOrg_name());
                organisationtemp.setState_ut(entity.getOrg_state_ut());
                organisationtemp.setEntity_type(AppConstant.typeOthers);
                organisationtemp.setDistrict(entity.getOrg_district());
                organisationtemp.setPincode(entity.getOrg_pincode());
                organisationtemp.setAddress(entity.getOrg_address());

                organisation = organisationRepository.save(organisationtemp);
            } else {
                organisation = organisationRepository.save(new Organisation(entity2, AppConstant.typeOthers));
            }
            if (entity.getPan_no() == null) {
                // ProjectProponentEntity entity2 = userEntityRepository.save(entity);
                // Organisation organisation = organisationRepository.save(new
                // Organisation(entity2,type));
                Employee employee = new Employee(entity2, AppConstant.typeOthers, request);
                String genPass = StringUtil.generateRandomPassword(7);
                employee.setPassword(passEncoder.encode(genPass));
                employee.setEmailId(employee.getEmail().trim());
                employee.setOrganisation(organisation);
                employee.setAuth_letter(entity2.getVoter_id());
                Employee employee2 = userEmployeeRepository.save(employee);
                registrationService.UserRegistration(employee2, organisation, genPass);
                String token = UUID.randomUUID().toString();
                createVerificationToken(employee2, token);

                /*
                 * Sending the Scrutiny Mail
                 */

                MailContent ob2 = new MailContent();
                ob2.setSubject(AppConstant.inProcessSubject);
                ob2.setVmname(AppConstant.inProcessTemplate);
                ob2.setTo(entity2.getEmail());
                Map<String, String> body = new HashMap<>();
                body.put("entityType", type);
                body.put("recieverMail", entity2.getEmail().trim());
                ob2.setBody(body);
                log.info("Sending Registration (Others) Mail to client.");
                notifyClient.sendMail(ob2);

                // String url =
                // "http://localhost:4200/activateEmail?token="+token+"&email="+individual2.getEmail();

                String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + entity2.getEmail().trim() + "&username=" + entity2.getName_of_Entity();
                // Sending the Suucessful Mail
                MailContent ob1 = new MailContent();
                ob1.setSubject(AppConstant.confirmMailSubject);
                ob1.setVmname(AppConstant.confirmTemplate);
                ob1.setTo(entity2.getEmail());
                Map<String, String> body2 = new HashMap<>();
                body2.put("entityType", AppConstant.typeOthers);
                body2.put("recieverMail", entity2.getEmail().trim());
                body2.put("password", genPass);
                body2.put("url", url);
                ob1.setBody(body2);

                /*
                 * Kafka Producer Publishes the Object on the Topic (Activation mail in case of
                 * others when pan not provided)
                 */

                // notifyClient.sendMail(ob1);

                Map<String, Integer> userId = new HashMap<>();
                userId.put("UserId", employee2.getEntityid());
                return ResponseHandler.generateResponse("User Registration ", HttpStatus.OK, "", userId);
            } else {
                Employee employee = new Employee(entity2, AppConstant.typeOthers, request);
                String genPass = StringUtil.generateRandomPassword(7);
                employee.setPassword(passEncoder.encode(genPass));
                employee.setEmailId(employee.getEmail().trim());
                employee.setOrganisation(organisation);
                employee.setAuth_letter(entity2.getVoter_id());
                Employee employee2 = userEmployeeRepository.save(employee);
                registrationService.UserRegistration(employee2, organisation, genPass);
                String token = UUID.randomUUID().toString();
                createVerificationToken(employee2, token);
                String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + entity2.getEmail().trim() + "&username=" + entity2.getName_of_Entity();
                MailContent ob1 = new MailContent();
                ob1.setSubject(AppConstant.confirmMailSubject);
                ob1.setVmname(AppConstant.confirmTemplate);
                ob1.setTo(entity2.getEmail());
                Map<String, String> body = new HashMap<>();
                body.put("entityType", AppConstant.typeOthers);
                body.put("recieverMail", entity2.getEmail().trim());
                body.put("password", genPass);
                body.put("url", url);
                ob1.setBody(body);

                /*
                 * Kafka Producer Publishes the Object on the Topic
                 */

                log.info("Sending Registration (Others) Mail to client.");
                notifyClient.sendMail(ob1);
//				System.out.println("password generated: "+genPass);
                Map<String, Integer> userId = new HashMap<>();
                userId.put("UserId", employee2.getEntityid());
                log.info("Uesr registeration others completed");
                return ResponseHandler.generateResponse("User Registration Others", HttpStatus.OK, "", userId);
                // return "success";

            }
        }
    }

    public ResponseEntity<Object> addEntityRegistration(ProjectProponentEntity entity, String type, HttpServletRequest request) throws IOException {
        if (userEntityRepository.findRecordByPan(entity.getPan_no()).isPresent()) {
            throw new UserAlreadyExistException("User Already exist in the registration process with Pan number");
        } else if (userRepository.findRecordByEmail(entity.getEmail()).size() >= 1) {
            throw new UserAlreadyExistException("User Already exist in the registration process with email ID.");
        } else if (userGovernmentRepository.findRecordByCin(entity.getCin_no() != null ? entity.getCin_no() : "1").isPresent()) {
            throw new UserAlreadyExistException("User Already exist in the registration process with CIN number.");
        } else {

            /*
             * Getting the State Code by District Code and match with state code in state_ut
             * column
             */

            District d = districtService.getStatebyDistrictCode(Integer.valueOf(entity.getDistrict()));
            if (!(Integer.valueOf(entity.getState_ut()) == d.getState_code())) {
                return ResponseHandler.generateResponse("User Registration", HttpStatus.PRECONDITION_FAILED, "", "State not Same");
            }

            ProjectProponentEntity entity2 = userEntityRepository.save(entity);
            Organisation organisation = organisationRepository.save(new Organisation(entity2, type));
            Employee employee = new Employee(entity2, type, request);
            String genPass = StringUtil.generateRandomPassword(7);
            employee.setPassword(passEncoder.encode(genPass));
            employee.setEmailId(employee.getEmail().trim());
            employee.setOrganisation(organisation);
            employee.setAuth_letter(entity2.getAuthority_letter());
            Employee employee2 = userEmployeeRepository.save(employee);
            registrationService.UserRegistration(employee2, organisation, genPass);
            String token = UUID.randomUUID().toString();
            createVerificationToken(employee2, token);

            // String url =
            // "http://localhost:4200/activateEmail?token="+token+"&email="+individual2.getEmail();
            String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + entity2.getEmail().trim() + "&username=" + entity2.getName_of_Entity();
            MailContent ob1 = new MailContent();
            ob1.setSubject(AppConstant.confirmMailSubject);
            ob1.setVmname(AppConstant.confirmTemplate);
            ob1.setTo(entity2.getEmail());
            Map<String, String> body = new HashMap<>();
            body.put("entityType", type);
            body.put("recieverMail", entity2.getEmail().trim());
            body.put("password", genPass);
            body.put("url", url);
            ob1.setBody(body);

            // Kafka Producer Publishes the Object on the Topic
            notifyClient.sendMail(ob1);
//				System.out.println("password generated: "+genPass);
            Map<String, Integer> userId = new HashMap<>();
            userId.put("UserId", employee2.getEntityid());
            return ResponseHandler.generateResponse("User Registration Others", HttpStatus.OK, "", userId);
        }
    }

    public ResponseEntity<Object> addGovernmentRegistration(ProjectProponentGovernment entity, String type, HttpServletRequest request) throws IOException {
        if (userGovernmentRepository.findRecordByPan(entity.getPan_no()).isPresent()) {

            // return ResponseHandler.generateResponse("Government PP Not
            // added",HttpStatus.PRECONDITION_FAILED,"Government Entity Already exist in the
            // registration process with Pan","");
            throw new UserAlreadyExistException("Government Entity Already exist in the registration process with Pan");
        } else if (userRepository.findRecordByEmail(entity.getEmail()).size() >= 1) {
            // return ResponseHandler.generateResponse("Government PP Not
            // added",HttpStatus.PRECONDITION_FAILED,"Government Entity Already exist in the
            // registration process with Email Id","");

            throw new UserAlreadyExistException("Government Entity Already exist in the registration process with Email Id");
        } else if (userGovernmentRepository.findRecordByCin(entity.getCin_no()).isPresent()) {
            // return ResponseHandler.generateResponse("Government PP Not
            // added",HttpStatus.PRECONDITION_FAILED,"Government Entity Already exist in the
            // registration process with CIN no","");
            throw new UserAlreadyExistException("Government Entity Already exist in the registration process with Cin no");
        } else if (entityExist(entity)) {
            // return ResponseHandler.generateResponse("Government PP Not
            // added",HttpStatus.PRECONDITION_FAILED,"Government Entity Already exist in the
            // registration process with Entity name","");
            throw new UserAlreadyExistException("Government Entity Already exist in the registration process with Entity name");
        } else {

            /*
             * Getting the State Code by District Code and match with state code in state_ut
             * column
             */

            District d = districtService.getStatebyDistrictCode(Integer.valueOf(entity.getDistrict()));
            if (!(Integer.valueOf(entity.getState_ut()) == d.getState_code())) {
                return ResponseHandler.generateResponse("User Registration Government", HttpStatus.PRECONDITION_FAILED, "", "State not Same");
            }

            if (Arrays.stream(new String[]{"gov.in", "nic.in"}).anyMatch(entity.getEmail()::contains)) {
                ProjectProponentGovernment entity2 = userGovernmentRepository.save(entity);
                Organisation organisation = organisationRepository.save(new Organisation(entity2, type));
                Employee employee = new Employee(entity2, type, request);
                String genPass = StringUtil.generateRandomPassword(7);
                employee.setEmailId(employee.getEmail().trim());
                employee.setPassword(passEncoder.encode(genPass));
                employee.setOrganisation(organisation);
                employee.setAuth_letter(entity2.getAuthority_letter());
                Employee employee2 = userEmployeeRepository.save(employee);
                registrationService.UserRegistration(employee2, organisation, genPass);
                String token = UUID.randomUUID().toString();
                createVerificationToken(employee2, token);

                // String url =
                // "http://localhost:4200/activateEmail?token="+token+"&email="+individual2.getEmail();
                if (entity2.getPan_no() == null && entity2.getCin_no() == null) {
                    MailContent ob1 = new MailContent();
                    ob1.setSubject(AppConstant.inProcessSubject);
                    ob1.setVmname(AppConstant.inProcessTemplate);
                    ob1.setTo(entity2.getEmail());
                    Map<String, String> body = new HashMap<>();
                    body.put("entityType", type);
                    body.put("recieverMail", entity2.getEmail().trim());
                    ob1.setBody(body);
                    // Kafka Producer Publishes the Object on the Topic
                    notifyClient.sendMail(ob1);
                    String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + entity2.getEmail().trim() + "&username=" + entity2.getName_of_Entity();
                    MailContent ob2 = new MailContent();
                    ob2.setSubject(AppConstant.confirmMailSubject);
                    ob2.setVmname(AppConstant.confirmTemplate);
                    ob2.setTo(entity2.getEmail());
                    Map<String, String> body2 = new HashMap<>();
                    body2.put("entityType", type);
                    body2.put("recieverMail", entity2.getEmail().trim());
                    body2.put("password", genPass);
                    body2.put("url", url);
                    ob2.setBody(body2);
//					notifyClient.sendMail(ob2);
                } else {
                    String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + entity2.getEmail().trim() + "&username=" + entity2.getName_of_Entity();
                    MailContent ob1 = new MailContent();
                    ob1.setSubject(AppConstant.confirmMailSubject);
                    ob1.setVmname(AppConstant.confirmTemplate);
                    ob1.setTo(entity2.getEmail());
                    Map<String, String> body = new HashMap<>();
                    body.put("entityType", type);
                    body.put("recieverMail", entity2.getEmail().trim());
                    body.put("password", genPass);
                    body.put("url", url);
                    ob1.setBody(body);

                    // Kafka Producer Publishes the Object on the Topic
                    notifyClient.sendMail(ob1);
                }
//					System.out.println("password generated: "+genPass);
                Map<String, Integer> userId = new HashMap<>();
                userId.put("UserId", employee2.getEntityid());
                return ResponseHandler.generateResponse("Government PP added Successfully", HttpStatus.OK, "", userId);
                // return "success";
            } else {
                // return ResponseHandler.generateResponse("Government PP not added
                // Successfully",HttpStatus.PRECONDITION_FAILED,"Mail ID domain must be only
                // @nic.in/@gov.in","");
                throw new EmailException("Mail ID domain must be only @nic.in/@gov.in");
            }
        }
    }

    /*
     * Employee Service
     */
    public ResponseEntity<Object> addEmployee(Employee employee, UserPrincipal currentUser) {

        /*
         * Getting the State Code by District Code and match with state code in state_ut
         * column
         */
    	boolean verifyEmployee = false;
        MailContent ob1 = new MailContent();
        Map<String, String> body = new HashMap<>();       
        MailContent ob2 = new MailContent();
        Map<String, String> body2 = new HashMap<>();
        Employee empTempEmployee = null;
    	
        District d = districtService.getStatebyDistrictCode(Integer.valueOf(employee.getDistrict()));
        if (!(Integer.valueOf(employee.getState_ut()) == d.getState_code())) {
            return ResponseHandler.generateResponse("User Registration Others", HttpStatus.PRECONDITION_FAILED, "", "State not Same");
        }

        if(employee.getOrganisation_id() != null) {
        	
        	verifyEmployee = true;
        }
        
        Integer orgId = userEmployeeRepository.findOrganisationId(currentUser.getId());
        Organisation organisation = organisationRepository.getById(orgId);
        employee.setOrganisation(organisation);
        employee.setEmailId(employee.getEmail().trim());
        employee.setUser_type("Employee");
        employee.setUserType("Employee");
        employee.setStd_code(employee.getStd_code());

        // Mail after Activating the Employee
        if(verifyEmployee) {
        	
        	employee.setVerify_status("PENDING");
            
        	//Mail for Employee
        	ob1.setSubject(AppConstant.pendingMailSubject);
            ob1.setVmname(AppConstant.pendingTemplate);
            ob1.setTo(employee.getEmail());
            body.put("entityType", "Employee");
            body.put("recieverMail", employee.getEmail().trim());
            ob1.setBody(body);
            
            //Mail for Organization
        	ob2.setSubject(AppConstant.verifyMailSubject);
            ob2.setVmname(AppConstant.verifyTemplate);
            ob2.setTo(organisation.getEmail());
            body2.put("entityType", "organisation");
            body2.put("recieverMail", organisation.getEmail().trim());
            body2.put("Employee_mobile", employee.getMobile());
            body2.put("Employee_email", employee.getEmail());
            body2.put("Employee_name", employee.getName_of_Entity());
            ob2.setBody(body2);
            notifyClient.sendMail(ob2);
        }
        else {
        	
            String genPass = StringUtil.generateRandomPassword(7);
            employee.setPassword(passEncoder.encode(genPass));
            employee.setVerify_status("VERIFIED");

        	String token = UUID.randomUUID().toString();
            createVerificationToken(employee, token);
            String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + employee.getEmail().trim() + "&username=" + employee.getName_of_Entity();
            ob1.setSubject(AppConstant.confirmMailSubject);
            ob1.setVmname(AppConstant.confirmTemplate);
            ob1.setTo(employee.getEmail());
            body.put("entityType", "Employee");
            body.put("recieverMail", employee.getEmail().trim());
            body.put("password", genPass);
            body.put("url", url);
            ob1.setBody(body);

        }
        

//        String token = UUID.randomUUID().toString();
//        createVerificationToken(employee, token);
//        String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + employee.getEmail().trim() + "&username=" + employee.getName_of_Entity();
//        MailContent ob1 = new MailContent();
//        ob1.setSubject(AppConstant.confirmMailSubject);
//        ob1.setVmname(AppConstant.confirmTemplate);
//        ob1.setTo(employee.getEmail());
//        Map<String, String> body = new HashMap<>();
//        body.put("entityType", "Employee");
//        body.put("recieverMail", employee.getEmail().trim());
//        body.put("password", genPass);
//        body.put("url", url);
//        ob1.setBody(body);

        empTempEmployee = userEmployeeRepository.save(employee);
        notifyClient.sendMail(ob1);

        return ResponseHandler.generateResponse("Add Employee", HttpStatus.OK, "", empTempEmployee);

    }
    
    public ResponseEntity<Object> updateStatus(Employee employee) {

        /*
         * Getting the State Code by District Code and match with state code in state_ut
         * column
         */
    	boolean verified = false;
        MailContent ob1 = new MailContent();
        Map<String, String> body = new HashMap<>();
        
        Employee empTemp = userEmployeeRepository.getEmployee(employee.getEntityid()).orElseThrow(() -> new UserNotFoundException("Employee not found with id")) ;

        if(employee.getVerify_status().toUpperCase().equals("VERIFIED")) {
        	verified = true;
        }
        	
        
        if(verified) {
        	
        	empTemp.setVerify_status("VERIFIED");
     		String genPass = StringUtil.generateRandomPassword(7);
     		empTemp.setPassword(passEncoder.encode(genPass));
     		String token = UUID.randomUUID().toString();
     		createVerificationToken(empTemp, token);
     		String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + empTemp.getEmail().trim()
     				+ "&username=" + empTemp.getName_of_Entity();
     		ob1.setSubject(AppConstant.confirmMailSubject);
     		ob1.setVmname(AppConstant.confirmTemplate);
     		ob1.setTo(empTemp.getEmail());
     		body.put("entityType", "Employee");
     		body.put("recieverMail", empTemp.getEmail().trim());
     		body.put("password", genPass);
     		body.put("url", url);
     		ob1.setBody(body);
        	
        } else {
        	
        	empTemp.setVerify_status("REJECTED");

     		ob1.setSubject(AppConstant.rejectMailSubject);
     		ob1.setVmname(AppConstant.rejectTemplate);
     		ob1.setTo(empTemp.getEmail());
     		body.put("entityType", "Employee");
     		body.put("recieverMail", empTemp.getEmail().trim());
     		ob1.setBody(body);
        }
       
            
        Employee empResp = userEmployeeRepository.save(empTemp);
        notifyClient.sendMail(ob1);

        return ResponseHandler.generateResponse("Employee Status changed", HttpStatus.OK, "", empResp);

    }

    public ResponseEntity<Object> getEmployeeDetail(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found with id"));
        if (user.getUser_type() != null && user.getUser_type().equals("Consultant")) {
            return ResponseHandler.generateResponse("Get Consultant", HttpStatus.OK, "", consultantRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Consultant not found with id")));
        } else {

            return ResponseHandler.generateResponse("Get Employee", HttpStatus.OK, "", userEmployeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Employee not found with id")));
        }
        // return userEmployeeRepository.getEmployeeDetails(id);
    }
    
    public ResponseEntity<Object> getEmployeeDetailByEmail(String email) {
    	User u = userRepository.findEmployeeByEmail(email);

    		
    		if (u.getUser_type() != null && u.getUser_type().equals("Consultant")) {
    	          
    			return ResponseHandler.generateResponse("Get Consultant Details Success", HttpStatus.OK, "",consultantRepository.findById(u.getEntityid()).orElseThrow(() -> new UserNotFoundException("Consultant not found with id")));
    	
    		}else {
    			
    			return ResponseHandler.generateResponse("Get Employee Details Success", HttpStatus.OK, "",userEmployeeRepository.findById(u.getEntityid()).orElseThrow(() -> new UserNotFoundException("Employee not found with id")));
    		}
    	
    }
    
    public ResponseEntity<Object> getEmployeeDetailByStatus(String status) {
    	
    			
    	List<Employee> empResp = userEmployeeRepository.findByVerify_status(status.toUpperCase());

        return ResponseHandler.generateResponse("Get "+status+" Employee Details Success", HttpStatus.OK, "", empResp);

    }

    public ResponseEntity<Object> getProjectEmployeeDetail(Integer id) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("1. user not found with id "));
        if (user.getCreated_by() != null && user.getCreated_by() != 1) {
            user = userRepository.findCreatorById(user.getCreated_by()).orElseThrow(() -> new UserNotFoundException("2. user not found with id"));
        } else {
            user = userRepository.findCreatorById(id).orElseThrow(() -> new UserNotFoundException("3. user not found with id"));
        }
        if (user.getUser_type() != null && user.getUser_type().equals("Consultant")) {
            return ResponseHandler.generateResponse("Get Consultant", HttpStatus.OK, "", consultantRepository.findById(user.getEntityid()).orElseThrow(() -> new UserNotFoundException("Consultant not found with id")));
        } else {

            return ResponseHandler.generateResponse("Get Employee", HttpStatus.OK, "", userEmployeeRepository.findById(user.getEntityid()).orElseThrow(() -> new UserNotFoundException("Employee not found with id")));
        }
    }

    public ResponseEntity<Object> getEmployeeList1() {

        Integer orgId = userEmployeeRepository.findOrganisationId(getuserId());

        List<Employee> e = userEmployeeRepository.getEmployeeList(orgId);

        return ResponseHandler.generateResponse("Get Employee List", HttpStatus.OK, "", e);
        // return e;
    }

    public ResponseEntity<Object> getEmployeeList(String active, UserPrincipal currentUser) {
        Integer orgId = userEmployeeRepository.findOrganisationId(currentUser.getId());
        if (active != null) {
            return ResponseHandler.generateResponse("Get Employee List ", HttpStatus.OK, "", userEmployeeRepository.get_all_EmployeebyStatus(orgId, active));
        } else {
            return ResponseHandler.generateResponse("Get Employee List ", HttpStatus.OK, "", userEmployeeRepository.get_all_Employees(orgId));
        }
    }

    public ResponseEntity<Object> getEmployeeListwithSearch(String active, String search, UserPrincipal currentUser) {
        Integer orgId = userEmployeeRepository.findOrganisationId(currentUser.getId());
        if (active != null) {
            return ResponseHandler.generateResponse("Get Employee List with Search", HttpStatus.OK, "", userEmployeeRepository.get_all_EmployeebyStatusWithSearch(orgId, active, search));
            // return
            // userEmployeeRepository.get_all_EmployeebyStatusWithSearch(orgId,active,search);
        } else {
            return ResponseHandler.generateResponse("Get Employee List with Search", HttpStatus.OK, "", userEmployeeRepository.get_all_EmployeesWithSearch(orgId, search));
            // return userEmployeeRepository.get_all_EmployeesWithSearch(orgId,search);
        }
    }

    public ResponseEntity<Object> updateEmployee(Employee employee, UserPrincipal currentUser) {
        Integer orgId = userEmployeeRepository.findOrganisationId(currentUser.getId());
        Organisation organisation = organisationRepository.findById(orgId).orElseThrow(() -> new UserNotFoundException("organisation not found for logged in user"));
        User user = userRepository.findById(employee.getEntityid()).orElseThrow(() -> new UserNotFoundException("User not found with id"));
        employee.setOrganisation(organisation);
        employee.setEmailId(employee.getEmail());
        employee.setFailedAttemptCount(user.getFailedAttemptCount());
        employee.setStatus(user.getStatus());
        employee.setIsActive(user.getIsActive());
        employee.setFirstName(employee.getName_of_Entity());
        employee.setUser_type(user.getUser_type());
        employee.setMobileNumber(employee.getMobile());
        employee.setName(employee.getName_of_Entity());
        employee.setUserType(user.getUser_type());
        employee.setStd_code(employee.getStd_code());
        Employee empTemp = userEmployeeRepository.save(employee);
        return ResponseHandler.generateResponse("Update Employee", HttpStatus.OK, "", empTemp);
        // return "SUCCESS";
    }

    public ResponseEntity<Object> deleteEmployee(List<Integer> Id) {
        /*
         * Id.forEach(ids -> { List<ProjectDetails> temp =
         * projectDetailRepository.findProjectsByUserId(ids); temp.forEach(project -> {
         * project.removeEmployee(ids); projectDetailRepository.save(project); }); });
         */

        for (Integer EmpId : Id) {
            userRepository.deleteEmployeeId(EmpId);
            /*
             * user ob1=userRepository.getById(EmpId);
             *
             * Employee emp=userEmployeeRepository.getById(EmpId);
             *
             * emp.setIs_active(false); emp.setIs_delete(false); userRepository.save(ob1);
             * userEmployeeRepository.save(emp);
             */
        }
        return ResponseHandler.generateResponse("Delete Employee", HttpStatus.OK, "", "SUCCESS");
        // return "SUCCESS";
    }

    /*
     * Utility For fetching the UserId
     */
    public Integer getuserId() {
        return 1;
    }

    private boolean entityExist(ProjectProponentGovernment entity) {
        if (entity.getState() == null) {
            return userRepository.findRecordByEntity(entity.getName_of_Entity()).isPresent();
        } else {
            return userRepository.findRecordByEntityAndState(entity.getName_of_Entity(), entity.getState()).isPresent();
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

    public ResponseEntity<Object> ChangeUserPassword(ChangePassword changeRequest) {
        User uLogin = userRepository.findRecordByEmail(changeRequest.getEmail()).get(0);

        String getOldPassword = new String(Base64.getDecoder().decode(changeRequest.getOldPassword()), StandardCharsets.UTF_8);
        String getNewPassword = new String(Base64.getDecoder().decode(changeRequest.getNewPassword()), StandardCharsets.UTF_8);


        if (uLogin != null) {
            if (passEncoder.matches(getOldPassword, uLogin.getPassword())) {
                uLogin.setPassword(passEncoder.encode(getNewPassword));
                userRepository.save(uLogin);
            } else {
                return ResponseHandler.generateResponse("Change password", HttpStatus.OK, "", "Invalid old Password");
//				throw new EntityNotFoundException("Invalid old Password");
            }
//			return true;
            return ResponseHandler.generateResponse("Change password", HttpStatus.OK, "", "Password has been changed");
        } else {
            return ResponseHandler.generateResponse("Change password", HttpStatus.OK, "", "Invalid Username");
//			throw new EntityNotFoundException("Invalid Username");
        }
    }

    public boolean duplicatePan(String pan, String type) {
        if (type.equals(AppConstant.typeIndividual)) {
            return (userIndividualRepository.findRecordByPan(pan).isPresent() || userOtherRepository.findRecordByPan(pan).isPresent());
        } else if (Arrays.stream(AppConstant.typeEntity).anyMatch(type::contains)) {
            return userEntityRepository.findRecordByPan(pan).isPresent();
        } else if (type.equals(AppConstant.typeOthers)) {
            return (userOtherRepository.findRecordByPan(pan).isPresent() || userIndividualRepository.findRecordByPan(pan).isPresent());
        } else {
            return userGovernmentRepository.findRecordByPan(pan).isPresent();
        }
    }

    public boolean duplicateCIN(String CIN, String type) {
        if (Arrays.stream(AppConstant.typeEntity).anyMatch(type::contains)) {
            return userEntityRepository.findRecordByCIN(CIN).isPresent();
        } else {
            return userGovernmentRepository.findRecordByCIN(CIN).isPresent();
        }
    }

    public boolean duplicateEmail(String email, String type) {

    	if(type.toLowerCase().equals("employee")) {
    		
            Boolean flag = userRepository.isDuplicateEmployee(email).size() >= 1;
            log.info("### Flag for duplicate Email is " + flag);
            return flag;
    	}
    	
        Boolean flag = userRepository.findRecordByEmail(email).size() >= 1;
        log.info("### Flag for duplicate Email is " + flag);
        return flag;
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

    public <T> void validateInput(T input) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Map<String, Object> getEntityTypes() {
        List<GenCodeMaster> genCodeMasters = genCodeMasterRepository.findAllByParentGrp("ENTITY_TYPE");
        List<String> types = genCodeMasters.stream().map(e -> e.getName()).collect(Collectors.toList());
        Map<String, Object> groups = new HashMap<>();
        String group1 = genCodeMasters.stream().filter(e -> e.getName().equals(AppConstant.typeIndividual)).findAny().orElse(null).getName();
        List<String> group2 = genCodeMasters.stream().filter(e -> Arrays.asList(AppConstant.typeEntity).contains(e.getName())).map(e -> e.getName()).collect(Collectors.toList());
        List<String> group3 = genCodeMasters.stream().filter(e -> Arrays.asList(AppConstant.typeGovernment).contains(e.getName())).map(e -> e.getName()).collect(Collectors.toList());
        String group4 = genCodeMasters.stream().filter(e -> e.getName().equals(AppConstant.typeOthers)).findAny().orElse(null).getName();
        groups.put("Types", types);
        groups.put("Group1", group1);
        groups.put("Group2", group2);
        groups.put("Group3", group3);
        groups.put("Group4", group4);
        return groups;
    }

    public ResponseEntity<Object> getOrganisationDetail(UserPrincipal principal) {
        User user = userRepository.findById(principal.getId()).orElseThrow(() -> new UserNotFoundException("user not found with id"));
        if (user.getUser_type() != null && user.getUser_type().equals("Consultant")) {
            String orgId = consultantRepository.findOrganisationId(principal.getId());
            return ResponseHandler.generateResponse("Organisation details", HttpStatus.OK, "", consultantOrganisationRepository.findByOrg_id(orgId));
        } else {
            Integer orgId = userEmployeeRepository.findOrganisationId(principal.getId());
            return ResponseHandler.generateResponse("Organisation details", HttpStatus.OK, "", organisationRepository.findById(orgId).orElseThrow(() -> new UserNotFoundException("Organisation detail not found with id")));
        }
        // return organisationRepository.findById(getuserId()).get();
    }

    public ProjectProponentEntity getEntity(String emailId) {
        Optional<ProjectProponentEntity> user = userEntityRepository.findRecordByEmail(emailId);
        return user.get();
    }

    public User getLoggedInUser(int id) {

//		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id"));
        User user = userRepository.getUserDataById(id);
        //added for logged user
        String designation = user.getSelected_role() == 222 ? userRepository.getDesignationPP(id) : userRepository.getDesignationOther(id);
        user.setDesignation(designation);
        //added for logged user
        return user;
    }

    public boolean duplicateEntity(String entity, String state) {
        if (state == null) {
            return userRepository.findRecordByEntity(entity).isPresent();
        } else {
            return userRepository.findRecordByEntityAndState(entity, state).isPresent();
        }
    }

    public ResponseEntity<Object> resendMail(String email) {
        log.info("Email for resend confirmation is " + email);
        String genPass = StringUtil.generateRandomPassword(7);
        User user = userRepository.findRecordByEmailId(email).get(0);
        log.info("User object is " + user.toString());
        userRepository.updatePassword(user.getEntityid(), passEncoder.encode(genPass));
//		log.info("Updated User object is "+updatedUser.toString());
        String token = UUID.randomUUID().toString();
        createVerificationToken(user, token);

        // String url =
        // "http://localhost:4200/activateEmail?token="+token+"&email="+individual2.getEmail();
        String url = environment.getProperty(AppConstant.tokenUrl) + token + "&email=" + email.trim() + "&username=" + user.getName_of_Entity();
        MailContent ob1 = new MailContent();
        ob1.setSubject(AppConstant.confirmMailSubject);
        ob1.setVmname(AppConstant.confirmTemplate);
        ob1.setTo(email.trim());
        Map<String, String> body = new HashMap<>();
        body.put("entityType", user.getUser_type());
        body.put("recieverMail", email.trim());
        body.put("password", genPass);
        body.put("url", url);
        ob1.setBody(body);

        // Kafka Producer Publishes the Object on the Topic
        notifyClient.sendMail(ob1);
        return ResponseHandler.generateResponse("Verification Mail sent", HttpStatus.OK, "", "Message Sent successfully");
    }

    public ResponseEntity<Object> migrateEmails(ArrayList<String> emails) {
        try {
            log.info("In MigrateEmails");
            List<String> statusEmail = emails.stream().map(value -> {
                Employee emp = userEmployeeRepository.searchEmployeebyEmailForMigration(value);
                Organisation org = organisationRepository.getOrganisationByEmailForMigration(value);
                log.info("Employee is and Organisation is " + value);
                String pass = "Test@1234";
                if ((emp != null) && (org != null)) {
                    log.info("Employee and Organisation for {} is not Null", value);
                    registrationService.UserRegistration(emp, org, pass);
                    emp.setIs_migrated_to_Parivesh1(true);
                    userEmployeeRepository.save(emp);
                }
                return value;
            }).collect(Collectors.toList());


            return ResponseHandler.generateResponse("In MigrateEmails to Migrate User 1.0 ", HttpStatus.OK, "", "Success in migrating " + statusEmail);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("In MigrateEmails to Migrate User 1.0", HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage());
        }

    }

    public ResponseEntity<Object> getSelectedRoleName(Integer id) {
        Integer role = userRepository.getSelectedRoleId(id);
        SelectedRoleDto dto = new SelectedRoleDto();
        dto.setRole(userRepository.getSelectedRoleName(id));
        if (role == 222) {
            dto.setDesignation(userRepository.getDesignationPP(id));
        } else {
            dto.setDesignation(userRepository.getDesignationOther(id));
        }
        List<String> jurisdiction = userRepository.getLoggedinUserDetail(id);
        dto.setJurisdiction(String.join(",", jurisdiction));

        return ResponseHandler.generateResponse("Role and Designation", HttpStatus.OK, "No Error", dto);
    }

//    public ResponseEntity<Object> getSelectedRoleNameV2(Integer id) {
//
//        List<SelectedRoleDto> dto = userRepository.getLoggedinUserDetails(id);
//
//        return ResponseHandler.generateResponse("Role and Designation", HttpStatus.OK, "No Error", dto);
//    }

    public ResponseEntity<Object> getSelectedSector(Integer id) {
        SelectedSectorDto dto = userRepository.getSelectedSector(id);
        if (dto == null) {
            return ResponseHandler.generateResponse("selected_sector is null in user_entity for entityId-" + id, HttpStatus.OK, "No Error", dto);
        }
        return ResponseHandler.generateResponse("sector_id and sector_name", HttpStatus.OK, "No Error", dto);
    }
    
    public List<com.backend.model.Organisation> searchOrganization(String name){
    	
    	return userRepository.searchOrganization(name.toLowerCase());
    }

}
