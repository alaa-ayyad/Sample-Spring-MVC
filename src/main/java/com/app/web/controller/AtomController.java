package com.app.web.controller;

import java.io.IOException;

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
			//initiare Abdera object once and for all, no need to create new object evert time the api is called. It could be even moved to a better place
			AbderaHandler.initAbdera();
			feed = AbderaHandler.getFeedFromFile(feedPath); //AbderaHandler.getFeed(feedUrl);
		} catch (ParseException e) {
		}
		
		model.addObject("atom", feed);
		model.setViewName("atom/view");
		return model;
	}
	
	@RequestMapping(value = "/url", method = RequestMethod.GET)
	public ModelAndView viewUrl(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView();
		
		Feed feed = null;
		String atomObject;
		try {
			//initiare Abdera object once and for all, no need to create new object evert time the api is called. It could be even moved to a better place
			AbderaHandler.initAbdera();
			feed = AbderaHandler.getFeed(feedUrl);
		} catch (ParseException e) {
		} catch (IOException e) {
		}
		
		model.addObject("atom", feed);
		model.setViewName("atom/view");
		return model;
	}
	
	
	
}