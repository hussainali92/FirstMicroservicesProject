package com.example.in28mintues.microservices.limits_services.controller;

import com.example.in28mintues.microservices.limits_services.Configuration.Configuration;
import com.example.in28mintues.microservices.limits_services.bean.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
@Autowired
    private Configuration configuration;
    @GetMapping("/limits/config")
    public Limits limitsConfig(){
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
    @GetMapping("/limits")
    public Limits limits(){
        return new Limits(1000,2000);
    }
}
