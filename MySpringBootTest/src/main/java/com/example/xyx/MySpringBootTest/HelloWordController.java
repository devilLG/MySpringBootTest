package com.example.xyx.MySpringBootTest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/MyTest")
public class HelloWordController {

	@RequestMapping(value="hello")
	public String hello() {
		return "hellow World!";
	}
}
