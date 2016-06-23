package com.app.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import utils.AppUtils;

import com.app.web.models.MyForm;

@Controller
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, HttpServletRequest request) {

		//model.addAttribute("message", "Spring 3 MVC Hello World");
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (ipAddress.isEmpty()) {
			ipAddress = "Empty!";
		}
		model.addAttribute("ip", AppUtils.getIpAddress());
		model.addAttribute("agent", AppUtils.getAgentType());

		return "hello";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (ipAddress.isEmpty()) {
			ipAddress = "Empty!";
		}
		model.addObject("ip", ipAddress);

		return model;
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView viewForm(@ModelAttribute("form") MyForm model, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("form");
		return mav;
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView submitForm(@ModelAttribute("form") MyForm form, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		return mav;
	}
}