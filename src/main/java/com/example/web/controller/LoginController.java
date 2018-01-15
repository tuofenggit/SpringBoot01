package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *登陆
 * @author wangchuang
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/v1")
	public String returnJsp() {
		
		return "login";
	}
	
}
