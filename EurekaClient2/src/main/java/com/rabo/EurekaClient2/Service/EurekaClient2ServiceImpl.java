package com.rabo.EurekaClient2.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class EurekaClient2ServiceImpl implements EurekaClient2Service {

    @Override
    public String getCity() {
        return "Chennai";
    }

    @HystrixCommand(fallbackMethod = "fallback_email")
    @Override
    public String getEmailUsingHystrix() throws FileNotFoundException {
        FileInputStream fis = null;
        fis = new FileInputStream("D:/test.txt");
        return "testemail@gmail.com";
    }

    private String fallback_email() {
        return "Request fails. It takes long time to response";
    }

}
