package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.dto.HomePageMasterResponse;
import com.backend.model.FAQ;
import com.backend.model.HomeApprovalMaster;
import com.backend.model.HomeHeroMaster;
import com.backend.model.HomeKPIMaster;
import com.backend.model.HomePageMedia;
import com.backend.model.HomeRecentMaster;
import com.backend.model.HomeRecentUploadsMaster;
import com.backend.model.HomeUpdateMaster;
import com.backend.model.HomeUsefulLinkMaster;
import com.backend.response.ResponseHandler;
import com.backend.service.HomePageService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/home/")
public class HomePageController {

	@Autowired
	private HomePageService homePageService;

	@GetMapping("/hero")
	public ResponseEntity<Object> getHeroMasterData(@RequestParam(value = "active", required = false) String active) {
		if(active != null) {
			return homePageService.getHeroMaster(active);
		} else {			
			return homePageService.getHeroMaster(active);
		}
	}

	@RequestMapping(value = "/hero/save", method = RequestMethod.POST)
	public ResponseEntity<Object> heroDashboard(@RequestBody HomeHeroMaster homeHero) {
		HomeHeroMaster response = homePageService.saveHero(homeHero);
		HomePageMasterResponse res = new HomePageMasterResponse();
		res.setMappingId(response.getId());
		res.setType("hero");
		return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",res);
		//return res;
	}

	@GetMapping("/approvals")
	public ResponseEntity<Object> getApprovalMasterData(@RequestParam(value = "active", required = false) String active) {
		if(active != null) {
			return homePageService.getApprovalMaster(active);
		} else {			
			return homePageService.getApprovalMaster(active);
		}
		
	}

	@RequestMapping(value = "/approvals/save", method = RequestMethod.POST)
	public ResponseEntity<Object> approvalDashboard(@RequestBody HomeApprovalMaster homeApproval) {
		HomeApprovalMaster response = homePageService.saveApprovals(homeApproval);
		HomePageMasterResponse res = new HomePageMasterResponse();
		res.setMappingId(response.getId());
		res.setType("approvals");
		return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",res);
		//return res;
	}

	@GetMapping("/kpi")
	public ResponseEntity<Object> getKpiMasterData(@RequestParam(value = "active", required = false) String active) {
		if(active != null) {
			return homePageService.getKpiMaster(active);
		} else {			
			return homePageService.getKpiMaster(active);
		}
	}

	@RequestMapping(value = "/kpi/save", method = RequestMethod.POST)
	public ResponseEntity<Object> kpiDashboard(@RequestBody HomeKPIMaster kpiApprovals) {
		HomeKPIMaster response = homePageService.saveKPI(kpiApprovals);
		HomePageMasterResponse res = new HomePageMasterResponse();
		res.setMappingId(response.getId());
		res.setType("kpi");
		return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",res);
	}

	
	@GetMapping("/recentEvents")
	public ResponseEntity<Object> getRecentMasterData(@RequestParam(value = "active", required = false) String active) {
		if(active != null) {
			return homePageService.getRecentMaster(active);
		} else {			
			return homePageService.getRecentMaster(active);
		}
		
	}

	@RequestMapping(value = "/recentEvents/save", method = RequestMethod.POST)
	public ResponseEntity<Object> recentDashboard(@RequestBody HomeRecentMaster recentMaster) {
		HomeRecentMaster response = homePageService.saveRecent(recentMaster);
		HomePageMasterResponse res = new HomePageMasterResponse();
		res.setMappingId(response.getId());
		res.setType("recentEvents");
		return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",res);
		//return res;
	}

	@PostMapping("/getMedia")
	public ResponseEntity<Object> getMedia(@RequestParam(value = "active", required = false) String active){
		if(active != null) {
			return homePageService.getMedia(active);
		} else {			
			return homePageService.getMedia(active);
		}
	}
	
	@PostMapping(value = "/getMedia/save")
	public ResponseEntity<Object> saveMedia(@RequestBody HomePageMedia homePageMedia) {
		HomePageMedia response = homePageService.saveMedia(homePageMedia);
		HomePageMasterResponse res = new HomePageMasterResponse();
		res.setMappingId(response.getId());
		res.setType("media");
		return ResponseHandler.generateResponse("Save Home Page Media",HttpStatus.OK,"",res);
	}
	
	@PostMapping("/getFAQ")
	public ResponseEntity<Object> getFAQ(@RequestParam(value = "active", required = false) String active){
		if(active != null) {
			return homePageService.getFAQ(active);
		} else {			
			return homePageService.getFAQ(active);
		}
	}
	
	@PostMapping(value = "/getFAQ/save")
	public ResponseEntity<Object> saveFAQ(@RequestBody FAQ faq) {
		FAQ response = homePageService.saveFAQ(faq);
		HomePageMasterResponse res = new HomePageMasterResponse();
		res.setMappingId(response.getId());
		res.setType("recentEvents");
		return ResponseHandler.generateResponse("Save Home Page FAQ",HttpStatus.OK,"",res);
	}
	
	@GetMapping("/updates")
	public ResponseEntity<Object> getUpdateMasterData(@RequestParam(value = "active", required = false) String active) {
		if(active != null) {
			return homePageService.getUpdateMaster(active);
		} else {			
			return homePageService.getUpdateMaster(active);
		}
		
	}

	@RequestMapping(value = "/updates/save", method = RequestMethod.POST)
	public ResponseEntity<Object> updateDashboard(@RequestBody HomeUpdateMaster updateMaster) {
		ResponseEntity<Object> response = homePageService.saveUpdate(updateMaster);
		return response;
	}
	
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	public ResponseEntity<Object> updateImageOrder(@RequestBody List<Object> order, @RequestParam("type") String type) {
		ResponseEntity<Object> response = homePageService.updateOrder(order,type);
		return response;
	}
	
	@GetMapping("/recentUploads")
	public ResponseEntity<Object> getRecentUploadsMasterData(@RequestParam(value = "active", required = false) String active) {
		if(active != null) {
			return homePageService.getHomeRecentUpload(active);
		} else {			
			return homePageService.getHomeRecentUpload(active);
		}
	}

	@RequestMapping(value = "/recentUploads/save", method = RequestMethod.POST)
	public ResponseEntity<Object> heroDashboard(@RequestBody HomeRecentUploadsMaster homeRecentUploadsMaster) {
		HomeRecentUploadsMaster response = homePageService.saveRecentUpload(homeRecentUploadsMaster);
		HomePageMasterResponse res = new HomePageMasterResponse();
		res.setMappingId(response.getId());
		res.setType("recentUploads");
		return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",res);
	}
	
	@GetMapping("/usefulLink")
	public ResponseEntity<Object> getUsefulLinkMasterData(@RequestParam(value = "active", required = false) String active) {
		if(active != null) {
			return homePageService.getHomeUsefulLink(active);
		} else {			
			return homePageService.getHomeUsefulLink(active);
		}
	}

	@RequestMapping(value = "/usefulLink/save", method = RequestMethod.POST)
	public ResponseEntity<Object> heroDashboard(@RequestBody HomeUsefulLinkMaster homeUsefulLinkMaster) {
		HomeUsefulLinkMaster response = homePageService.saveHomeUsefulLink(homeUsefulLinkMaster);
		HomePageMasterResponse res = new HomePageMasterResponse();
		res.setMappingId(response.getId());
		res.setType("usefulLink");
		return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",res);
		//return res;
	}
	
	@RequestMapping(value = "/deleteContent", method = RequestMethod.POST)
	public ResponseEntity<Object> updateImageOrder(@RequestBody Object obj, @RequestParam("type") String type) {
		ResponseEntity<Object> response = homePageService.deleteContent(obj,type);
		return response;
	}

	@PostMapping(value = "/uploadfile", consumes = { "multipart/form-data", MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("mapping_Id") Integer id,
			@RequestParam("type") String type) {
		String filename = homePageService.fileUpload(file, id, type);
		//return "file uploaded successfully: "+filename;
		return ResponseHandler.generateResponse("Upload File",HttpStatus.OK,"","file uploaded successfully: "+filename);
	}
}
