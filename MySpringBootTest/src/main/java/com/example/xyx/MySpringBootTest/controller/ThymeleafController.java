package com.example.xyx.MySpringBootTest.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="thymeleaf")
public class ThymeleafController {

    @RequestMapping(value="hello")
    public String hello(Map<String, String> map) {
        map.put("msg", "Hello Thymeleaf");
        return "hello";
    }
}
