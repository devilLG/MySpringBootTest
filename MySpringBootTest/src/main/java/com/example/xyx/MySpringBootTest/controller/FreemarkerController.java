package com.example.xyx.MySpringBootTest.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="freemarker")
public class FreemarkerController {

    @RequestMapping(value="hello")
    public String hello(Map<String, String> map, Model model) {
        model.addAttribute("name", "ChengJiawei");
        map.put("msg", "Freemarker's World");
        return "hello";
    }
}
