package com.rabo.EurekaClient1.controller;

import com.rabo.EurekaClient1.service.EurekaClient1Service;
import com.rabo.EurekaClient1.service.EurekaClient2ServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClient1Controller {

    @Autowired
    private EurekaClient1Service eurekaClient1Service;

    @Autowired
    private EurekaClient2ServiceProxy eurekaClient2ServiceProxy;

    @GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getEmail() {
        return new ResponseEntity<>(eurekaClient1Service.getEmail(), HttpStatus.OK);
    }

    @GetMapping(value = "/cityByRestTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCityByRestTemplate() {
        return new ResponseEntity<>(eurekaClient1Service.getCity(), HttpStatus.OK);
    }

    @GetMapping(value = "/cityByFeignClient",  produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCityByFeignClient() {
        return eurekaClient2ServiceProxy.getCity();
    }
}
