package com.app.web.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.web.handlers.SimpleRSSReader;
import com.app.web.models.RSSModel;

@Controller
@RequestMapping(value="/rss")
public class RSSController {
	
	String feedUrlString = "http://local.telus.com:7001/webapp/rss/feed";
	
	@RequestMapping(value="/feed", method=RequestMethod.GET)
	public ModelAndView getRSSFeed(){
		
		//Create Feeds
		List<RSSModel> rssItems = new ArrayList<>();
		
		//RSS Item 1
		RSSModel rss01 = new RSSModel();
		rss01.setCreatedDate(new Date());
		rss01.setSummary("This is RSS item #1");
		rss01.setTitle("News 01");
		rss01.setUrl("www.yahoo.com");
		rssItems.add(rss01);
		
		//RSS Item 2
		RSSModel rss02 = new RSSModel();
		rss02.setCreatedDate(new Date());
		rss02.setSummary("This is RSS item #2");
		rss02.setTitle("News 02");
		rss02.setUrl("www.google.com");
		rssItems.add(rss02);
		
		//RSS Item 3
		RSSModel rss03 = new RSSModel();
		rss03.setCreatedDate(new Date());
		rss03.setSummary("This is RSS item #3");
		rss03.setTitle("News 03");
		rss03.setUrl("www.telus.com");
		rssItems.add(rss03);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("rssViewer");
		model.addObject("rssItems", rssItems);
		
		return model;
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView viewRSSFeed() throws IOException{
		
		String feedString = SimpleRSSReader.readFeed(new URL(feedUrlString));
		
		ModelAndView model = new ModelAndView();
		model.setViewName("rss/feedViewer");
		model.addObject("feed", feedString);
		
		return model;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView viewRSSFeedList() throws IOException{
		
		List<RSSModel> rssList = SimpleRSSReader.getRSSList(new URL(feedUrlString));
		
		ModelAndView model = new ModelAndView();
		model.setViewName("rss/rssList");
		model.addObject("list", rssList);
		
		return model;
	}
	
}