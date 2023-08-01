package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Activities;
import com.backend.model.SubActivities;
import com.backend.repository.postgres.ActivityRepository;
import com.backend.repository.postgres.SubActivityRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.NotesheetService;

import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/kya/")
public class SubActivityController {

	@Autowired
	SubActivityRepository subActivtyRepository;
	
	@Autowired
	ActivityRepository activityRepository;

	@RequestMapping(value = "getSubactivities", method = RequestMethod.GET)
	public List<SubActivities> getSubactivity(@RequestParam(value = "active", required = false) String active) {
		List<SubActivities> subActivities;
		if (active == null) {
			subActivities = subActivtyRepository.findAllSubActivities();
		} else {
			if (active.equals("true")) {
				subActivities=subActivtyRepository.findAllSubActivitiesByStatus("true","false");
			} else {
				subActivities=subActivtyRepository.findAllSubActivitiesByStatus("false","true");
			}
		}
		return subActivities;
	}

	@RequestMapping(value = "getSubactivity", method = RequestMethod.GET)
	public List<SubActivities> getSubactivityById(@RequestParam Integer id) {
		return (subActivtyRepository.findAllSubactivityById(id));
	}

	@RequestMapping(value = "newSubActivity", method = RequestMethod.POST)
	public String newSubActivity(@RequestBody SubActivities subActivity,@RequestParam Integer ActId) {
		try {
			Activities tempAct=activityRepository.findById(ActId).orElseThrow(()-> new ProjectNotFoundException("Activity Not Present"));
			subActivity.setActivities(tempAct);
			subActivtyRepository.save(subActivity);
			return "SUCCESS";
		}
		catch(Exception ex) {
			return ""+ex;
		}
	}

	@RequestMapping(value = "deleteSubActivitybyId", method = RequestMethod.GET)
	public String deleteSubActivity(@RequestParam("id") Integer id) {
		subActivtyRepository.deleteSubActivityById(id);
		return "SUCCESS";
	}

	@RequestMapping(value = "updateSubActivity", method = RequestMethod.POST)
	public String updateSubActivity(@RequestParam(name="id") Integer id, @RequestParam(name="name",required = false) String name,
									@RequestParam(name="description",required = false) String description,
									@RequestParam(name="activityId",required = false) Integer actId) {
		SubActivities temp=subActivtyRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Sub Activity Not Present"));
		if(name!=null)
			temp.setName(name);
		if(description!=null)
			temp.setDescription(description);
		if(actId!=null)
		{
			Activities act=activityRepository.findById(actId).orElseThrow(()->new ProjectNotFoundException("Activity Not Present"));
			temp.setActivities(act);
		}
		subActivtyRepository.save(temp);
		
		//subActivtyRepository.updateSubActivityById(name,description,id);
		return "SUCCESS";
	}

	@RequestMapping(value = "updateSubActivityStatus", method = RequestMethod.POST)
	public String updateSubActivityStatus(@RequestParam("id") Integer id, @RequestParam("active") Boolean active) {
		subActivtyRepository.updateSubActivityStatusById(active, id);
		return "SUCCESS";
	}
	
	@RequestMapping(value = "admin/newSubActivity", method = RequestMethod.POST)
	public ResponseEntity<Object> newSubActivityAdmin(@RequestBody SubActivities subActivity,@RequestParam Integer ActId) {
		try {
			Activities tempAct=activityRepository.findById(ActId).orElseThrow(()-> new ProjectNotFoundException("Activity Not Present"));
			subActivity.setActivities(tempAct);
			subActivtyRepository.save(subActivity);
			return ResponseHandler.generateResponse("Save Subactivity", HttpStatus.OK, "", "New Subactivity Add Success");

		} catch (Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in new Subactivity Add");
		}
	}
	
	@RequestMapping(value = "admin/updateSubActivity", method = RequestMethod.POST)
	public ResponseEntity<Object> updateSubActivityAdmin(@RequestParam(name="id") Integer id, @RequestParam(name="name",required = false) String name,
									@RequestParam(name="description",required = false) String description,
									@RequestParam(name="activityId",required = false) Integer actId) {
		
		try {
			
			SubActivities temp=subActivtyRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Sub Activity Not Present"));
			if(name!=null)
				temp.setName(name);
			if(description!=null)
				temp.setDescription(description);
			if(actId!=null)
			{
				Activities act=activityRepository.findById(actId).orElseThrow(()->new ProjectNotFoundException("Activity Not Present"));
				temp.setActivities(act);
			}
			subActivtyRepository.save(temp);
		
		//subActivtyRepository.updateSubActivityById(name,description,id);
			return ResponseHandler.generateResponse("Update Subactivity", HttpStatus.OK, "", "Update Subactivity Success");

		} catch (Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Error in Update Subactivity");
		}
	}

}
