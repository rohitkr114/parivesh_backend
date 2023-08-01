package com.backend.client;

import com.backend.util.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "documentClient", url = "${doc.address}", configuration = FeignConfig.class)
public interface DocumentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/downloadDocument")
    byte[] getDocumentClient(@RequestParam("docTypemappingId") Integer docTypemappingId, @RequestParam("refId") String refId, @RequestParam("refType") String refType, @RequestParam("uuid") String uuid, @RequestParam("version") String version);
}
