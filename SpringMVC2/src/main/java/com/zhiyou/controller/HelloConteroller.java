package com.zhiyou.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloConteroller {

	@RequestMapping("/hello")
	public ModelAndView method() {
		ModelAndView model = new ModelAndView();
		model.addObject("msg", "今天真冷啊 0-0");
		model.setViewName("show");
		return model;
	}

	// @ResponseBody
	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		req.setAttribute("msg", "哈哈哈");
		return "login"; // 逻辑视图
	}
}
