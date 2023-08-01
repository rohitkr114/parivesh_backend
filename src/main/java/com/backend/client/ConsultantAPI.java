package com.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.util.FeignConfig;

import feign.Headers;

@FeignClient(value = "qci", url = "https://eia.nabet.qci.org.in/MOEAPI/api/", configuration = FeignConfig.class)
public interface ConsultantAPI {

	@RequestMapping(method = RequestMethod.POST, value = "Moe_API", consumes = "application/x-www-form-urlencoded")
	@Headers("Content-Type: application/x-www-form-urlencoded")
	String getQCIConsultantDetails(@PathVariable(name = "offset") Integer offset,
			@PathVariable(name = "fetch_Next") Integer fetch);

	/*@RequestMapping(method = RequestMethod.POST, value = "Moe_API", consumes = "application/x-www-form-urlencoded")
	@Headers("Content-Type: application/x-www-form-urlencoded")
	String getQCIConsutlant(@RequestParam(name = "orgid") String OrgId);*/
	
}
