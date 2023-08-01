package com.backend.service;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.FcRecommendedArea;
import com.backend.repository.postgres.FcRecommendedAreaRepository;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FcRecommendedAreaService {

    @Autowired
    private FcRecommendedAreaRepository fcRecommendedAreaRepository;

    public ResponseEntity<Object> saveArea(FcRecommendedArea fcRecommendedArea){
        try {
            if (fcRecommendedArea.getId()==0 || fcRecommendedArea.getId()==null){
                FcRecommendedArea temp= fcRecommendedAreaRepository.getRecommendedArea(fcRecommendedArea.getFc_id()).orElse(null);
                if(temp!=null){
                    fcRecommendedArea.setId(temp.getId());
                }
            }
            return ResponseHandler.generateResponse("saving recommended area", HttpStatus.OK,"",
                    fcRecommendedAreaRepository.save(fcRecommendedArea));
        }catch(Exception e){
            log.error("encounterd exception",e);
            throw new PariveshException("error in saving recommended area",e);
        }
    }

    public ResponseEntity<Object> getRecommendedArea(Integer fc_id){
        try {
            FcRecommendedArea response= fcRecommendedAreaRepository.getRecommendedArea(fc_id).orElse(null);

            return ResponseHandler.generateResponse("getting recommended area",HttpStatus.OK,"",response);
        } catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in getting recommended area",e);
        }
    }
}
