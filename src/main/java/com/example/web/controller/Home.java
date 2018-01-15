package com.example.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/home")
public class Home {
	@GetMapping(value="/{user_id}",produces="application/json")
	public @ResponseBody Object helloJsp(@PathVariable(value="user_id",required=false)String id,HttpServletRequest req) {
		System.out.println("HelloController.helloJsp().hello=");
		
		System.err.println("req.getRequestURI()."+req.getRequestURI());
		System.err.println("req.getContextPath()."+req.getContextPath());
		System.err.println("req.getPathInfo()."+req.getPathInfo());
		System.err.println("req.getServletPath()."+req.getServletPath());
		
		
		System.err.println("你好："+id);
		return "user,admin---home>>>才可访问的："+id;
	}

}
