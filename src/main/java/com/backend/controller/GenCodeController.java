

package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.backend.model.GenCodeMaster;
import com.backend.repository.postgres.GenCodeMasterRepository;
import com.backend.response.ResponseHandler;



@RestController()
@RequestMapping("/kyaGenCode")
public class GenCodeController {
	
	@Autowired
	GenCodeMasterRepository genCodeMasterRepository;
	
	@PostMapping("/addGenCode")
	public String setGencode(@RequestBody GenCodeMaster gen) {
		GenCodeMaster gm=null;
		String Message="";
		
		
		try {
		genCodeMasterRepository.save(gen);
		return "SUCCESS";
		}
		catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		/*
		try {
			 gm=genCodeMasterRepository.findabbrIsActiveFalse(gen.getAbbr());
			 
			
			if(gm==null) {
				GenCodeMaster newGenCode=new GenCodeMaster();
				 newGenCode.setAbbr(gen.getAbbr());
				 newGenCode.setCreated_by(gen.getCreated_by());
				 newGenCode.setCreated_on(gen.getCreated_on());
				 newGenCode.setIs_active(true);
				 newGenCode.setIs_deleted(false);
				 newGenCode.setParent_grp(gen.getParent_grp());
				 newGenCode.setVal(gen.getVal());
				 newGenCode.setName(gen.getName());
				 newGenCode.setUuid(gen.getUuid());
				 newGenCode.setUpdated_by(gen.getUpdated_by());
				 newGenCode.setUpdated_on(gen.getUpdated_on());
			 genCodeMasterRepository.save(newGenCode);
				Message="GenCode with following abbr "+gen.getAbbr()+" is created. ";
				ResponseEntity<String> response=new ResponseEntity<String>(Message,HttpStatus.OK);
				return response;
			}else {
				throw new Exception("Duplicate abbreviation");
			}
			
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}*/
		
	}
	
	@PutMapping("/updateGenCode")
	public ResponseEntity<String> updateGencode(@RequestBody GenCodeMaster gen ) {
		GenCodeMaster gdm=null;
		String Message="";
		try {gdm=genCodeMasterRepository.findabbr(gen.getAbbr());

			if(gdm==null) {
				throw new Exception("Following Abbreviation is not present .");
			}
			 			 
				GenCodeMaster newGenCode=new GenCodeMaster();
				newGenCode.setId(gen.getId());
				 newGenCode.setAbbr(gen.getAbbr());
				 newGenCode.setCreated_by(gen.getCreated_by());
				 newGenCode.setCreated_on(gen.getCreated_on());
				 newGenCode.setIs_active(gen.isIs_active());
				 newGenCode.setIs_deleted(gen.isIs_deleted());
				 newGenCode.setParent_grp(gen.getParent_grp());
				 newGenCode.setVal(gen.getVal());
				 newGenCode.setName(gen.getName());
				 newGenCode.setUuid(gen.getUuid());
				 newGenCode.setUpdated_by(gen.getUpdated_by());
				 newGenCode.setUpdated_on(gen.getUpdated_on());
			   genCodeMasterRepository.save(newGenCode);
				Message="GenCode with following Name "+gen.getName()+" is Updated. ";
				ResponseEntity<String> response=new ResponseEntity<String>(Message,HttpStatus.OK);
				return response;
			
			
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	@PostMapping("/deleteGenCode")
	public ResponseEntity<String> deleteGencode(@RequestBody GenCodeMaster gen ) {
		GenCodeMaster gdm=null;
		String Message="";
		try {gdm=genCodeMasterRepository.findabbr(gen.getAbbr());

			if(gdm==null) {
				throw new Exception("Following Abbreviation is not present .");
			}
			 			 
			GenCodeMaster newGenCode=new GenCodeMaster();
			newGenCode.setId(gen.getId());
			 newGenCode.setAbbr(gen.getAbbr());
			 newGenCode.setCreated_by(gen.getCreated_by());
			 newGenCode.setCreated_on(gen.getCreated_on());
			 newGenCode.setIs_active(gen.isIs_active());
			 newGenCode.setIs_deleted(gen.isIs_deleted());
			 newGenCode.setParent_grp(gen.getParent_grp());
			 newGenCode.setVal(gen.getVal());
			 newGenCode.setName(gen.getName());
			 newGenCode.setUuid(gen.getUuid());
			 newGenCode.setUpdated_by(gen.getUpdated_by());
			 newGenCode.setUpdated_on(gen.getUpdated_on());
		   genCodeMasterRepository.save(newGenCode);
				Message="GenCode with following Name "+gen.getName()+" is Deleted. ";
				ResponseEntity<String> response=new ResponseEntity<String>(Message,HttpStatus.OK);
				return response;
			
			
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@GetMapping("/getGenCode")
	public ResponseEntity <List<GenCodeMaster>> getGencode(@RequestParam(value = "active", required = false) Boolean active) throws Exception{
		if((active==null)||(active==true)) {
			List<GenCodeMaster> genCodeMasters=null;
			String Message="Unable to Generate List";
			try {
				genCodeMasters	 = genCodeMasterRepository.findAll();

				ResponseEntity<List<GenCodeMaster>> response=new ResponseEntity<List<GenCodeMaster>>(genCodeMasters,HttpStatus.OK);
				return response;
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
		else {
			List<GenCodeMaster> genCodeMasters=null;
			String Message="Unable to Generate List";
			try {
				genCodeMasters	 = genCodeMasterRepository.findAllInactive();

				ResponseEntity<List<GenCodeMaster>> response=new ResponseEntity<List<GenCodeMaster>>(genCodeMasters,HttpStatus.OK);
				return response;
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}
	}
	
	@PostMapping("/getGenderGenCode")
	public List<GenCodeMaster> getRegistrationTypes(@RequestParam String type,@RequestParam(value = "active", required = false) Boolean active) {
		
		System.out.print("active===="+active);
		
		if(active==null || active==true) {
			return genCodeMasterRepository.findActiveAllByParentGrp(type);
		}
		else {
			return genCodeMasterRepository.findAllByParentGrp(type);
		}
	}
	
	
	 @PostMapping("getDistinctRegistrationType")
	 public ResponseEntity<Object> getDistinctRegistrationTypes(){ 
		 try {
			 //return genCodeMasterRepository.findDistinctRegistration();
			 //System.out.println(genCodeMasterRepository.findDistinctRegistration());
			 return ResponseHandler.generateResponse("Distinct Registration types",HttpStatus.OK,"",genCodeMasterRepository.findDistinctRegistration());
		 }
		 catch(Exception ex) {
			 //System.out.println(ex.getMessage());
			 return ResponseHandler.generateResponse("Distinct Registration types",HttpStatus.EXPECTATION_FAILED,ex.getMessage(),"");
		 }
	  }
	 
}

