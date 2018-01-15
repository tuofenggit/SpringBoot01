package com.example.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: JspController 
 * @Description: 测试SpringBoot 配置jsp 
 * @author wangchuang@goldwind.com.cn
 * @date 2017年8月28日 下午9:35:46 
 *
 */
@Controller
@RequestMapping("/")
public class JspController {
    
	/**
	 * 
	 * @Title: returnJsp 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("")
	public String returnJsp(HttpServletRequest req) {
		
		return "index";
	}
	
}
