package com.backend.service.certificate;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.certificate.ClearanceMatrix;
import com.backend.repository.postgres.certificate.ClearanceMatrixRepository;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@Transactional
public class ClearanceMatrixService {

    @Autowired
    private ClearanceMatrixRepository clearanceMatrixRepository;

    public ClearanceMatrix saveClearanceMatrix(ClearanceMatrix clearanceMatrix) {

        return clearanceMatrixRepository.save(clearanceMatrix);
    }
    
    public ResponseEntity<Object> getClearanceMatrixByActivityId(Integer activityId){
    	try {
    		List<ClearanceMatrix> response= new ArrayList<ClearanceMatrix>();
    		
    		if(activityId!=null) {
    		response= clearanceMatrixRepository.getClearanceMatrixByActivityId(activityId);
    		}else {
    			response= clearanceMatrixRepository.getAllClearanceMatrix();
    		}
    		
    		return ResponseHandler.generateResponse("getting clearance matrix for activity id:"+activityId, HttpStatus.OK, "", response);
    	} catch(Exception e) {
    		log.error("Encountered Exception", e);
    		throw new PariveshException("Error in getting clearance Matrix-", e);
    	}
    }

    public ResponseEntity<Object> deleteClearanceMatrix(Integer id) {
        try {

            Integer upadate = clearanceMatrixRepository.deleteClearanceMatrix(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Clearance Matrix id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Clearance Matrix data", HttpStatus.OK, null, "Record deleted Successfully");
    }

    public List<ClearanceMatrix> getClearanceMatrix(Integer application_id, String category, String sub_category, Integer sub_activity_id, String activity_id, String sector) {

        List<ClearanceMatrix> list = new ArrayList<ClearanceMatrix>();
        try {

            String applicationId = null;
            String subActivityId = null;
            List<String> activityId = null;
            List<String> cat = null;

            if (activity_id != null) {
                activityId = Stream.of(activity_id.split(",", -1)).collect(Collectors.toList());
            }

            if (category != null) {
                cat = Stream.of(category.split(",", -1)).collect(Collectors.toList());
            }
            if (application_id != null) {
                applicationId = String.valueOf(application_id);
            }
            if (sub_activity_id != null) {
                subActivityId = String.valueOf(sub_activity_id);
            }

            if (applicationId == null && cat == null && sub_category == null && subActivityId == null && activity_id == null && sector == null) {
                list = clearanceMatrixRepository.findLimitedRecords();
            } else {
                list = clearanceMatrixRepository.getClearanceMatrix(applicationId, cat, sub_category, subActivityId, activityId, sector);
            }
            return list;

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting Clearance Matrix " + e);
        }

    }

    public List<ClearanceMatrix> getClearanceMatrixWithLimit(Integer page, Integer size, Integer application_id, String category, String sub_category, Integer sub_activity_id, String activity_id, String sector) {

        List<ClearanceMatrix> list = new ArrayList<ClearanceMatrix>();
        try {

            String applicationId = null;
            String subActivityId = null;
            List<String> activityId = null;
            List<String> cat = null;

            if (activity_id != null) {
                activityId = Stream.of(activity_id.split(",", -1)).collect(Collectors.toList());
            }
            if (category != null) {
                cat = Stream.of(category.split(",", -1)).collect(Collectors.toList());
            }
            if (application_id != null) {
                applicationId = String.valueOf(application_id);
            }
            if (sub_activity_id != null) {
                subActivityId = String.valueOf(sub_activity_id);
            }

            if (applicationId == null && cat == null && sub_category == null && subActivityId == null && activity_id == null && sector == null) {
                list = clearanceMatrixRepository.findLimitedRecords();
            } else {
                list = clearanceMatrixRepository.getClearanceMatrixWithLimit(applicationId, cat, sub_category, subActivityId, activityId, sector, page, size);
            }
            return list;

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting Clearance Matrix " + e);
        }

    }

    public List<String> getCategory() {

        return clearanceMatrixRepository.getCategory();
    }


    public List<ClearanceMatrix> getClearanceMatrixV2(String activityIdList, String category, String typeProposal) {
		List<ClearanceMatrix> list = new ArrayList<ClearanceMatrix>();
		List<ClearanceMatrix> commonSectorCondition = new ArrayList<ClearanceMatrix>();
		try {
			List<Integer> activity_id = new ArrayList<>();
			String[] arr = activityIdList.split(",", -1);
			for (String str : arr) {
				activity_id.add(Integer.valueOf(str));
			}
			list = clearanceMatrixRepository.getClearanceMatrixV2(activity_id, category, typeProposal);
			if (!list.isEmpty()) {
				commonSectorCondition = clearanceMatrixRepository.getCommonClearanceMatrix(list.get(0).getSector(),
						category, typeProposal);
				log.info("activityCondition:{} ,commonSectorCondition:{} found for sector:{} ", list.size(),
						commonSectorCondition.size(), list.get(0).getSector());
				commonSectorCondition.addAll(list);
			}

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting Clearance Matrix " + e);
		}
		return commonSectorCondition;
	}

    public List<ClearanceMatrix> getClearanceMatrixV3(String activityIdList, String subActivityIdList, String category, String typeProposal) {
        List<ClearanceMatrix> list = new ArrayList<ClearanceMatrix>();
        try {
            List<Integer> activity_id = new ArrayList<>();
            String[] arr = activityIdList.split(",", -1);
            for (String str : arr) {
                activity_id.add(Integer.valueOf(str));
            }
            List<Integer> subActivities = Arrays.stream(subActivityIdList.split(",")).map(ele -> Integer.valueOf(ele)).collect(Collectors.toList());
            log.info("subactivities id: {}", subActivities);
            list = clearanceMatrixRepository.getClearanceMatrixV3(activity_id,subActivities, category, typeProposal);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting Clearance Matrix " + e);
        }
        return list;
    }
}
