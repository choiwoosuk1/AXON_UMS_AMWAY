/**
 * 작성자 : 김준희
 * 작성일시 : 2021.12.05
 * 설명 : 크로스스크립팅 필터 
 */
package kr.co.enders.util;

import java.io.FileInputStream;
import java.util.Properties;
 
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class CrossScriptingFilter  {
	private static Logger logger = Logger.getLogger(CrossScriptingFilter.class);
	
	public static boolean existScript(HttpServletRequest request , String editText) {
		
		boolean retVal = false;
		try {
			// 설정파일 읽기
			String realPath = request.getServletContext().getRealPath("/");
			Properties prop = new Properties();
			prop.load(new FileInputStream(realPath + "/WEB-INF/config/properties/ums.xss.properties"));
			
			String regExpYn = prop.getProperty("XSS_USE").trim();
			if ("Y".equals(regExpYn)) {
				editText= editText.toLowerCase();
				logger.debug("CrossScriptingFilter existScript text = [" + editText + "]");
				
				String regExpColum = prop.getProperty("XSS_REGEXP").trim();
				String[] regexpCols = regExpColum.split("\\;");
				
				Loop1:for(int j=0;j<regexpCols.length;j++) {
						String regex =  regexpCols[j];
						if(editText.matches(regex)) {
							retVal = true;
							logger.debug("CrossScriptingFilter existScript matches = [" + regexpCols[j] + "]");
							break Loop1;
						}
					}
				
				String filterColumn = prop.getProperty("XSS_FILTER").trim();
				String[] filterCols = filterColumn.split("\\;");
				
				Loop2:for(int j=0;j<filterCols.length;j++) {
					if(editText.contains(filterCols[j])) {
						retVal = true;
						logger.debug("CrossScriptingFilter existScript matches = [" + filterCols[j] + "]");
						break Loop2;
					}
				}
				return retVal;
			} else {
				logger.debug("CrossScriptingFilter not Use");
				return false;
			}
			
		} catch(Exception e) {
			logger.error("CrossScriptingFilter existScript error = " + e);
			return true;
		}
	}
}
