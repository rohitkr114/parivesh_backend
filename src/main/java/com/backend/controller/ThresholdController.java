package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.repository.postgres.ActivityRepository;
import com.backend.repository.postgres.GenCodeMasterRepository;
import com.backend.repository.postgres.SubActivityRepository;
import com.backend.repository.postgres.ThresholdRepository;
import com.backend.response.ResponseHandler;
import com.backend.model.Activities;
import com.backend.model.Employee;
import com.backend.model.GenCodeMaster;
import com.backend.model.SubActivities;
import com.backend.model.ThresholdParameter;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kya/")
public class ThresholdController {

	@Autowired
	ThresholdRepository thresholdRepository;

	@Autowired
	GenCodeMasterRepository genCodeMasterRepository;

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	SubActivityRepository subActivityRepository;

	@RequestMapping(value = "/getThreshold", method = RequestMethod.GET)
	public List<ThresholdParameter> getThreshold(@RequestParam(value = "active", required = false) String active) {
		List<ThresholdParameter> thresholdParameters;
		if (active == null) {
			thresholdParameters = thresholdRepository.getThresholdByStatus("true", "false");
		} else {
			if (active.equals("true")) {
				thresholdParameters = thresholdRepository.getThresholdByStatus("true", "false");
			} else {
				thresholdParameters = thresholdRepository.getThresholdByStatus("false", "true");
			}
		}
		return thresholdParameters;
	}

	@GetMapping("/getActivityThreshold")
	public List<ThresholdParameter> getActivityByThreshold(@RequestParam Integer id) {
		List<ThresholdParameter> ThreshParam = thresholdRepository.findThresholdbyActId(id);

		List<GenCodeMaster> genCodeMasters = genCodeMasterRepository.findAllByParentGrp("THRESHOLD_UNIT");
		ThreshParam.forEach(parameter -> {
			genCodeMasters.forEach(code -> {
				if (code.getVal().equals(String.valueOf(parameter.getUnit()))) {
					parameter.setThreshold_unit(code.getName());
				}
			});
		});

		return (ThreshParam);
	}

	@GetMapping("/getSubActivityThreshold")
	public List<ThresholdParameter> getSubActivityByThreshold(@RequestParam Integer id) {
		List<ThresholdParameter> ThreshParam = thresholdRepository.findThresholdbySubActId(id);
		List<GenCodeMaster> genCodeMasters = genCodeMasterRepository.findAllByParentGrp("THRESHOLD_UNIT");
		ThreshParam.forEach(parameter -> {
			genCodeMasters.forEach(code -> {
				if (code.getVal().equals(String.valueOf(parameter.getUnit()))) {
					parameter.setThreshold_unit(code.getName());
				}
			});
		});

		return (ThreshParam);
	}

	@PostMapping("/addThreshold")
	public ResponseEntity<Object> addThreshold(@RequestBody ThresholdParameter thresholdParameter,
			@RequestParam(value = "activityId", required = false) Integer activityId,
			@RequestParam(value = "subActivityId", required = false) Integer subActivityId) {
		try {
			Activities tempActivity;
			SubActivities tempSubActivity;
			/*
			 * if(activityId!=null) { tempActivity=activityRepository.getById(activityId); }
			 * else { List<Integer>
			 * activityIds=thresholdParameter.getSubActivities().getActivity_id();
			 * 
			 * } if(subActivityId!=null) {
			 * 
			 * } else {
			 * 
			 * }
			 */
			tempActivity = activityRepository.getById(activityId);
			tempSubActivity = subActivityRepository.getById(subActivityId);
			thresholdParameter.setActivities(tempActivity);
			thresholdParameter.setSubActivities(tempSubActivity);
			thresholdRepository.save(thresholdParameter);
			return ResponseHandler.generateResponse("Threshold Addition", HttpStatus.OK, "", "SUCCESS");
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Threshold Addition", HttpStatus.PRECONDITION_FAILED, "Exception",
					ex.getMessage());
		}
	}

	@PostMapping("/deleteThreshold")
	public ResponseEntity<Object> deleteThreshold(@RequestParam Integer id) {
		try {
			thresholdRepository.deleteThreshold(id);
			return ResponseHandler.generateResponse("Threshold Deletion", HttpStatus.OK, "", "SUCCESS");
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Threshold Deletion", HttpStatus.PRECONDITION_FAILED, "Exception",
					ex.getMessage());
		}
	}

	@PostMapping("/updatethresholdActivity")
	public ResponseEntity<Object> updateActivity(@RequestParam Integer ThresholdId, @RequestParam Integer ActivityId) {
		try {
			Activities activityTemp = activityRepository.getById(ActivityId);
			ThresholdParameter thresholdTemp = thresholdRepository.getById(ThresholdId);
			thresholdTemp.setActivities(activityTemp);
			thresholdRepository.save(thresholdTemp);
			return ResponseHandler.generateResponse("Update Activity Successful", HttpStatus.OK, "", "SUCCESS");
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Update Activity Successful", HttpStatus.PRECONDITION_FAILED,
					"Exception", ex.getMessage());
		}
	}

	@PostMapping("/updatethresholdSubActivity")
	public ResponseEntity<Object> updateSubActivity(@RequestParam Integer ThresholdId,
			@RequestParam Integer SubActivityId) {
		try {
			SubActivities SubactivityTemp = subActivityRepository.getById(SubActivityId);
			ThresholdParameter thresholdTemp = thresholdRepository.getById(ThresholdId);
			thresholdTemp.setSubActivities(SubactivityTemp);
			thresholdRepository.save(thresholdTemp);
			return ResponseHandler.generateResponse("Update Sub Activity Successful", HttpStatus.OK, "", "SUCCESS");
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Update Sub Activity Successful", HttpStatus.PRECONDITION_FAILED,
					"Exception", ex.getMessage());
		}
	}

}
