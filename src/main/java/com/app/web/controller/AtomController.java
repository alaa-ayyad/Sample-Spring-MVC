package com.app.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.web.handlers.AbderaHandler;

@Controller
@RequestMapping(value="/atom")
public class AtomController {
	
	String feedUrl = "http://intertwingly.net/blog/index.atom";
	String feedPath = "C:/atom.xml";
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView();
		
		Feed feed = null;
		String atomObject;
		try {
			AbderaHandler.initAbdera();
			feed = AbderaHandler.getFeedFromFile(feedPath); //AbderaHandler.getFeed(feedUrl);
		} catch (ParseException e) {
		}
		
		model.addObject("atom", feed);
		model.setViewName("atom/view");
		return model;
	}
	
}