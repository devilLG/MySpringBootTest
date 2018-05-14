package com.example.xyx.MySpringBootTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/MyTest")
public class HelloWordController {
    
    @RequestMapping(value="helloWrold")
    public String helloWorld() {
        return "Hello World!";
    }
}
