package com.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.backend.model.PaymentCompletionDTO;
import com.backend.util.FeignConfig;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(value = "insertchallanapi", url = "${feign.challan.url}", configuration = FeignConfig.class)
public interface ChallanAPI {
		
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> insertChallan(@RequestBody PaymentCompletionDTO paymentCompletionDTO);


}
