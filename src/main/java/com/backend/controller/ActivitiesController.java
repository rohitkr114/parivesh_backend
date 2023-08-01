package com.backend.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Activities;
import com.backend.model.GenCodeMaster;
import com.backend.model.SectorEntity;
import com.backend.model.ThresholdParameter;
import com.backend.repository.postgres.ActivityRepository;
import com.backend.repository.postgres.GenCodeMasterRepository;
import com.backend.repository.postgres.SectorEntityRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.RequestServiceImpl;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/kya/")
public class ActivitiesController {

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	GenCodeMasterRepository genCodeMasterRepository;

	@Autowired
	SectorEntityRepository sectorEntityRepository;

	// Service to fetch IP Address
	@Autowired
	RequestServiceImpl requestServiceImpl;

	@RequestMapping(value = "/newactivity", method = RequestMethod.POST)
	public String newActivity(@RequestBody Activities newactivity) {
		try {
			activityRepository.save(newactivity);
			return "SUCCESS";
		} catch (Exception ex) {
				return ex.getMessage();
		}
	}

	@RequestMapping(value = "/updateSectorToActivity", method = RequestMethod.GET)
	public String addSector(@RequestParam Integer ActivityId, @RequestParam Long SectorId) {
		try {
			Activities activityTemp = activityRepository.findById(ActivityId).orElseThrow();
			SectorEntity sectorTemp = sectorEntityRepository.findById(SectorId).orElseThrow();
			activityTemp.setSectorEntity(sectorTemp);

			activityRepository.save(activityTemp);

			return "Sector mapped to Activity Successfully";

		} catch (Exception ex) {
			return "Exception " + ex;
		}
	}

	@RequestMapping(value = "/getactivities", method = RequestMethod.GET)
	public List<Activities> getActivities(@RequestParam(value = "active", required = false) String active) {
		List<Activities> activities;
		if (active == null) {
			activities = activityRepository.findAllActivities();
		} else {
			if (active.equals("true")) {
				activities = activityRepository.findAllActivitiesbyStatus("true", "false");
			} else {
				activities = activityRepository.findAllActivitiesbyStatus("false", "true");
			}
		}
		return activities;
	}

	@GetMapping("/getactivity/id")
	public List<Activities> getActivityId(@RequestParam Integer id) {
		return (activityRepository.findAllActivityById(id));
	}

	@RequestMapping(value = "deleteActivitybyId", method = RequestMethod.GET)
	public String deleteActivity(@RequestParam("id") Integer id) {
		activityRepository.deleteActivityById(id);
		return "SUCCESS";
	}

	@RequestMapping(value = "updateActivity", method = RequestMethod.POST)
	public String updateActivity(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "description", required = false) String description,
			@RequestParam(name = "item_no", required = false) String item_no) {
		Activities actTemp = activityRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Activity Not Present"));
		if (name != null)
			actTemp.setName(name);
		if (description != null)
			actTemp.setDescription(description);
		if (item_no != null)
			actTemp.setItem_no(item_no);

		activityRepository.save(actTemp);
		// activityRepository.updateActivityById(name,description,id);
		return "SUCCESS";
	}

	@RequestMapping(value = "updateActivityStatus", method = RequestMethod.POST)
	public String updateActivityStatus(@RequestParam("id") Integer id, @RequestParam("active") Boolean active) {
		activityRepository.updateActivityStatusById(active, id);
		return "SUCCESS";
	}

	@PostMapping("/getFileValidation")
	public boolean fileValidation(@RequestPart("file") MultipartFile file) {
		String fileType = null;
		Tika tika = new Tika();
		try {
			fileType = tika.detect(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileType.equals("application/vnd.google-earth.kml+xml");
	}

	@PostMapping("/getActivitySector")
	public ResponseEntity<Object> getActivitySector(@RequestParam Integer ActivityId) {
		try {
			List<Activities> activityTemp = activityRepository.findAllActivityById(ActivityId);
			List<SectorEntity> sector = null;
			for (Activities activities : activityTemp) {
				sector.add(activities.getSectorEntity());
			}
			return ResponseHandler.generateResponse("Activity Sector", HttpStatus.OK, "", sector);
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Activity Sector", HttpStatus.EXPECTATION_FAILED, "",
					e.getMessage());
		}
	}

	public ResponseEntity<Object> addSector(@RequestParam Integer ActivityId, @RequestBody SectorEntity sectorEntity) {
		try {
			Activities activityTemp = activityRepository.getById(ActivityId);
			activityTemp.setSectorEntity(sectorEntity);
			return ResponseHandler.generateResponse("Activity Sector", HttpStatus.OK, "",
					activityRepository.save(activityTemp));
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Sector Entity", HttpStatus.EXPECTATION_FAILED, "", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/admin/newactivity", method = RequestMethod.POST)
	public ResponseEntity<Object> newActivityAdmin(@RequestBody Activities newactivity) {
		try {
			Activities activityTemp = activityRepository.save(newactivity);
			return ResponseHandler.generateResponse("Activity Save", HttpStatus.OK, "",
					"Error in Saving Activity");
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Activity Save", HttpStatus.INTERNAL_SERVER_ERROR, "", e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/updateActivityStatus", method = RequestMethod.POST)
	public ResponseEntity<Object> updateActivityStatusAdmin(@RequestParam("id") Integer id, @RequestParam("active") Boolean active) {
		try {
			
			activityRepository.updateActivityStatusById(active, id);
			return ResponseHandler.generateResponse("Activity status update", HttpStatus.OK, "",
					"Error in updating Activity Status");
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Activity status update", HttpStatus.INTERNAL_SERVER_ERROR, "", e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/updateActivity", method = RequestMethod.POST)
	public ResponseEntity<Object> updateActivityAdmin(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "description", required = false) String description,
			@RequestParam(name = "item_no", required = false) String item_no) {
		
		try {
			Activities actTemp = activityRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("Activity Not Present"));
			if (name != null)
				actTemp.setName(name);
			if (description != null)
				actTemp.setDescription(description);
			if (item_no != null)
				actTemp.setItem_no(item_no);
	
			activityRepository.save(actTemp);
			// activityRepository.updateActivityById(name,description,id);
			return ResponseHandler.generateResponse("Activity Update", HttpStatus.OK, "",
					"Error in updating Activity");
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Activity Update", HttpStatus.INTERNAL_SERVER_ERROR, "", e.getMessage());
		}
	}


}
