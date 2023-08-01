package com.backend.service.WildLifeService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.FcProposedDiversionsDetails;
import com.backend.model.FcProposedDiversionsDetailsAudit;
import com.backend.model.ForestClearanceProposedDiversions;
import com.backend.model.ForestClearanceProposedDiversionsAudit;
import com.backend.model.WildLifeClearance.WLComponentWiseDetails;
import com.backend.model.WildLifeClearance.WLComponentWiseDetailsAudit;
import com.backend.model.WildLifeClearance.WLDivisionLandDetail;
import com.backend.model.WildLifeClearance.WLDivisionLandDetailAudit;
import com.backend.model.WildLifeClearance.WLEnclosuresAudit;
import com.backend.model.WildLifeClearance.WLOtherDetailsAudit;
import com.backend.model.WildLifeClearance.WLProposedLandAudit;
import com.backend.model.WildLifeClearance.WLUndertakingAudit;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.backend.model.WildLifeClearance.WildLifeClearanceAudit;
import com.backend.repository.postgres.WildLifeClearance.FcProposedDiversionDetailsAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.ForestClearanceProposedDiversionsAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WLComponentWiseDetailsAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WLDivisionLandDetailAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WLEnclosuresAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WLLinearProjectLandDetailsAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WLLinearProjectLandDetailsRepository;
import com.backend.repository.postgres.WildLifeClearance.WLOtherDetailsAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WLProposedLandAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WLUndertakingAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceAuditRepository;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceRepository;
import com.backend.service.UserService;


@Transactional
@Service
public class WildLifeClearanceAuditService {

	@Autowired
	private WildLifeClearanceRepository wildLifeClearanceRepository;
	
	@Autowired
	private WLProposedLandAuditRepository wlProposedLandAuditRepository;

	@Autowired
	private WLOtherDetailsAuditRepository wlOtherDetailsAuditRepository;

	@Autowired
	private WLEnclosuresAuditRepository wlEnclosuresAuditRepository;

	@Autowired
	private WLUndertakingAuditRepository wlUndertakingAuditRepository;
	
	@Autowired
	private WLDivisionLandDetailAuditRepository wlandDetailAuditRepository;
	
	@Autowired
	private WLComponentWiseDetailsAuditRepository wlComponentWiseDetailsAuditRepository;

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	WLLinearProjectLandDetailsRepository wlLinearProjectLandDetailsRepository;
	
	@Autowired
	WLLinearProjectLandDetailsAuditRepository wlLinearProjectLandDetailsAuditRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	WildLifeClearanceAuditRepository wildLifeClearanceAuditRepository;
	
	@Autowired
	FcProposedDiversionDetailsAuditRepository fcProposedDiversionDetailsRepositoryAudit;
	
	@Autowired
	ForestClearanceProposedDiversionsAuditRepository forestClearanceProposedDiversionsAuditRepository;

	public Object AddWilLifeClearanceAuditForm(Integer id){
		
		WildLifeClearance form = wildLifeClearanceRepository.findDetailByWlId(id);
		if(form==null)
		{
			throw new RuntimeException(" Not a valid WL Id");
		}
		
		WildLifeClearanceAudit wildLifeClearanceFormAudit= new WildLifeClearanceAudit();
		
		WLProposedLandAudit  wLProposedLandAudit= new WLProposedLandAudit();
		WLEnclosuresAudit  wLEnclosuresAudit= new WLEnclosuresAudit();
		List<WLComponentWiseDetailsAudit> wLComponentWiseDetailsAudit = new ArrayList<WLComponentWiseDetailsAudit>();
		List<WLDivisionLandDetailAudit> wLDivisionLandDetailAudit = new ArrayList<WLDivisionLandDetailAudit>();
		//WLLinearProjectLandDetailsAudit wLLinearProjectLandDetailsAudit = new WLLinearProjectLandDetailsAudit();
		WLOtherDetailsAudit wLOtherDetailsAudit= new WLOtherDetailsAudit();
		WLUndertakingAudit wLUndertakingAudit = new WLUndertakingAudit();
		List<ForestClearanceProposedDiversionsAudit> forestClearanceProposedDiversionsAudit = new ArrayList<ForestClearanceProposedDiversionsAudit>();
		List<FcProposedDiversionsDetailsAudit> fcProposedDiversionsDetailsAudit = new ArrayList<FcProposedDiversionsDetailsAudit>();
		
		  try {
		 
			BeanUtils.copyProperties(wildLifeClearanceFormAudit, form);
		  }
			 catch(Exception e) {System.out.println("Copy null values");}
			try {
		  BeanUtils.copyProperties(wLProposedLandAudit, form.getWlProposedLand());
			}
			catch(Exception e) {System.out.println("Copy null values");}
			try {
			BeanUtils.copyProperties(wLEnclosuresAudit, form.getWlEnclosures());
			}
			catch(Exception e) {System.out.println("Copy null values");}
			try {
			BeanUtils.copyProperties(wLComponentWiseDetailsAudit, form.getWlComponentWiseDetails());
			}
			catch(Exception e) {System.out.println("Copy null values");}
			try {
			BeanUtils.copyProperties(wLDivisionLandDetailAudit, form.getWldivisionLandDetails());
			}
			catch(Exception e) {System.out.println("Copy null values");}
			
			     for (ForestClearanceProposedDiversions source: form.getForestClearanceProposedDiversions() ) {
			        ForestClearanceProposedDiversionsAudit target= new ForestClearanceProposedDiversionsAudit();
			        try {
			        BeanUtils.copyProperties(target ,source );
			        target.setId(null);
			        }
			        catch(Exception e)   
				  	{	
					  System.out.println("Copy null values");
					}
			        
			        forestClearanceProposedDiversionsAuditRepository.save(target);
				     for (FcProposedDiversionsDetails source1: source.getFcProposedDiversionsDetails()) {
				    	 FcProposedDiversionsDetailsAudit target1= new FcProposedDiversionsDetailsAudit();
				    	 try {   
				    	 BeanUtils.copyProperties(target1 ,source1 );
					        target1.setId(null);
					        }
					        catch(Exception e)   
						  	{	
							  System.out.println("Copy null values");
							}
					        target1.setForestClearanceProposedDiversionsAudit(target);
				        
					        fcProposedDiversionsDetailsAudit.add(target1);
					        fcProposedDiversionsDetailsAudit.addAll(fcProposedDiversionsDetailsAudit);
					     }
			        
			        target.setWildLifeClearanceaudit(wildLifeClearanceFormAudit);
			        target.setWlProposedLandAudit(wLProposedLandAudit);
			        forestClearanceProposedDiversionsAudit.add(target);
			        
			        forestClearanceProposedDiversionsAudit.addAll(forestClearanceProposedDiversionsAudit);
			     }
			     
			     for (WLComponentWiseDetails source: form.getWlComponentWiseDetails() ) {
			    	 WLComponentWiseDetailsAudit target= new WLComponentWiseDetailsAudit();
				     try {   
			    	 BeanUtils.copyProperties(target ,source );
				       target.setId(null);
				         }
				     catch(Exception e)   
					  	{	
						  System.out.println("Copy null values");
						}
				        target.setWildLifeClearanceaudit(wildLifeClearanceFormAudit);
				        target.setWlProposedLandAudit(wLProposedLandAudit);
				        wLComponentWiseDetailsAudit.add(target);
				        
				        wLComponentWiseDetailsAudit.addAll(wLComponentWiseDetailsAudit);
				     }
			     
			     
			     for (WLDivisionLandDetail source: form.getWldivisionLandDetails() ) {
			    	 WLDivisionLandDetailAudit target= new WLDivisionLandDetailAudit();
			    	 try
				        {  
			    	 BeanUtils.copyProperties(target ,source );
				        target.setId(null);
				        }
				        catch(Exception e)   
					  	{	
						  System.out.println("Copy null values");
						}
				        target.setWildLifeClearanceaudit(wildLifeClearanceFormAudit);
				        target.setWlProposedLandAudit(wLProposedLandAudit);
				        wLDivisionLandDetailAudit.add(target);
				        wLDivisionLandDetailAudit.addAll(wLDivisionLandDetailAudit);
				     }
			     try {
			    	 BeanUtils.copyProperties(wLUndertakingAudit, form.getWlUndertaking());
			     	}
			     catch(Exception e) 
				  
				  	{	
					  System.out.println("Copy null values");
					}
			     try {
			    	 BeanUtils.copyProperties(wLOtherDetailsAudit, form.getWlOtherDetails());				
			    	 
			     	} 
			     catch(Exception e) 
			     {	
			  System.out.println("Copy null values");
			     }
			 
		wildLifeClearanceFormAudit.setWl_id(id);
		wildLifeClearanceFormAudit.setId(null);
		
		wLProposedLandAudit.setId(null);
		wLProposedLandAudit.setWildLifeClearanceaudit(wildLifeClearanceFormAudit);
		
		wLEnclosuresAudit.setId(null);
		wLOtherDetailsAudit.setId(null);
		wLOtherDetailsAudit.setWildLifeClearanceaudit(wildLifeClearanceFormAudit);	
		
		wLUndertakingAudit.setId(null);
		wLUndertakingAudit.setWildLifeClearanceaudit(wildLifeClearanceFormAudit);
		
		wildLifeClearanceFormAudit.setProposal_created_on(form.getCreated_on());
		
		 wildLifeClearanceAuditRepository.save(wildLifeClearanceFormAudit);
		 wlProposedLandAuditRepository.save(wLProposedLandAudit);
		 fcProposedDiversionDetailsRepositoryAudit.saveAll(fcProposedDiversionsDetailsAudit);
		 wlEnclosuresAuditRepository.save(wLEnclosuresAudit);	
		 wlComponentWiseDetailsAuditRepository.saveAll(wLComponentWiseDetailsAudit);
		 wlandDetailAuditRepository.saveAll(wLDivisionLandDetailAudit);
		 wlOtherDetailsAuditRepository.save(wLOtherDetailsAudit);
		 wlUndertakingAuditRepository.save(wLUndertakingAudit);	
		return wildLifeClearanceFormAudit.getId();
	}
	
	public WildLifeClearanceAudit getWilLifeClearanceAuditForm(Integer id) throws ParseException {
		
		WildLifeClearanceAudit form = wildLifeClearanceAuditRepository.findDetailByWlId(id);			 
		return form;
	}
}
