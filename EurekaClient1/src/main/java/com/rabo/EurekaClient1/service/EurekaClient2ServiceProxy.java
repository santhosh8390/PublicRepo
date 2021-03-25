package com.rabo.EurekaClient1.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "${service.eurekaclient2.serviceId}")
public interface EurekaClient2ServiceProxy {

    @GetMapping(value = "/city")
    public String getCity();

}
