package utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AppUtils {
	
	/**
	 * returns the Ip Address of the current logged in user using HTTP request
	 * @return Ip Address
	 */
	public static String getIpAddress(){
		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String ipAddress =  httpRequest.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {
			ipAddress = httpRequest.getRemoteAddr();
		}
		
		return ipAddress;
	}
	
	/**
	 * Returns Browser Agent type of the logged in user using HTTP request
	 * @return
	 */
	public static String getAgentType(){
		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String userAgnet = httpRequest.getHeader("user-agent");
		
		return userAgnet;
	}
	
}
