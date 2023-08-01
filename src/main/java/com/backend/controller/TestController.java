package com.backend.controller;

import com.backend.constant.AppConstant;
import com.backend.dto.UserPrincipal;
import com.backend.model.RemovedProposals;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.ConditionDetailService;
import com.backend.service.NotificationService;
import com.backend.service.RemovedProposalsService;
import com.backend.service.TestService;
import com.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/response")
public class TestController {

    @Autowired
    private Environment environment;

    @Autowired
    private ConditionDetailService conditionDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    @Autowired
    private NotificationService notificationSevice;
    
    @Autowired
    private RemovedProposalsService removedProposalsService;

    private final Path fileStorageLocation = Paths.get("/opt/war").toAbsolutePath().normalize();
//	private final Path fileStorageLocation = Paths.get("C:\\Users\\JS494ET\\Rahul").toAbsolutePath().normalize();

//    @PostMapping("/test2")
//    public ResponseEntity<Object> testControllerMethod(@RequestBody(required = false) RemovedProposals proposals,@CurrentUser UserPrincipal principal ) {      
//    	
//    	 removedProposalsService.sendEmailNotificationInfo(proposals, principal);
//    	 
//    	return ResponseHandler.generateResponse("Test Controller for Email notify in remove proposals", HttpStatus.OK, "", null);
//    }
    
    @GetMapping("/log")
    public ResponseEntity<?> fetchCurrentLogFile(@RequestParam(required = false) String fileName) {
        String logFolderPath = "/opt/logs/";
//        String logFolderPath = "logs/";
        String currentLogFilePath = logFolderPath + fileName;

        if (isEmptyOrNull(fileName)) {
            return fetchFolderContents(logFolderPath);
        } else {
            return fetchFile(currentLogFilePath);
        }
    }

    @GetMapping("/log/archives")
    public ResponseEntity<?> getArchivedLogs(@RequestParam(required = false) String fileName) {
        String archivedFolderPath = "/opt/logs/archived/";
//        String archivedFolderPath = "logs/archived/";
        String archivedLogFilePath = archivedFolderPath + fileName;

        if (isEmptyOrNull(fileName)) {
            return fetchFolderContents(archivedFolderPath);
        } else {
            return fetchFile(archivedLogFilePath);
        }
    }

    private ResponseEntity<?> fetchFile(String filePath) {
        ByteArrayResource resource;

        try {
            Path path = Paths.get(filePath);
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }

    private ResponseEntity<?> fetchFolderContents(String folderPath) {
        List<String> folderContents;

        try {
            Path path = Paths.get(folderPath);
            File folder = path.toFile();
            folderContents = Arrays.stream(Objects.requireNonNull(folder.list())).filter(contents -> contents.endsWith(".log")).collect(Collectors.toList());
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }

        return ResponseEntity.ok().body(folderContents);
    }

    private boolean isEmptyOrNull(String str) {
        return str == null || "".equals(str.trim());
    }

    @PostMapping(value = "/uploadWar", consumes = {"multipart/form-data", MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) {
        String fileName = environment.getProperty(AppConstant.warFile);

        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return "file uploaded successfully: "+filename;
        return ResponseHandler.generateResponse("War File", HttpStatus.OK, "", "file uploaded successfully: " + fileName);
    }

//	@PostMapping(value = "/uploadExcel", consumes = { "multipart/form-data", MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<Object> uploadExcelFile(@RequestParam("file") MultipartFile file){
//		
//		if(!file.isEmpty()) {
//			System.out.println("name:"+ file.getOriginalFilename());
//			System.out.println("type:" + file.getContentType());
//			ExcelUtil.uploadFile(file);
//		}
//		return ResponseHandler.generateResponse("File upload successfully", HttpStatus.OK, "", null);
//		
//	}

    @PostMapping(value = "/status")
    public ResponseEntity<Object> checkServerStatus() {
        try {
            return ResponseHandler.generateResponse("Check Status", HttpStatus.OK, "", "SUCCESS");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Check Status", HttpStatus.OK, "", e.getMessage());
        }
    }

    @PostMapping(value = "/test")
    public ResponseEntity<Object> testController(Integer id) {
        try {

//            userService.resendMail(id);
            notificationSevice.sendPaymentNotification(id);

        } catch (Exception e) {
            //log.info("=====================>>>>>>>>>>>>>>>>>>>>>>" + e);
            return ResponseHandler.generateResponse("Test API", HttpStatus.INTERNAL_SERVER_ERROR, "", e.getMessage());

        }
        return ResponseHandler.generateResponse("Test API", HttpStatus.OK, "", "SUCCESS");
    }

    @PostMapping(value = "/getComplianceByEmailId")
    public ResponseEntity<Object> getComplianceData(@RequestParam("email") String email) {
        try {
            return testService.getCompliance(email);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Test API", HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage());
        }

    }

    @PostMapping(value = "/getConditiondetail")
    public ResponseEntity<Object> getCompliancedetail(@RequestParam("proposal_id") Integer proposal_id) {
        try {
            return conditionDetailService.getCompliancedetail(proposal_id);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Test API", HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage());
        }
    }
}
