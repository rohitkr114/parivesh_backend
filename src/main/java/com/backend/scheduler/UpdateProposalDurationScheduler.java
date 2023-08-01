package com.backend.scheduler;

import com.backend.repository.postgres.ProponentApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class UpdateProposalDurationScheduler {
    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleUpdate(){
        log.info("inside update proposal duration scheduler");
        LocalDateTime date= LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        Boolean flag= proponentApplicationRepository.updateProposalDuration();
        if(flag==true){
            log.info("proposal duration updated at "+ date.format(format));
        }
    }
}
