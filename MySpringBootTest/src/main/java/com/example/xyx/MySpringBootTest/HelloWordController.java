package com.example.xyx.MySpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/MyTest")
public class HelloWordController {
    
    @Autowired
    private BaseDate baseDate;

	@RequestMapping(value="hello")
	public String hello() {
	    System.out.println(baseDate.getName());
		return baseDate.getName()+"住址:"+"欢迎光临！";
	}
}
