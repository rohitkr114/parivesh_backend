package com.backend.client;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.util.FeignConfig;

import feign.Headers;
import io.swagger.v3.core.util.Json;

@FeignClient(value = "qci2", url = "https://eia.nabet.qci.org.in/MOEAPI/api/", configuration = FeignConfig.class)
public interface ConsultantOrganisationAPI {
	
	@RequestMapping(method = RequestMethod.POST, value = "Moe_API_org", consumes = "application/x-www-form-urlencoded")
	@Headers("Content-Type: application/x-www-form-urlencoded")
	String getQCIConsutlant(@PathVariable(name = "orgid") String OrgId);
	
	@RequestMapping(method = RequestMethod.POST, value = "updatedorg", consumes = "application/x-www-form-urlencoded")
	@Headers("Content-Type: application/x-www-form-urlencoded")
	String getQCIUpdatedConsutlant(@PathVariable(name = "Date") String date);
}
