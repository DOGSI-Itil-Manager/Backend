package com.dogsi.itil.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/configuration")
public class ConfigurationController {
    
    @GetMapping
    public String getConfiguration(){
        return "Hello";
    }

}
