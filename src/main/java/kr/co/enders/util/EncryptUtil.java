/**
 * 작성자 : 김준희
 * 작성일시 : 2021.07.06
 * 설명 : 문자열을 암호화 처리
 */
package kr.co.enders.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.StringFixedSaltGenerator;

public class EncryptUtil {
	private static Logger logger = Logger.getLogger(EncryptUtil.class);
	
	/**
	 * 문자열을 SHA256으로 암호화(해싱)한다.
	 * @param str
	 * @return
	 */
	public static String getEncryptedSHA256(String str) {
		String result = "";
		if(str == null) {
			return "";
		} else {
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				digest.reset();
				digest.update(str.getBytes());
				byte[] hash = digest.digest();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < hash.length; i++) {
					sb.append(Integer.toString((hash[i]&0xff) + 0x100, 16).substring(1));
				}
				result = sb.toString();
			} catch (NoSuchAlgorithmException nsae) {
				result = str;
			}
			return result;
		}
	}
	
	/**
	 * 문자열을 Jasypt library + 고정키로 암호화한다(복호화가능한 데이터에 대한 처리)
	 * @param algorithm
	 * @param password
	 * @param str
	 * @return
	 */
	public static String getJasyptEncryptedFixString(String algorithm, String password, String str) {
		try {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setAlgorithm(algorithm);
			encryptor.setPassword(password);
			encryptor.setSaltGenerator(new StringFixedSaltGenerator(password));
			return encryptor.encrypt(str);
		} catch(Exception e) {
			logger.error("getJasyptEncryptedFixString error = " + e);
			return str;
		}
	}

	/**
	 * 문자열을 Jasypt library + 고정키로 복호화한다(복호화가능한 데이터에 대한 처리)
	 * @param algorithm
	 * @param password
	 * @param str
	 * @return
	 */
	public static String getJasyptDecryptedFixString(String algorithm, String password, String str) {
		try {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setAlgorithm(algorithm);
			encryptor.setPassword(password);
			encryptor.setSaltGenerator(new StringFixedSaltGenerator(password));
			return encryptor.decrypt(str);
		} catch(Exception e) {
			logger.error("getJasyptDecryptedFixString error = " + e + "[str]" + str + "[password]" + password);
			return str;
		}
	}
	
	/**
	 * 문자열을 Jasypt library로 암호화한다(복호화 되면 안되는 데이터에 대한 처리 : 사용자 암호)
	 * @param algorithm
	 * @param password
	 * @param str
	 * @return
	 */
	public static String getJasyptEncryptedString(String algorithm, String password, String str) {
		try {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setAlgorithm(algorithm);
			encryptor.setPassword(password);
			return encryptor.encrypt(str);
		} catch(Exception e) {
			logger.error("getJasyptEncryptedString error = " + e + "[str]" + str + "[password]" + password);
			return str;
		}
	}
	
	/**
	 * 문자열을 Jasypt library로 복호화한다(복호화 되면 안되는 데이터에 대한 처리 : 사용자 암호)
	 * @param algorithm
	 * @param password
	 * @param str
	 * @return
	 */
	public static String getJasyptDecryptedString(String algorithm, String password, String str) {
		try {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setAlgorithm(algorithm);
			encryptor.setPassword(password);
			return encryptor.decrypt(str);
		} catch(Exception e) {
			logger.error("getJasyptDecryptedString error = " + e + "[str]" + str + "[password]" + password);
			return str;
		}
	}
	
	/**
	 * 문자열을 Base64로 인코딩한다.
	 * @param str
	 * @return
	 */
	public static String getBase64EncodedString(String str) {
		try {
			Encoder encoder = Base64.getEncoder();
			return new String(encoder.encode(str.getBytes()));
		} catch(Exception e) {
			logger.error("getBase64EncodedString Error = " + e.getMessage() + "[str]" + str);
			return str;
		}
	}
	
	/**
	 * 문자열을 Base64로 디코딩한다.
	 * @param str
	 * @return
	 */
	public static String getBase64DecodedString(String str) {
		try {
			Decoder decoder = Base64.getDecoder();
			return new String(decoder.decode(str.getBytes()));
		} catch(Exception e) {
			logger.error("getBase64DecodedString Error = " + e.getMessage() + "[str]" + str);
			return str;	
		}
	}

}
