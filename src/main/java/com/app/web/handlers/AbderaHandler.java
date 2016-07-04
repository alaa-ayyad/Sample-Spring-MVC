package com.app.web.handlers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.ParseException;
import org.apache.abdera.parser.Parser;
import org.apache.abdera.util.AbderaSource;
import org.apache.abdera.examples.*;
import org.apache.abdera.examples.xsltxpath.XsltExample;

/**
 * Helper and handlers to test readers and writers for Atom using Abdera
 * @author x151887
 *
 */
public class AbderaHandler {
	
	private static Abdera abdera;
	private static Parser parser;
	
	public AbderaHandler(){
		
	}
	
	/**
	 * 
	 */
	public static synchronized void initAbdera(){
		if (abdera == null){
			setAbdera(Abdera.getInstance());
			setParser(abdera.getParser());
		}
	}
	
	/**
	 * Gets Atom feed object
	 * @param feedURL
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static Feed getFeed(String feedURL) throws ParseException, IOException{
		AbderaHandler.initAbdera();
		Abdera abdera = new Abdera();
		Parser parser = abdera.getParser();
		
		URL url = new URL(feedURL);
		Document<Feed> doc =  parser.parse(url.openStream(), url.toString());
		
		Feed feed = doc.getRoot();
		
		return feed;
	}
	
	/**
	 * Gets parsed object from given feed source (XML file)
	 * 
	 * @param path to well structrued Atom XML file
	 * @return 
	 * @throws FileNotFoundException 
	 */
	public static Feed getFeedFromFile(String path) {
		AbderaHandler.initAbdera();
		InputStream is = null;
		try {
			is = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Document<Feed> doc = getParser().parse(is);
		//AbderaSource feedSource = new AbderaSource(doc);
		
		Feed feed = doc.getRoot();
		return feed;
	}

	
	
	public static Abdera getAbdera() {
		return abdera;
	}

	public static void setAbdera(Abdera abdera) {
		AbderaHandler.abdera = abdera;
	}

	public static Parser getParser() {
		return parser;
	}

	public static void setParser(Parser parser) {
		AbderaHandler.parser = parser;
	}

}
