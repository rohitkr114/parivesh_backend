package com.backend.NSWS.service;

import com.backend.NSWS.external.client.NswsPushLicenseClient;
import com.backend.NSWS.external.client.NswsRedirectionClient;
import com.backend.NSWS.external.client.NswsSsoTokenClient;
import com.backend.NSWS.external.client.PariveshTokenClient;
import com.backend.NSWS.external.request.AuthenticationRequest;
import com.backend.NSWS.external.response.AuthenticationResponse;
import com.backend.NSWS.model.NSWSUserDetails;
import com.backend.NSWS.repository.NSWSUserRepository;
import com.backend.NSWS.requestbody.GenerateTokenRequestBody;
import com.backend.NSWS.requestbody.PingRequestBody;
import com.backend.NSWS.requestbody.PushLicenseBody;
import com.backend.NSWS.responsebody.GeneratetokenResponseBody;
import com.backend.NSWS.responsebody.PingResponseBody;
import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.model.*;
import com.backend.parivesh.nic.UserRegistrationService;
import com.backend.repository.postgres.*;
import com.backend.response.ResponseHandler;
import com.backend.security.JwtTokenUtil;
import com.backend.security.JwtUserDetailsService;
import com.backend.service.DistrictService;
import com.backend.service.UserService;
import com.backend.util.StringUtil;
import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class PariveshSWSService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserIndividualRepository userIndividualRepository;

    @Autowired
    private UserOtherRepository userOtherRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserGovernmentRepository userGovernmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private PasswordEncoder passEncoder;
    @Autowired
    private UserEmployeeRepository userEmployeeRepository;
    @Autowired
    private UserRegistrationService registrationService;

    @Autowired
    private PariveshTokenClient generateTokenClient;

    @Autowired
    private NSWSUserRepository nswsUseRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NswsSsoTokenClient createSsoToken;

    @Autowired
    private NswsRedirectionClient nswsRedirectionClient;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private NswsPushLicenseClient nswsPushLicenseClient;

    public ResponseEntity<Object> pariveshPing(PingRequestBody pingBody, HttpServletRequest request) throws PariveshException, JSONException {

        NSWSUserDetails nswsUser = new NSWSUserDetails();

        Boolean isPanWithEmailExist = userIndividualRepository.checkUserByPanAndEmail(pingBody.getPan_no(), pingBody.getEmail());

        if (!isPanWithEmailExist && (userIndividualRepository.findRecordByPan(pingBody.getPan_no()).isPresent() || userOtherRepository.findRecordByPan(pingBody.getPan_no()).isPresent() || userEntityRepository.findRecordByPan(pingBody.getPan_no()).isPresent() || userGovernmentRepository.findRecordByPan(pingBody.getPan_no()).isPresent())) {

            return ResponseHandler.generateResponse("", HttpStatus.CONFLICT, "User Already exist in the registration process with Pan number : " + pingBody.getPan_no(), new PingResponseBody(pingBody.getEmail(), pingBody.getInvestorSWSId()));
        } else if (!isPanWithEmailExist && userRepository.findRecordByEmail(pingBody.getEmail()).size() >= 1) {

            return ResponseHandler.generateResponse("", HttpStatus.CONFLICT, "User Already exist in the registration process with email ID : " + pingBody.getEmail(), new PingResponseBody(pingBody.getEmail(), pingBody.getInvestorSWSId()));
        } else {
            Organisation organisation = new Organisation();

            Applications applications = new Applications();

            applications = applicationsRepository.getApplicationsByApprovalId(pingBody.getApprovalId());

            ProjectProponentIndividual individual = new ProjectProponentIndividual();
            ProjectProponentIndividual individual2 = new ProjectProponentIndividual();

            if (!ObjectUtils.isEmpty(pingBody)) {
                BeanUtils.copyProperties(pingBody, individual);
            }
            if (isPanWithEmailExist) {
                Optional<ProjectProponentIndividual> temp = userIndividualRepository.findRecordByEmail(pingBody.getEmail());
                individual.setId(temp.get().getId());
                individual2 = userIndividualRepository.save(individual);
            } else {
                individual2 = userIndividualRepository.save(individual);
            }

            District d = districtService.getStatebyDistrictCode(Integer.valueOf(pingBody.getDistrict()));
            if (!(Integer.valueOf(pingBody.getState_ut()) == d.getState_code())) {
                return ResponseHandler.generateResponse("User Registration Individual", HttpStatus.PRECONDITION_FAILED, "", "State not Same");
            }

            if (pingBody.getIs_organisation() == true) {
                Organisation organisationtemp = new Organisation();
                organisationtemp.setName(pingBody.getName_of_Entity());
                organisationtemp.setState_ut(pingBody.getOrg_state_ut().toString());
                organisationtemp.setEntity_type(AppConstant.typeIndividual);
                organisationtemp.setDistrict(pingBody.getOrg_district().toString());
                organisationtemp.setPincode(pingBody.getOrg_pincode());
                organisationtemp.setAddress(pingBody.getOrg_address());
                organisationtemp.setEmail(pingBody.getEmail().trim());
                organisationtemp.setMobile_no(pingBody.getMobile());

                organisation = organisationRepository.save(organisationtemp);
            } else {
                organisation = organisationRepository.save(new Organisation(individual2, AppConstant.typeIndividual));
            }
            Employee employee = new Employee(individual2, AppConstant.typeIndividual, request);
            String genPass = StringUtil.generateRandomPassword(7);
            employee.setPassword(passEncoder.encode(genPass));
            employee.setEmailId(employee.getEmail().trim());
            employee.setOrganisation(organisation);
            employee.setUserid(pingBody.getInvestorSWSId());
            employee.setIsActive(true);
            employee.setUserCreationStatus("APPROVED");
            Employee employee2 = userEmployeeRepository.save(employee);
            registrationService.UserRegistration(employee2, organisation, genPass);
            String token = UUID.randomUUID().toString();
            userService.createVerificationToken(employee2, token);

            nswsUser.setEntityId(employee2.getEntityid());
            nswsUser.setEmail(pingBody.getEmail());
            nswsUser.setPanNO(pingBody.getPan_no());
            nswsUser.setInvestorReqId(pingBody.getInvestorReqId());
            nswsUser.setInvestorSWSId(pingBody.getInvestorSWSId());
            nswsUser.setApprovalId(pingBody.getApprovalId());
            nswsUser.setProjectId(pingBody.getProjectId());
            nswsUser.setDepartmentId(applications.getDepartmentId());
            nswsUser.setMinistryId(applications.getMinistryId());

            NSWSUserDetails temp = nswsUseRepository.save(nswsUser);

            ResponseEntity<String> ssoTokenResponse = createSsoToken.createSsoToken();
            if (ssoTokenResponse.getStatusCode() != HttpStatus.OK) {
//                throw new PariveshException("Error in accessing NSWS Token API");
                return ResponseHandler.generateResponse("", ssoTokenResponse.getStatusCode(), "Error in accessing NSWS Token API", new PingResponseBody(pingBody.getEmail(), pingBody.getInvestorSWSId()));
            }
            String tokenJson = ssoTokenResponse.getBody();

            JSONObject jsonObject = new JSONObject(tokenJson.toString());

            String accessToken = jsonObject.getString("access_token");

            ResponseEntity<String> redirectUrlResponse = nswsRedirectionClient.nswsRedirectUrl(accessToken, temp, applications);
            log.info("REDIRECTURLRESPONSE :{}", redirectUrlResponse);
            if (redirectUrlResponse.getStatusCode() != HttpStatus.OK) {
//                throw new PariveshException("Error in accessing NSWS Redirection API");
                return ResponseHandler.generateResponse("", redirectUrlResponse.getStatusCode(), "Error in accessing NSWS Redirection API", new PingResponseBody(pingBody.getEmail(), pingBody.getInvestorSWSId()));
            }

            return ResponseHandler.generateResponse("User has been successfully registered on Parivesh Portal", HttpStatus.OK, "", new PingResponseBody(pingBody.getEmail(), pingBody.getInvestorSWSId()));
        }
    }

    public ResponseEntity<Object> generateToken(GenerateTokenRequestBody tokenRequestBody) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        AuthenticationRequest authRequest = encryptPassword(tokenRequestBody);

        AuthenticationResponse response = generateTokenClient.createAuthenticationToken(authRequest).getBody();

        return ResponseHandler.generateResponse("Token has been successfully generated.", HttpStatus.OK, "", new GeneratetokenResponseBody(tokenRequestBody.getUsername(), response.getJwt()));

    }

    public static AuthenticationRequest encryptPassword(GenerateTokenRequestBody tokenRequestBody) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        AuthenticationRequest authRequest = new AuthenticationRequest();

        String[] strinArray = {"s", "e", "c", "r", "e", "t", "k", "e", "y"};

        StringBuilder secretKeyText = new StringBuilder();
        for (int j = 1; j < strinArray.length; j++) {
            secretKeyText.append(strinArray[(int) (Math.random() * strinArray.length)]);
        }

        String keyBase64 = Base64.encodeBase64String((secretKeyText.toString() + Math.random()).getBytes());

        keyBase64 = Base64.encodeBase64String(keyBase64.getBytes()).substring(0, 32);

        String propParivesh = keyBase64;

        String password = tokenRequestBody.getPassword();

        byte[] keyBytes = Base64.decodeBase64(keyBase64.getBytes());
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        String encryptedPassword = Base64.encodeBase64String(encryptedBytes);

        authRequest.setUsername(tokenRequestBody.getUsername());
        authRequest.setPassword(encryptedPassword);
        authRequest.setPropParivesh(propParivesh);

        return authRequest;
    }

    public ResponseEntity<Object> pushLicenseApi(List<PushLicenseBody> pushLicenseBody) {

        ResponseEntity<String> pushLicenseResponse = nswsPushLicenseClient.nswsPushLicense(pushLicenseBody);
        log.info("PUSHLICENSERESPONSE :{}", pushLicenseResponse);

        return ResponseHandler.generateResponse("License Details has been pushed Successfully.", HttpStatus.OK, "", "");
    }
}
