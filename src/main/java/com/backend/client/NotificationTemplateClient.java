package com.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.model.NotificationTemplate;
import com.backend.util.FeignConfig;

@FeignClient(value = "notificationTemplate", url = "${feign.engine.template.url}", configuration = FeignConfig.class)
public interface NotificationTemplateClient {

	@RequestMapping(method = RequestMethod.POST, value = "/get")
	NotificationTemplate getNotificationTemplate(@RequestParam("Id") Integer Id);

}
