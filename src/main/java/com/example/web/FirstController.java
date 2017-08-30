package com.example.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {

	// 从 application.properties 中读取配置，如取不到默认值为HelloShanhy

	@RequestMapping(value="/{user_id}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody Object helloJsp(@PathVariable(value="user_id",required=false)Integer id) {
		System.out.println("HelloController.helloJsp().hello=");
		
		
		return id;
		//return "first";

	}

}