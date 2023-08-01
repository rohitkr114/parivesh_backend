package com.backend.controller.TrackYourProposal;

import com.backend.exceptions.PariveshException;
import com.backend.response.ResponseHandler;
import com.backend.service.TrackYourProposal.TrackYourProposalService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;

import static com.backend.util.CommonUtils.handleEmpty;

@Slf4j
@RestController
@RequestMapping("/trackYourProposal")
public class TrackYourProposalController {

    @Autowired
    TrackYourProposalService trackYourProposalService;

    @GetMapping("/onBasesOfProposalNo")
    public ResponseEntity<Object> getDataOnBasesOfProposalNo(@RequestParam("proposalNo") String proposalNo,
                                                        HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/onBasesOfProposalNo?proposalNo="+proposalNo);
        return ResponseHandler.generateResponse(" Get Data on bases of proposalNo ", HttpStatus.OK, "",
                trackYourProposalService.getDataOnBasesOfProposalNo(proposalNo));
    }

    @GetMapping("/onBasesOfSingleWindowNo")
    public ResponseEntity<Object> getDataOnBasesOfSingleWindowNo(@RequestParam("swno") String swno,
    													@RequestParam("workgroupId") String workgroupId,
                                                        HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/onBasesOfSingleWindowNo?swno="+swno+":"+workgroupId);
        return ResponseHandler.generateResponse(" Get Data on bases of single window number", HttpStatus.OK, "",
                trackYourProposalService.getDataOnBasesOfSingleWindowNo(swno,workgroupId));
    }
    
    @GetMapping("/crz/onBasesOfSingleWindowNo")
    public ResponseEntity<Object> getCrzDataOnBasesOfSingleWindowNo(@RequestParam("swno") String swno,
    													@RequestParam("workgroupId") String workgroupId,
                                                        HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/crz/onBasesOfSingleWindowNo?swno="+swno+":"+workgroupId);
        return ResponseHandler.generateResponse(" Get CRZ Data on bases of single window number", HttpStatus.OK, "",
                trackYourProposalService.getCrzDataOnBasesOfSingleWindowNo(swno,workgroupId));
    }

    @GetMapping("/dataOfProposalNo")
    public ResponseEntity<Object> getDataOfProposalNo(@RequestParam("proposalNo") String proposalNo,
                                                             HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/dataOfProposalNo?proposalNo="+proposalNo);
        return ResponseHandler.generateResponse(" Get Data of proposalNo ", HttpStatus.OK, "",
                trackYourProposalService.getDataOfProposalNo(proposalNo));
    }

    @GetMapping("/historyDataOnProposal")
    public ResponseEntity<Object> getHistoryDataOnProposal(@RequestParam("proposalNo") String proposalNo,
                                                      HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/historyDataOnProposal?proposalNo="+proposalNo);
        return ResponseHandler.generateResponse(" Get History Data of Proposal ", HttpStatus.OK, "",
                trackYourProposalService.getHistoryDataOnProposal(proposalNo));
    }

    @GetMapping("/advanceSearchData")
    public ResponseEntity<Object> getAdvanceSearchData(@RequestParam(value = "majorClearanceType", required = false) String clearanceId,
                                                       @RequestParam(value = "state", required = false) String stateId,
                                                       @RequestParam(value = "sector", required = false) String sectorId,
                                                       @RequestParam(value = "proposalStatus", required = false) String proposalStatus,
                                                       @RequestParam(value = "proposalType", required = false) String proposalType,
                                                       @RequestParam(value = "issuingAuthority", required = false) String issuingAuthority,
                                                       @RequestParam(value = "category", required = false) String category,
                                                       @RequestParam(value = "startDate", required = false) String startDate,
                                                       @RequestParam(value = "endDate", required = false) String endDate,
                                                       @RequestParam(value = "areaMin", required = false) String areaMin,
                                                       @RequestParam(value = "areaMax", required = false) String areaMax,
                                                       @RequestParam(value = "activityId", required = false) String activityId,
                                                       @RequestParam(value = "text", required = false) String text,
                                                             HttpServletRequest request) throws PariveshException, ParseException {
        log.info("get: /trackYourProposal/advanceSearchData");
        return ResponseHandler.generateResponse(" Get Data on Advance Search", HttpStatus.OK, "",
                trackYourProposalService.getAdvanceSearchData(handleEmpty(clearanceId), handleEmpty(stateId),
                        handleEmpty(sectorId),  handleEmpty(proposalStatus),  handleEmpty(proposalType),  handleEmpty(issuingAuthority),
                        handleEmpty(category),  handleEmpty(startDate),  handleEmpty(endDate), handleEmpty(areaMin), handleEmpty(areaMax),handleEmpty(activityId), handleEmpty(text)));
    }

    @GetMapping("/getAllClearanceType")
    public ResponseEntity<Object> getAllClearanceType(HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/getAllClearanceType");
        return ResponseHandler.generateResponse("Get All Clearance Type ", HttpStatus.OK, "",
                trackYourProposalService.getAllClearanceType());
    }


    @GetMapping("/getProposalTypeOnBasesOfClearanceType")
    public ResponseEntity<Object> getProposalTypeOnBasesOfClearanceType(@RequestParam ("id") Integer id,
            HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/getProposalTypeOnBasesOfClearanceType?id="+id);
        return ResponseHandler.generateResponse("Get Proposal Type On Bases Of Clearance Type", HttpStatus.OK, "",
                trackYourProposalService.getProposalTypeOnBasesOfClearanceType(id));
    }

    @GetMapping("/getListOfAllState")
    public ResponseEntity<Object> getListOfAllState(HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/getListOfAllState");
        return ResponseHandler.generateResponse("Get All State", HttpStatus.OK, "",
                trackYourProposalService.getListOfAllState());
    }
    
    @GetMapping("/getListOfCrzState")
    public ResponseEntity<Object> getListOfCrzState(HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/getListOfCrzState");
        return ResponseHandler.generateResponse("Get Crz State", HttpStatus.OK, "",
                trackYourProposalService.getListOfCrzState());
    }

    @GetMapping("/getListOfIssuingAuthority")
    public ResponseEntity<Object> getListOfIssuingAuthority(HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/getListOfIssuingAuthority");
        return ResponseHandler.generateResponse("Get List Of Issuing Authority", HttpStatus.OK, "",
                trackYourProposalService.getListOfIssuingAuthority());
    }

    @GetMapping("/getListOfSector")
    public ResponseEntity<Object> getListOfSector(HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/getListOfSector");
        return ResponseHandler.generateResponse("Get List Of Sector", HttpStatus.OK, "",
                trackYourProposalService.getListOfSector());
    }

    @GetMapping("/getListOfCategoryForFC")
    public ResponseEntity<Object> getListOfCategoryForFC(HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/getListOfCategoryForFC");
        return ResponseHandler.generateResponse("Get List Of Category For FC", HttpStatus.OK, "",
                trackYourProposalService.getListOfCategoryForFC());
    }

    @GetMapping("/getListOfStatus")
    public ResponseEntity<Object> getListOfStatus(@RequestParam (value ="workgroupId", required = false) String workgroupId,
    											@RequestParam (value ="clearanceType", required = false) String clearanceType,				
    											HttpServletRequest request) throws PariveshException {
        log.info("get: /trackYourProposal/getListOfStatus");
        return ResponseHandler.generateResponse("Get List Of Status", HttpStatus.OK, "",
                trackYourProposalService.getListOfStatus(handleEmpty(workgroupId),handleEmpty(clearanceType)));
    }
}
