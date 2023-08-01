package com.backend.controller.StandardTorCertificate;

import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.ConditionMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/conditionMaster")
public class EcConditionMaster {

    @Autowired
    private ConditionMasterService conditionMasterService;

    @GetMapping("/getHeadings")
    public ResponseEntity<Object> getHeading (@RequestParam String sectorName, @RequestParam String typeOfProposal, @RequestParam int activityId) {
        return ResponseHandler.generateResponse("get Headings", HttpStatus.OK, "",
                conditionMasterService.getHeading(sectorName, typeOfProposal,activityId));
    }

    @GetMapping("/getConditionHeadings")
    public ResponseEntity<Object> getConditionHeadingByHeading (@RequestParam String heading, @RequestParam int activityId) {
        return ResponseHandler.generateResponse("get Condition Headings by heading", HttpStatus.OK, "",
                conditionMasterService.getConditionHeadingByHeading(heading, activityId));
    }

    @GetMapping("/getConditions")
    public ResponseEntity<Object> getConditionsByConditionHeading (@RequestParam String conditionHeading, @RequestParam int activityId) {
        return ResponseHandler.generateResponse("get Condition Headings by heading", HttpStatus.OK, "",
                conditionMasterService.getConditionsByConditionHeading(conditionHeading, activityId));
    }

    @GetMapping("/getAllHeadings")
    public ResponseEntity<Object> getAllHeading () {
        return ResponseHandler.generateResponse("get All heading", HttpStatus.OK, "",
                conditionMasterService.getAllHeading());
    }

    @GetMapping("/getConditionsByActivityId")
    public ResponseEntity<Object> getConditionsByActivityId (@RequestParam int activityId, @RequestParam String proposalType) {
        return ResponseHandler.generateResponse("get All heading by activity id", HttpStatus.OK, "",
                conditionMasterService.getAllConditionsByActivityId(activityId, proposalType));
    }
}
