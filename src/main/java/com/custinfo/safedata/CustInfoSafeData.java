package com.custinfo.safedata;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import kr.co.enders.util.EncryptUtil;
import kr.co.enders.util.PropertiesUtil;
import kr.co.enders.util.StringUtil;

public class CustInfoSafeData {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private PropertiesUtil properties; 
	/**
	 * 암호화
	 * @param data
	 * @param type (RNNO/NOT_RNNO)
	 * @return
	 * @throws Exception
	 */
	public synchronized String getEncrypt(String data, String type) throws Exception {
		
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		properties = (PropertiesUtil) context.getBean("properties");
		
		return EncryptUtil.getJasyptEncryptedFixString("PBEWithMD5AndDES", type, data); 
	}
	
	/**
	 * 복호화
	 * @param data
	 * @param type (RNNO/NOT_RNNO)
	 * @return
	 * @throws Exception
	 */
	public synchronized String getDecrypt(String data, String type) throws Exception {
		return EncryptUtil.getJasyptDecryptedFixString("PBEWithMD5AndDES", type, data);
	}
}
