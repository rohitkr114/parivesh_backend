package com.backend.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.model.DraftFormData;
import com.backend.repository.postgres.DraftFormDataRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DraftFormDataService {

    @Autowired
    private DraftFormDataRepository draftFormDataRepository;

    @Transactional
    public ResponseEntity<Object> saveDraftFormData(DraftFormData formData) {
        log.info("DraftFormDataService::saveDraftFormData for saving form data in draft");
        try {
            DraftFormData returnFormData = new DraftFormData();
            DraftFormData listOldFormData =
                    draftFormDataRepository.getDraftFormDataCurrentVersion(formData.getStepId(),
                            formData.getApplicationdId(),formData.getFormId());
            //log.info(listOldFormData.getData());

            if (listOldFormData != null) {
                listOldFormData.setIs_active(false);
                listOldFormData.setIs_deleted(true);
                Integer oldVersion = listOldFormData.getVersion();
                Integer newVersion = oldVersion + 1;
                formData.setVersion(newVersion);

                draftFormDataRepository.save(listOldFormData);
                returnFormData = draftFormDataRepository.save(formData);

            } else {
                formData.setVersion(1);
                returnFormData = draftFormDataRepository.save(formData);
            }
            log.info("DraftFormDataService::saveDraftFormData data :  saved in DB successfully!!");
            return ResponseHandler.generateResponse("Saving draft form data", HttpStatus.OK, null, returnFormData);
        } catch (Exception e) {
            log.error("DraftFormDataService::saveDraftFormData : Error in data saved in DB!!!" + e.toString() + " "
                    + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving draft form data", e);
        }
    }

    @Transactional
    public ResponseEntity<Object> getDraftFormDataAllVersion(Integer formId) {
        try {
            log.info("DraftFormDataService::getDraftFormDataAllVersion : Fetching data form DB");
            return ResponseHandler.generateResponse("getting DraftFormDetails details", HttpStatus.OK, null,
                    draftFormDataRepository.getDraftFormDataAllVersion(formId));
        } catch (Exception e) {
            log.error("DraftFormDataService::getDraftFormDataAllVersion: Error in fetching data from DB" + e.toString()
                    + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting draft form data", e);
        }
    }

    @Transactional
    public ResponseEntity<Object> getDraftFormDataCurrentVersion(Integer formId, Integer applicationId,Integer stepId) {
        try {
            log.info("DraftFormDataService::getDraftFormDataCurrentVersion:Fetching data from DB");
            DraftFormData draftFormData = null;
            if (applicationId == null) {
                draftFormData = draftFormDataRepository.getDraftFormDataCurrentVersionWithoutAppId(formId,stepId);
            } else {
                draftFormData = draftFormDataRepository.getDraftFormDataCurrentVersion(stepId, applicationId, formId);
            }
            return ResponseHandler.generateResponse("getting DraftFormDetails details", HttpStatus.OK, null,
                    draftFormData);
        } catch (Exception e) {
            log.error("DraftFormDataService::getDraftFormDataCurrentVersion: Error in fetching data from DB"
                    + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting draft form data", e);
        }
    }

    public ResponseEntity<Object> getFormDataForCompare(Integer formId, Integer applicationId) {
        try {
            log.info("DraftFormDataService::getFormDataForCompare:Fetching data from DB");
            List<DraftFormData> previousVersion = new ArrayList<DraftFormData>();
            List<DraftFormData> currentVersion = new ArrayList<DraftFormData>();
            HashMap<String, List<DraftFormData>> result = new HashMap<String, List<DraftFormData>>();

            currentVersion = draftFormDataRepository.getDraftFormDataCurrent(formId, applicationId);

            Integer currentV=currentVersion.get(0).getVersion();
            if(currentV!=1)
            {
                currentV=currentV-1;
            }
            previousVersion = draftFormDataRepository.getDraftFormDataPrevious(formId,applicationId,currentV);

            result.put("currentVersion",currentVersion);
            result.put("previousVersion",previousVersion);
            return ResponseHandler.generateResponse("Current and Previous version for compare", HttpStatus.OK, null,
                    result);
        } catch (Exception e) {
            log.error("DraftFormDataService::getFormDataForCompare: Error in fetching data from DB"
                    + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting draft form data", e);
        }
    }

    public ResponseEntity<Object> getFormDataForCompareWithStep(Integer formId, Integer applicationId,Integer stepId) {
        try {
            log.info("DraftFormDataService::getFormDataForCompare:Fetching data from DB");
            List<DraftFormData> previousVersion = new ArrayList<DraftFormData>();
            List<DraftFormData> currentVersion = new ArrayList<DraftFormData>();
            HashMap<String, List<DraftFormData>> result = new HashMap<String, List<DraftFormData>>();

            currentVersion = draftFormDataRepository.getFormDataForCompareWithStep(formId, applicationId,stepId);

            Integer currentV=currentVersion.get(0).getVersion();
            if(currentV!=1)
            {
                currentV=currentV-1;
            }
            previousVersion = draftFormDataRepository.getDraftFormDataPreviousWithStep(formId,applicationId,currentV,
                    stepId);

            result.put("currentVersion",currentVersion);
            result.put("previousVersion",previousVersion);
            return ResponseHandler.generateResponse("Current and Previous version for compare", HttpStatus.OK, null,
                    result);
        } catch (Exception e) {
            log.error("DraftFormDataService::getFormDataForCompare: Error in fetching data from DB"
                    + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting draft form data", e);
        }
    }

}
