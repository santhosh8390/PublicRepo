package com.rabo.EurekaClient1.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EurekaClient1ServiceImpl implements EurekaClient1Service {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.eurekaclient2.serviceId}")
    private String eurekaClient2Id;

    @Autowired
    private EurekaClient eurekaClient;

    @Override
    public String getEmail() {
        return "testemail@gmail.com";
    }

    @Override
    public String getCity() {
        Application application = eurekaClient.getApplication(eurekaClient2Id);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort()  + "/city";
        return restTemplate.getForEntity(url, String.class).getBody();
    }
}
