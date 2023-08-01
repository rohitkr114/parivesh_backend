package com.backend.service.StandardTORCertificate;

import com.backend.dto.StandadCertificateDto.ConditionMasterDto;
import com.backend.dto.StandadCertificateDto.GeneralConditionDetails;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcConditionMasterRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ConditionMasterService {

    @Autowired
    private EcConditionMasterRepo conditionMasterRepo;
    
    @Autowired 
    private ProponentApplicationRepository proponentApplicationRepository;

    public Set<String> getHeading (String sectorName, String typeOfProposal, int activityId) {
        return conditionMasterRepo.getHeading(sectorName, typeOfProposal, activityId);
    }

    public Set<String> getConditionHeadingByHeading (String heading, int activityId) {
        return conditionMasterRepo.getConditionHeadingByHeading(heading, activityId);
    }

    public Set<String> getConditionsByConditionHeading (String conditionHeading, int activityId) {
        return conditionMasterRepo.getConditionsByConditionHeading(conditionHeading, activityId);
    }

    public Set<String> getAllHeading () {
        return conditionMasterRepo.getAllHeading();
    }


    public List<GeneralConditionDetails> getAllConditionsByActivityId(int activityId, String proposalType) {
        List<GeneralConditionDetails> listOfGeneralConditions = new ArrayList<>();

        Set<String> headingByCondition = conditionMasterRepo.getAllHeadingByActivityId(activityId, proposalType);

        headingByCondition.stream()
                .forEach(heading -> {
                    GeneralConditionDetails generalConditionDetails = new GeneralConditionDetails();
                    generalConditionDetails.setHeading(heading);
                    Set<String> conditionsHeading = conditionMasterRepo.getConditionHeadingByHeading(heading, activityId);
                    List<GeneralConditionDetails.ConditionHeading> listOfConditionHeadings = new ArrayList<>();
                    conditionsHeading.stream()
                            .forEach(item -> {
                                GeneralConditionDetails.ConditionHeading innerConditionHeading = new GeneralConditionDetails.ConditionHeading();
                                List<String> addConditions = new ArrayList<>();
                                innerConditionHeading.setHeadingCondition(item);
                                if (item != null) {
                                    Set<String> conditions = conditionMasterRepo.getConditionsByConditionHeading(item, activityId);
                                    conditions.stream()
                                            .filter(StringUtils::hasText)
                                            .forEach(cond -> {
                                                addConditions.add(cond);
                                            });
                                    innerConditionHeading.setConditions(addConditions);
                                }
                                listOfConditionHeadings.add(innerConditionHeading);
                            });
                    generalConditionDetails.setConditionHeading(listOfConditionHeadings);
                    listOfGeneralConditions.add(generalConditionDetails);
                });
        return listOfGeneralConditions;
    }
    
	/**
	 * Method is used to getAllFCCondition by using status
	 * @param status
	 * @return {@link List<ConditionMasterEntity>}
	 */
	public List<ConditionMasterDto> getAllFCCondition(Integer applicationId,String conditionType) {
		/*Converting status to upper case*/
		ProponentApplications application= proponentApplicationRepository.findById(applicationId)
				.orElseThrow(()->new ProjectNotFoundException("proponent applications Not Found for id:"+applicationId));
		
		List<ConditionMasterDto> conditionList =new ArrayList<>();
		
		String[] str= application.getProposal_no().split("/");
		String projectCategory=str[2];

		if(conditionType!=null) {
		if(conditionType.equalsIgnoreCase("STANDARD")) {
			System.out.println("getting standard conditions");
			conditionList = conditionMasterRepo.getStandardConditions(projectCategory,"STANDARD");	
		}else{
			System.out.println("getting general conditions");
			conditionList=conditionMasterRepo.getGeneralConditions("GENERAL");
		}
		} else {
			conditionList=conditionMasterRepo.getGeneralConditions("GENERAL");
		}
		
		return conditionList;
	}
}
