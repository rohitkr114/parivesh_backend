package com.backend.service;

import com.backend.dto.UserStateDetailsDto;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.State;
import com.backend.model.SubDistrict;
import com.backend.model.Village;
import com.backend.repository.postgres.*;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private VillageRepo villageRepo;

    @Autowired
    VillageRepository villageRepository;

    @Autowired
    SubDistrictRepository subDistrictRepository;

    public ResponseEntity<Object> getAllStates(String active) {
        try {
            if (active == null)
                return ResponseHandler.generateResponse("Get all States", HttpStatus.OK, "",
                        stateRepository.findAllStates());
            else if (active.equals("true")) {
                return ResponseHandler.generateResponse("Get all States", HttpStatus.OK, "",
                        stateRepository.findAllStatesByStatus("true", "false"));
            } else
                return ResponseHandler.generateResponse("Get all States", HttpStatus.OK, "",
                        stateRepository.findAllStatesByStatus("false", "true"));
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getAllStates WITH STRING----> active  ---- RETRIEVING ALL STATES - FAILURE");
            return ResponseHandler.generateResponse("All States", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> getState(Integer Id) {
        try {
            if (Id > 0) {
                log.info("INFO ------------ getState WITH STATE_ID----> " + Id + "  ---- RETRIEVING ALL STATES - SUCCESS");
                return ResponseHandler.generateResponse("State by ID", HttpStatus.OK, "",
                        stateRepository.getStateById(Id));
            } else {
                log.info("ERROR ------------ PRECONDITION_FAILED: getState WITH STATE_ID----> " + Id + "  ---- RETRIEVING ALL STATES - FAILURE");
                return ResponseHandler.generateResponse("State by ID", HttpStatus.PRECONDITION_FAILED,
                        "State ID must be positive", "");
            }
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getState WITH STATE_ID----> " + Id + "  ---- RETRIEVING ALL STATES - FAILURE");
            return ResponseHandler.generateResponse("State not returned", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> addStates(List<State> states) {
        try {
            /*
             * ForestClearance temp = forestClearanceRepository.findById(fc_id).get();
             * List<ForestClearanceMaps> maps = forestClearanceMaps.stream().map(value -> {
             * value.setForestClearance(temp); if(value.getScanCopy() != null) {
             * value.getScanCopy().setProposal_no(temp.getProposal_no()); } return value;
             * }).collect(Collectors.toList()); List<ForestClearanceMaps> forestMapDetails =
             * forestClearanceMapsRepository.saveAll(maps);
             */
            log.info("INFO ------------ addStates WITH LIST STATES----> " + states + "  ---- ADDING ALL STATES - SUCCESS");
            return ResponseHandler.generateResponse("Add States", HttpStatus.OK, "", stateRepository.saveAll(states));
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: addStates WITH LIST STATES----> " + states + "  ---- ADDING ALL STATES - FAILURE");
            return ResponseHandler.generateResponse("State not returned", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> deleteState(Integer state) {
        try {
            State temp = stateRepository.findById(state)
                    .orElseThrow(() -> new ProjectNotFoundException("State Not Found"));
            temp.setIs_active(false);
            temp.setIs_deleted(true);
            log.info("INFO ------------ deleteState WITH state_id----> " + state + "  ---- DELETING STATE - SUCCESS");
            return ResponseHandler.generateResponse("State Deletion", HttpStatus.OK, "", stateRepository.save(temp));
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: deleteState WITH state_id----> " + state + "  ---- DELETING STATE - FAILURE");
            return ResponseHandler.generateResponse("State Deletion", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> getVillages(Integer count) {
        try {

            List<Village> villages1 = villageRepo.findAllVillagesLimitedTo(count);
            log.info("INFO ------------ getVillages WITH LIMIT----> " + count + "  ---- RETRIEVING VILLAGES - SUCCESS");
            return ResponseHandler.generateResponse("Get Villages", HttpStatus.OK, "", villages1);
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getVillages WITH LIMIT----> " + count + "  ---- RETRIEVING VILLAGES - FAILURE");
            return ResponseHandler.generateResponse("Get Villages", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> getVillagesBySubDistrictCode(Integer sub_district_code) {
        try {

            List<Village> villages = villageRepository.getVillagesbySubDistrictCode(sub_district_code);
            log.info("INFO ------------ getVillagesBySubDistrictCode WITH sub_district_code----> " + sub_district_code + "  ---- RETRIEVING VILLAGES BY SUB DISTRICT CODE - SUCCESS");
            return ResponseHandler.generateResponse("Get Villages", HttpStatus.OK, "", villages);
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getVillagesBySubDistrictCode WITH sub_district_code----> " + sub_district_code + "  ---- RETRIEVING VILLAGES BY SUB DISTRICT CODE - FAILURE");
            return ResponseHandler.generateResponse("Get Villages", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> getVillagesBySubDistrictCodes(List<Integer> sub_district_codes) {
        try {
            List<List<Village>> villages = new ArrayList<>();

            villages = sub_district_codes.stream().map(value -> {
                List<Village> vilList;
                vilList = villageRepository.getVillagesbySubDistrictCode(value);
                return vilList;
            }).collect(Collectors.toList());


            return ResponseHandler.generateResponse("Get Villages", HttpStatus.OK, "", villages);
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getVillagesBySubDistrictCode WITH sub_district_code----> ---- RETRIEVING VILLAGES BY SUB DISTRICT CODE - FAILURE");
            return ResponseHandler.generateResponse("Get Villages", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> getVillagesByDistrictCode(Integer district_code) {
        try {

            List<Village> villages = villageRepository.getVillagesbyDistrictCode(district_code);
            log.info("INFO ------------ getVillagesByDistrictCode WITH sub_district_code----> " + district_code + "  ---- RETRIEVING VILLAGES BY DISTRICT CODE - SUCCESS");
            return ResponseHandler.generateResponse("Get Villages", HttpStatus.OK, "", villages);
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getVillagesByDistrictCode WITH district_code----> " + district_code + "  ---- RETRIEVING VILLAGES BY DISTRICT CODE - FAILURE");
            return ResponseHandler.generateResponse("Get Villages", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public Optional<State> getByStateCode(Integer code) {
        return stateRepository.getStateByCode(code);
    }

    public State getStateByCode(Integer code) {
        return stateRepository.getByStateCode(code);
    }

    public ResponseEntity<Object> getSubDistrictsByDistrictCode(Integer district_code) {
        try {

            List<SubDistrict> subDistricts = subDistrictRepository.getSubDistrictsbyDistrictCode(district_code);
            log.info("INFO ------------ getSubDistrictsByDistrictCode WITH district_code----> " + district_code + "  ---- RETRIEVING SUB DISTRICT CODE BY DISTRICT CODE - SUCCESS");
            return ResponseHandler.generateResponse("Get Sub Districts", HttpStatus.OK, "", subDistricts);

        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getSubDistrictsByDistrictCode WITH district_code----> " + district_code + "  ---- RETRIEVING SUB DISTRICT CODE BY DISTRICT CODE - FAILURE");
            return ResponseHandler.generateResponse("Get SubDistricts", HttpStatus.BAD_REQUEST, "Exception Occurred",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> getUserStateDetails(Integer entityid) {

        List<UserStateDetailsDto> details = new ArrayList<>();

        details = stateRepository.getUserStateDetails(entityid);

        return ResponseHandler.generateResponse("Getting User State Details", HttpStatus.OK, null, details);

    }
}
