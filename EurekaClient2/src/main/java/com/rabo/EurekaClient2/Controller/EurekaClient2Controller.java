package com.rabo.EurekaClient2.Controller;

import com.rabo.EurekaClient2.Service.EurekaClient2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class EurekaClient2Controller {

    @Autowired
    private EurekaClient2Service eurekaClient2Service;

    @GetMapping(value = "/city")
    public String getCity() {
        return eurekaClient2Service.getCity();
    }

    @GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getEmailUsingHystrix() throws FileNotFoundException {
        return new ResponseEntity<>(eurekaClient2Service.getEmailUsingHystrix(), HttpStatus.OK);
    }
}
