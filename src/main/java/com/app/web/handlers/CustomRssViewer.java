package com.app.web.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.app.web.models.RSSModel;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;

public class CustomRssViewer extends AbstractRssFeedView {
	
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request){
		feed.setTitle("Test feed item");
		feed.setDescription("Research for RSS Feed tile");
		feed.setLink("http://www.google.com");
		
		super.buildFeedMetadata(model, feed, request);
	}
	
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<RSSModel> ressModels =  (List<RSSModel>) model.get("rssItems");
		List<Item> items = new ArrayList<Item>(ressModels.size());
		
		for (RSSModel rssModel : ressModels){
			Item item = new Item();
			Content content  = new Content();
			content.setValue(rssModel.getSummary());
			
			item.setContent(content);
			item.setTitle(rssModel.getTitle());
			item.setLink(rssModel.getUrl());
			item.setPubDate(rssModel.getCreatedDate());
			
			items.add(item);
		}
		return items;
	}
	
}
