package com.example.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {

	// 从 application.properties 中读取配置，如取不到默认值为HelloShanhy

	@RequestMapping(value="/admin/{user_id}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody Object helloJsp(@PathVariable(value="user_id",required=false)String id) {
		System.out.println("HelloController.helloJsp().hello=");
		
		System.err.println("你好："+id);
		return "admin才可访问的："+id;
	}

}