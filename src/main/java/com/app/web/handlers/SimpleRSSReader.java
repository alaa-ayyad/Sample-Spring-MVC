package com.app.web.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.xml.sax.InputSource;

import com.app.web.models.RSSModel;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

/**
 * Provides RSS reading ability
 * @author x151887
 *
 */
public class SimpleRSSReader {

	/**
	 * Gets RSS unparsed object from give RSS source url
	 * @param feedUrl
	 * @return
	 * @throws IOException
	 */
	public static String readFeed(URL feedUrl) throws IOException {
		String result = null;
		SyndFeed feed = SimpleRSSReader.getFeed(feedUrl);
		result = feed.toString();

		return result;
	}
	
	/**
	 * Gets a list of custom RSS models after parsing the RSS xml object
	 * @param feedUrl
	 * @return
	 * @throws IOException
	 */
	public static List<RSSModel> getRSSList(URL feedUrl) throws IOException{
		List<RSSModel> result = null;
		
		SyndFeed feed = SimpleRSSReader.getFeed(feedUrl);
		result = SimpleRSSReader.getRSSModelList(feed.getEntries());
		return result;
	}

	/**
	 * Builds RSSModel list from Syndication
	 * @param originalEntries
	 * @return
	 */
	private static List<RSSModel> getRSSModelList(List<SyndEntry> originalEntries){
		
		List<RSSModel> rssList = new ArrayList<RSSModel>();
		Iterator<SyndEntry> iterator = originalEntries.iterator();
		RSSModel rssModel = null;
		
		while(iterator.hasNext()){
			SyndEntry syndEntry = iterator.next();
			rssModel = new RSSModel();
			rssModel.setTitle(syndEntry.getTitle());
			rssModel.setSummary(syndEntry.getContents().get(0).toString());
			rssModel.setUrl(syndEntry.getUri());
			rssModel.setCreatedDate(syndEntry.getPublishedDate());
			
			rssList.add(rssModel);
		}
		
		return rssList;
	}
	
	/**
	 * Fetches RSS feed from the source
	 * @param feedUrl
	 * @return
	 * @throws IOException
	 */
	private static SyndFeed getFeed(URL feedUrl) throws IOException{
		
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = null;
		InputStream is = null;

		try {
			URLConnection openConnection = new URL(feedUrl.toString()).openConnection();
			is = new URL(feedUrl.toString()).openConnection().getInputStream();
			if ("gzip".equals(openConnection.getContentEncoding())) {
				is = new GZIPInputStream(is);
			}
			InputSource source = new InputSource(is);
			feed = input.build(source);

			// SyndFeed feed = input.build(new XmlReader(feedUrl));

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
		}
		
		return feed;
	}

}
