package com.backend.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.dto.sieea;
import com.backend.util.FeignConfig;


@FeignClient(value = "SIEAA", url = "http://164.100.213.214/SEIAAAPI/APITOR/SEIAASTATUS", configuration = FeignConfig.class)
public interface SIEAAStatusAPI {

	@RequestMapping(method = RequestMethod.GET ,consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<sieea>> getSieeaStatus(@RequestParam(name = "state") Integer state);
}
