/**
 * 작성자 : 김준희
 * 작성일시 : 2021.07.06
 * 설명 : 문자열 관련 처리 모음
 */
package kr.co.enders.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import kr.co.enders.util.PropertiesUtil;

public class StringUtil {
	private static Logger logger = Logger.getLogger(StringUtil.class);
	
	@Autowired
	private static PropertiesUtil properties; 
	
	/**
	 * Null 문자열인 경우 0을 반환한다. Null이 아닌 경우 문자열을 숫자로 변환한다.
	 * @param str
	 * @return
	 */
	public static int setNullToInt(String str) {
		int nReturn = 0 ;
		try {
			if(str != null && !"".equals(str.trim())) {
				nReturn = Integer.parseInt(str);
			} else {
				nReturn = 0;
			}
		} catch(NumberFormatException e) {
			logger.error("StringUtil setNullToInt Error = " + e);
			nReturn = 0;
		}
		
		return nReturn;
	}
	
	/**
	 * 전달받은 숫자(i)가 0인 경우 res 값을 반환하고. 0이 아닌 경우 해당 숫자로 변환한다.(받은 값이 없을 경우 1로 세팅하기 위함)
	 * @param str
	 * @return
	 */
	public static int setNullToInt(int i, int res) {
		if(i == 0) {
			return res;
		} else {
			return i;
		}
	}
	
	/**
	 * 대문자로 변환하여 반환한다.
	 * @param str
	 * @return
	 */
	public static String setUpperString(String str) {
		if(str == null) {
			return "";
		} else {
			return str.toUpperCase();
		}
	}
	
	/**
	 * 문자(한자리 숫자)를 두자리 문자(숫자)로 변환하여 반환한다.
	 * @param str
	 * @return
	 */
	public static String setTwoDigitsString(String str) {
		try {
			if(str == null || "".equals(str)) {
				return "00";
			} else {
				int i = Integer.parseInt(str);
				if(i < 10)
					return "0" + i;
				else
					return "" + i;
			}
		} catch(Exception e) {
			logger.error("StringUtil setTwoDigitsString Error = " + e);
			return "00";
		}
	}
	
	/**
	 * 값이 NULL인지 체크한다.
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if(str == null || "".equals(str) || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 현재 날짜에서 기간을 계산한 날짜를 지정한 양식(format)으로 반환한다.
	 * durType => D:Date, M:Month, Y:Year<br/>
	 * @param dur
	 * @param durType
	 * @param format
	 * @return
	 */
	public static String getCalcDateFromCurr(int dur, String durType, String format) {
		Date curDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		if("D".equals(durType)) {
			cal.add(Calendar.DATE, dur);
		} else if("M".equals(durType)) {
			cal.add(Calendar.MONTH, dur);
		} else if("Y".equals(durType)) {
			cal.add(Calendar.YEAR, dur);
		} else if("H".equals(durType)) {
			cal.add(Calendar.HOUR_OF_DAY, dur);
		} else if("MI".equals(durType)) {
			cal.add(Calendar.MINUTE, dur);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 현재 시간을 읽어온다.
	 * @param   int		시간 포맷 
	 *					레벨 : TM_YMDHMS(1) ==> 년월일시분초, TM_MDHMS(2) ==> 월일시분초,
	 *						 TM_DHMS(3) ==> 일시분초, TM_HMS(4) ==> 시분초,
	 *						 TM_MS(5) ==> 분초,  TM_S(6) ==> 초,
	 *						 TM_YMD(7) ==> 년월일, TM_YMDHM(8) ==> 년월일시분
	 */
	public static String getDate(int level) {
		Calendar cal = Calendar.getInstance();
		
		String Y = Integer.toString(cal.get(Calendar.YEAR));
		String M = lPad(Integer.toString(cal.get(Calendar.MONTH)+1),2,"0");
		String D = lPad(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)),2,"0");
		String H = lPad(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)),2,"0");
		String MI = lPad(Integer.toString(cal.get(Calendar.MINUTE)),2,"0");
		String S = lPad(Integer.toString(cal.get(Calendar.SECOND)),2,"0");
		String MS = lPad(Integer.toString(cal.get(Calendar.MILLISECOND)),4,"0"); 
		
		String retval = "";
		
		switch(level) {
		
			case Code.TM_Y:		//년
					retval = Y;
					break;
			case Code.TM_M:		//월
					retval = M;
					break;
			case Code.TM_D:		//일
					retval = D;
					break;
			case Code.TM_H:		//시
					retval = H;
					break;
			case Code.TM_MI:	//분
					retval = MI;
					break;
			case Code.TM_S:		//초
					retval = S;
					break;
			case Code.TM_YM:	//년월
					retval = Y+M;
					break;
			case Code.TM_YMD:	//년월일
					retval = Y+M+D;
					break;
			case Code.TM_YMDH:	//년월일시
					retval = Y+M+D+H;
					break;
			case Code.TM_YMDHM:	//년월일시분
					retval = Y+M+D+H+MI;
					break;
			case Code.TM_YMDHMS:	//년월일시분초
					retval = Y+M+D+H+MI+S;
					break;
			case Code.TM_MDHMS:		//월일시분초
					retval = M+D+H+MI+S;
					break;
			case Code.TM_DHMS:		//일시분초
					retval = D+H+MI+S;
					break;
			case Code.TM_HMS:		//시분초
					retval = H+MI+S;
					break;
			case Code.TM_MS:		//분초
					retval = MI+S;
					break;
			case Code.TM_YMDHMSM:	//년월일시분초밀리세컨
					retval = Y+M+D+H+MI+S+MS;
					break;
		}
		return retval;
	}

	/**
	 * 왼쪽 필러
	 * @param		data	해당 데이터
	 * 				size	단윈
	 *				filler	채울값
	 * @return		String	변형문자열
	 */
	public static String lPad(String data, int size, String filler) {
		int csize = data.trim().length();
		if(size == csize) return data;

		String retstr = data;
		for(int i = 0; i < (size-csize); i++) {
			retstr = "0" + retstr;
		}
		return retstr;
	}
	
	/**
	 * 특정날짜의 특정 시간 이전이나 이후의 날짜를 구한다.  
	 * @param stand_date	기준 일자
	 * @param interval		문자타입  간격(시간, +시간, -시간)
	 * @return String 날짜(space면 포맷상의 문제)
	 */
	public static String getIntervalTime(String stand_date, String interval) {
		return getIntervalTime(stand_date, parseLong(interval));
	}

	/** 
	 * 특정날짜의 특정 시간 이전이나 이후의 날짜를 구한다. 
	 * @param stand_date 	기준 일자 
	 * @param interval		숫자타입 간격(시간, +시간, -시간)
	 * @return String		날짜(space면 포맷상의 문제)
	 */
	public static String getIntervalTime(String stand_date, long interval) {
		if (stand_date == null) {
			return "";
		}

		int length = stand_date.length();
		int year, month, day, hour, minute, second;
		String retval = "";
		Calendar cal = null;
		long stand_date_long, interval_long, temp_long;

		try {
			switch (length) {
			case 8:
				year = Integer.parseInt(stand_date.substring(0, 4));
				month = Integer.parseInt(stand_date.substring(4, 6)) - 1;
				day = Integer.parseInt(stand_date.substring(6, 8));

				// 기준 날짜 설정
				cal = Calendar.getInstance();
				cal.clear();
				cal.set(year, month, day);

				// 기준 날짜의 밀리세컨 총수
				stand_date_long = (cal.getTime()).getTime();

				// 간격에 대한 밀리세컨 총수
				interval_long = interval * (60 * 1000);

				temp_long = stand_date_long + interval_long;

				cal.clear();
				cal.setTime(new Date(temp_long));

				retval = Integer.toString(cal.get(Calendar.YEAR))
						+ lPad(Integer.toString(cal.get(Calendar.MONTH) + 1), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)), 2, "0");
				break;

			case 10:
				year = Integer.parseInt(stand_date.substring(0, 4));
				month = Integer.parseInt(stand_date.substring(4, 6)) - 1;
				day = Integer.parseInt(stand_date.substring(6, 8));
				hour = Integer.parseInt(stand_date.substring(8, 10));
				minute = 0;

				// 기준 날짜 설정
				cal = Calendar.getInstance();
				cal.clear();
				cal.set(year, month, day, hour, minute);

				// 기준 날짜의 밀리세컨 총수
				stand_date_long = (cal.getTime()).getTime();

				// 간격에 대한 밀리세컨 총수
				interval_long = interval * (60 * 1000);

				temp_long = stand_date_long + interval_long;

				cal.clear();
				cal.setTime(new Date(temp_long));

				retval = Integer.toString(cal.get(Calendar.YEAR))
						+ lPad(Integer.toString(cal.get(Calendar.MONTH) + 1), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)), 2, "0");
				break;

			case 12:
				year = Integer.parseInt(stand_date.substring(0, 4));
				month = Integer.parseInt(stand_date.substring(4, 6)) - 1;
				day = Integer.parseInt(stand_date.substring(6, 8));
				hour = Integer.parseInt(stand_date.substring(8, 10));
				minute = Integer.parseInt(stand_date.substring(10, 12));

				// 기준 날짜 설정
				cal = Calendar.getInstance();
				cal.clear();
				cal.set(year, month, day, hour, minute);

				// 기준 날짜의 밀리세컨 총수
				stand_date_long = (cal.getTime()).getTime();

				// 간격에 대한 밀리세컨 총수
				interval_long = interval * (60 * 1000);

				temp_long = stand_date_long + interval_long;

				cal.clear();
				cal.setTime(new Date(temp_long));

				retval = Integer.toString(cal.get(Calendar.YEAR))
						+ lPad(Integer.toString(cal.get(Calendar.MONTH) + 1), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.MINUTE)), 2, "0");
				break;

			case 14:
				year = Integer.parseInt(stand_date.substring(0, 4));
				month = Integer.parseInt(stand_date.substring(4, 6)) - 1;
				day = Integer.parseInt(stand_date.substring(6, 8));
				hour = Integer.parseInt(stand_date.substring(8, 10));
				minute = Integer.parseInt(stand_date.substring(10, 12));
				second = Integer.parseInt(stand_date.substring(12, 14));

				// 기준 날짜 설정
				cal = Calendar.getInstance();
				cal.clear();
				cal.set(year, month, day, hour, minute, second);

				// 기준 날짜의 밀리세컨 총수
				stand_date_long = (cal.getTime()).getTime();

				// 간격에 대한 밀리세컨 총수
				interval_long = interval * (60 * 1000);

				temp_long = stand_date_long + interval_long;

				cal.clear();
				cal.setTime(new Date(temp_long));

				retval = Integer.toString(cal.get(Calendar.YEAR))
						+ lPad(Integer.toString(cal.get(Calendar.MONTH) + 1), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.MINUTE)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.SECOND)), 2, "0");
				break;

			case 18:
				year = Integer.parseInt(stand_date.substring(0, 4));
				month = Integer.parseInt(stand_date.substring(4, 6)) - 1;
				day = Integer.parseInt(stand_date.substring(6, 8));
				hour = Integer.parseInt(stand_date.substring(8, 10));
				minute = Integer.parseInt(stand_date.substring(10, 12));
				second = Integer.parseInt(stand_date.substring(12, 14));

				// 기준 날짜 설정
				cal = Calendar.getInstance();
				cal.clear();
				cal.set(year, month, day, hour, minute, second);

				// 기준 날짜의 밀리세컨 총수
				stand_date_long = (cal.getTime()).getTime();

				// 간격에 대한 밀리세컨 총수
				interval_long = interval * (60 * 1000);

				temp_long = stand_date_long + interval_long;

				cal.clear();
				cal.setTime(new Date(temp_long));

				retval = Integer.toString(cal.get(Calendar.YEAR))
						+ lPad(Integer.toString(cal.get(Calendar.MONTH) + 1), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.MINUTE)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.SECOND)), 2, "0")
						+ lPad(Integer.toString(cal.get(Calendar.MILLISECOND)), 4, "0");
				break;

			default:
				retval = "";

			}
		} catch (Exception e) {
			logger.error("StringUtil getIntervalTime Error = " + e);
			return "";
		}

		return retval;
	}

	/**
	 * 해당 데이터를 long형으로 변경한다(null이거나 space일 경우 0). 
	 * @param input	변형할 데이터
	 * @return long 리턴 데이터
	 */
	public static long parseLong(String input) {
		if (input == null || input.equals("")) {
			return 0;
		} else {
			return Long.parseLong(input);
		}
	}
	
	/**
	 * 날짜포맷으로 변환한다 (20010830 --> 2001/08/30)
	 * @param datestr	날짜데이터
	 * @return String	날짜 출력 형식 변환 데이터 
	 */
	public static String getFDate(String datestr) {
		if(datestr == null || "".equals(datestr)) {
			return "";
		} else {
			int length = datestr.length();
			String fdate = null;
			switch (length) {
			case 8:
				fdate = datestr.substring(0, 4);
				fdate += "/";
				fdate += datestr.substring(4, 6);
				fdate += "/";
				fdate += datestr.substring(6, 8);
				break;
			case 12:
				fdate = datestr.substring(0, 4);
				fdate += "/";
				fdate += datestr.substring(4, 6);
				fdate += "/";
				fdate += datestr.substring(6, 8);
				fdate += " ";
				fdate += datestr.substring(8, 10);
				fdate += ":";
				fdate += datestr.substring(10, 12);
				break;
			default:
				fdate = "";
			}
			return fdate;
		}
	}
	
	/** 
	 * 날짜포맷으로 변환한다. 
	 * @param datestr	날짜데이터
	 * @param type 		출력 포맷
						DT_FMT1(1) --> xxxx/xx/xx
						DT_FMT2(2) --> xxxx-xx-xx
						DT_KOR(3) --> xxxx년 xx월 xx일
	 * @return String 날짜 출력 형식 변환 데이터
	 * 
	 */
	public static String getFDate(String datestr, int type) {
		if(datestr == null || "".equals(datestr)) {
			return "";
		} else {
			int length = datestr.length();
			String fdate = null;
			switch (type) {
			case Code.DT_FMT1:
				switch (length) {
				case 8:
					fdate = datestr.substring(0, 4);
					fdate += "/";
					fdate += datestr.substring(4, 6);
					fdate += "/";
					fdate += datestr.substring(6, 8);
					break;
				case 12:
					fdate = datestr.substring(0, 4);
					fdate += "/";
					fdate += datestr.substring(4, 6);
					fdate += "/";
					fdate += datestr.substring(6, 8);
					fdate += " ";
					fdate += datestr.substring(8, 10);
					fdate += ":";
					fdate += datestr.substring(10, 12);
					break;
				case 14:
					fdate = datestr.substring(0, 4);
					fdate += "/";
					fdate += datestr.substring(4, 6);
					fdate += "/";
					fdate += datestr.substring(6, 8);
					fdate += " ";
					fdate += datestr.substring(8, 10);
					fdate += ":";
					fdate += datestr.substring(10, 12);
					fdate += " ";
					fdate += datestr.substring(12, 14);
					break;
				default:
					fdate = "";
				}
				break;
			case Code.DT_FMT2:
				switch (length) {
				case 8:
					fdate = datestr.substring(0, 4);
					fdate += "-";
					fdate += datestr.substring(4, 6);
					fdate += "-";
					fdate += datestr.substring(6, 8);
					break;
				case 12:
					fdate = datestr.substring(0, 4);
					fdate += "-";
					fdate += datestr.substring(4, 6);
					fdate += "-";
					fdate += datestr.substring(6, 8);
					fdate += " ";
					fdate += datestr.substring(8, 10);
					fdate += ":";
					fdate += datestr.substring(10, 12);
					break;
				case 14:
					fdate = datestr.substring(0, 4);
					fdate += "-";
					fdate += datestr.substring(4, 6);
					fdate += "-";
					fdate += datestr.substring(6, 8);
					fdate += " ";
					fdate += datestr.substring(8, 10);
					fdate += ":";
					fdate += datestr.substring(10, 12);
					fdate += ":";
					fdate += datestr.substring(12, 14);
					break;
				default:
					fdate = "";
				}
				break;
			case Code.DT_KOR:
				switch (length) {
				case 8:
					fdate = datestr.substring(0, 4);
					fdate += "년 ";
					fdate += datestr.substring(4, 6);
					fdate += "월 ";
					fdate += datestr.substring(6, 8);
					fdate += "일";
					break;
				case 12:
					fdate = datestr.substring(0, 4);
					fdate += "년 ";
					fdate += datestr.substring(4, 6);
					fdate += "월 ";
					fdate += datestr.substring(6, 8);
					fdate += "일 ";
					fdate += datestr.substring(8, 10);
					fdate += "시 ";
					fdate += datestr.substring(10, 12);
					fdate += "분";
					break;
				case 14:
					fdate = datestr.substring(0, 4);
					fdate += "년 ";
					fdate += datestr.substring(4, 6);
					fdate += "월 ";
					fdate += datestr.substring(6, 8);
					fdate += "일 ";
					fdate += datestr.substring(8, 10);
					fdate += "시 ";
					fdate += datestr.substring(10, 12);
					fdate += "분 ";
					fdate += datestr.substring(12, 14);
					fdate += "초";
					break;
				default:
					fdate = "";
				}
				break;
			default:
				fdate = "";
			}
			return fdate;
		}
	}
	
	/**
	 * 해당 문자열을 변형한다. 
	 * @param input		변형할 문자열 전체 dest 변형할 문자열
	 * @return retstr 	리턴 데이터
	 */
	public static String repStr(String input, String target, String dest) {
		String s_Data = "";
		String s_Tmp = input;
		int i = s_Tmp.indexOf(target);

		while (i != -1) {
			s_Data = s_Data + s_Tmp.substring(0, i) + dest;
			s_Tmp = s_Tmp.substring(i + target.length());
			i = s_Tmp.indexOf(target);
		}
		s_Data = s_Data + s_Tmp;
		return s_Data;
	}
	
	/**
	 * 요일 정보를 반환한다 
	 * @param input		변형할 문자열 전체 dest 변형할 문자열
	 * @return retstr	리턴 데이터
	 */
	public static String getDayOfWeek(String datestr) {
	
		if (datestr == null || datestr.equals("")) {
			return "";
		}
		
		datestr = deleteChar(datestr);
		
		int yyyy = 0, MM = 1, dd = 1, day_of_week; // default
		
		String days[] = { "일", "월", "화", "수", "목", "금", "토" };
		
		try {
			yyyy = Integer.parseInt(datestr.substring(0, 4));
			MM = Integer.parseInt(datestr.substring(4, 6));
			dd = Integer.parseInt(datestr.substring(6, 8));
		} catch (Exception ex) {
			// do nothing
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, MM - 1, dd);
		day_of_week = cal.get(Calendar.DAY_OF_WEEK); // 1(일),2(월),3(화),4(수),5(목),6(금),7(토)
		
		return days[day_of_week - 1];
	}
	/**
	 * Null 문자열인 경우 ""을 반환한다. Null이 아닌 경우 문자열을 변환한다.
	 * @param input 확인할 문자열
	 * @return 
	 */
	public static String setNullToString(String input) {
		if(input != null && !"".equals(input) && !"".equals(input.trim()) ) {
			return input;
		} else {
			return "";
		} 
	}
	
	/**
	 * Null 문자열인 경우 defaultVal을 반환한다. Null이 아닌 경우 문자열을 변환한다.
	 * @param input 확인할 문자열
	 * @return
	 */
	public static String setNullToString(String input, String defaultVal) {
		if(input != null && !"".equals(input) && !"".equals(input.trim()) ) {
			return input;
		} else {
			return defaultVal;
		} 
	}
	
	/**
	 * 문자열이 날짜 형식으로 변경 가능한 것인지 확인 
	 * @param input 확인할 문자열 
	 * @return
	 */
	public static boolean isValidDateString(String input) {
		boolean ret = false;
		if(input != null && !"".equals(input) && !"".equals(input.trim()) ) {
			try {
				if(input.trim().length() != 14 ) {
					ret = false;
				} else {
					SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyyMMddHHmmss"); //검증할 날짜 포맷 설정
					dateFormatParser.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
					dateFormatParser.parse(input.trim()); //대상 값 포맷에 적용되는지 확인
					ret = true;
				}
			} catch (Exception e) {
				logger.error("StringUtil isValidDateString Error = " + e);
				ret = false;
			}
		} 
		return ret;
	}

	/**
	 * 입력된 문자열에서 숫자만을 리턴한다.
	 * @param input		숫자만 반환할 데이터
	 * @return retstr	리턴 데이터
	 */    
	public static String deleteChar(String input) {
		String strNumber = "0123456789";
		String retstr = "";
		
		if (input.length() > 0) {
			for (int i = 0; i < input.length(); i++) {
				if (strNumber.indexOf(input.charAt(i)) >= 0) {
					retstr = retstr + input.charAt(i);
				}
			}
		}
		return retstr;
	}
	
	/**
	 * 입력된 문자열중 설정한 특수문자를 지운다
	 * @param input		특수문자를 제거할 문자
	 * @return retstr 	리턴 데이터
	 */
	public static String removeSpecialChar(String input, String specChar) {
		String retstr = "";
		
		if (input == null) {
			retstr = "";
		} else {
			input = input.trim();
			if("".equals(input)) {
				retstr = "";
			} else {
				char lastChar = input.charAt(input.length() - 1);
				String lastStr = Character.toString(lastChar);
				if (specChar.equals(lastStr)) {
					input = input.substring(0, input.length() - 1);
				}
				retstr = input;
			}
		}
		return retstr;
	}
	
	/**
	 * 입력된 문자열중 설정한 정규식표현과 일치하는 내용을 지운다
	 * @param input 특수문자를 제거한 문자
	 * @return retstr 리턴 데이터
	 */
	public static String repalcePatternChar(String input) {
		
		String retstr = "";
		
		if (input == null) {
			retstr = "";
		} else {
			input = input.trim();
			if("".equals(input)) {
				retstr = "";
			} else {
				Pattern pattern = Pattern.compile("[$](.*?)[$]"); 
				Matcher matcher = pattern.matcher(input);
				retstr = matcher.replaceAll("null");
			}
		}
		return retstr;
	}
	
	/**
	 * 입력된 문자열이 유효한 이메일인지 확인한다
	 * @param input 유효한 이메일인지 확인 할 문자열
	 */
	public static boolean isValidEmail(String input) {
		boolean valid = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		if (m.matches()) {
			valid = true;
		}
		return valid;
	}
	
	/**
	 * 타입별 랜덤숫자를 생성한다
	 * @param makeType	랜덤 문자 생성 타입(숫자/문자+숫자/문자+숫자+특수)
	 * @return retstr	생성된 숫자 
	 */
	public static String makeRandomString(int makeType) {
		String retStr = "";
		int randomLength = 0;
		int randomCharLength = 0;
		int randomNumLength = 0;
		
		char[]charTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z','1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

		char[]charPwdTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
				'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*',
				'(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		
		char[]charOnlyTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		char[]numOnlyTable =   { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		
		Random random = new Random(System.currentTimeMillis());
		
		if(makeType == Code.RAND_TYPE_A) { //숫자
			randomLength = 6;
			
			int range = (int)Math.pow(10,randomLength);
			int trim = (int)Math.pow(10, randomLength-1);
			int result = random.nextInt(range)+trim;

			if(result>range){
				result = result - trim;
			}
			
			retStr =  String.valueOf(result);
		} else if (makeType == Code.RAND_TYPE_B) { //문자+숫자
			randomLength = 8;
			
			int tableLength  = charTable.length;
			StringBuffer buf = new StringBuffer();
			
			for (int i = 0; i < randomLength ; i ++) {
				buf.append(charTable[random.nextInt(tableLength)]);
			}
			
			retStr = buf.toString();
		} else if (makeType == Code.RAND_TYPE_D) { //문자2+숫자6
			randomCharLength = 2;
			randomNumLength = 6;
			
			int tableLength  = charOnlyTable.length;
			StringBuffer buf = new StringBuffer();
			
			for (int i = 0; i < randomCharLength ; i ++) {
				buf.append(charOnlyTable[random.nextInt(tableLength)]);
			}
			
			tableLength  = numOnlyTable.length;
			for (int j= 0; j < randomNumLength ; j ++) {
				buf.append(numOnlyTable[random.nextInt(tableLength)]);
			}
			retStr = buf.toString();
		} else { //문자+숫자+특수문자 
			randomLength = 8;
			
			int tableLength  = charPwdTable.length;
			StringBuffer buf = new StringBuffer();
			
			for (int i =0 ; i < randomLength ; i++) {
				buf.append(charPwdTable[random.nextInt(tableLength)]);
			}
			
			retStr = buf.toString();
		}
		return  retStr;
	}
	
	/**
	 * 두 날짜의 차이를 계산한다 
	 * @param srcDate 	기준이 되는 날짜 
	 * 		  tagDate	계산 대상 날짜
	 * @return 일자 차이 
	 */
	public static int getCalcDateDiff(String srcDate, String tagDate) {
		if("".equals(tagDate)) {
			tagDate = getDate(Code.TM_YMDHMS);
		}
		
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		long days = 0 ; 
		long secs = 0 ; 
		try {
			Date d1 = format.parse( tagDate );
			Date d2 = format.parse( srcDate );
			secs = (d1.getTime() - d2.getTime()) / 1000; // 초
			days = secs / (24*60*60); // 일자수
		} catch (ParseException e) {
			days = -99999;
		} 
		
		int dateDiff = Long.valueOf(days).intValue();
		
		return dateDiff;
	}
	
	/**
	 * 암호 유효성 확인
	 * @param inputPw	유효성 확인할 암호
	 * @return 유효검사결과 
	 */
	public static int validPwd(String input) {
		
		if (input == null || input.equals("") || input.trim().equals("")) {
			return Code.PWD_NONE;
		}
		
		try {
			WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
			properties = (PropertiesUtil) context.getBean("properties");
			
			String pwdValidYn = "N";
			
			if(!StringUtil.isNull(properties.getProperty("PW_VALID_YN"))) {
				pwdValidYn = properties.getProperty("PW_VALID_YN");
			}
			
			if ("Y".equals(pwdValidYn)) {
				int minLen = 0;
				int maxLen = 0; 
				String combType = "0000";
				String contEqual = "N";
				String contChar = "N";
				String contKey = "N";
					
				if(!isNull(properties.getProperty("PWD_OVER"))) {
					maxLen = getStringToInt(properties.getProperty("PWD_OVER"));
				}
				
				if(!isNull(properties.getProperty("PWD_LESS"))) {
					minLen = getStringToInt(properties.getProperty("PWD_LESS"));
				}
				
				if(!isNull(properties.getProperty("PW_COMB_TYPE"))) {
					combType = properties.getProperty("PW_COMB_TYPE");
				}
				
				if(!isNull(properties.getProperty("PW_CONT_EQUAL"))) {
					contEqual = properties.getProperty("PW_CONT_EQUAL");
				}
				
				if(!isNull(properties.getProperty("PW_CONT_CHAR"))) {
					contChar = properties.getProperty("PW_CONT_CHAR");
				}
				
				if(!isNull(properties.getProperty("PW_CONT_KEY"))) {
					contKey = properties.getProperty("PW_CONT_KEY");
				}
				
				if(maxLen < 1 ) maxLen = 20; //없으면 기본값설정
				if(minLen < 1 ) minLen = 1;
				
				if (input.length() >  maxLen ) return Code.PWD_OVER;
				if (input.length() <  minLen ) return Code.PWD_LESS;
				
				Pattern pAlphabetLow = null;
				Pattern pAlphabetUp = null;
				Pattern pNumber = null;
				Pattern pSpecialChar = null;
				Pattern pThreeChar = null;
				Matcher match;
				
				String checkNumber="0";
				String checkLower="0";
				String checkUpper="0";
				String checkSpecial="0";
				
				checkNumber = combType.substring(0,0);
				checkLower = combType.substring(1,1);
				checkUpper = combType.substring(2,2);
				checkSpecial = combType.substring(3,3);
				
				pAlphabetLow = Pattern.compile("[a-z]");		 	// 영소문자
				pAlphabetUp = Pattern.compile("[A-Z]");				// 영대문자
				pNumber = Pattern.compile("[0-9]");					// 숫자
				pSpecialChar = Pattern.compile("\\p{Punct}");		// 특수문자 -_=+\\|()*&^%$#@!~`?></;,.:'
				pThreeChar = Pattern.compile("(\\p{Alnum})\\1{2,}");// 3자리 이상 같은 문자 또는 숫자

				// 영소문자가 포함되어 있는가?
				if (!"0".equals(checkLower)) {
					match = pAlphabetLow.matcher(input);
					if(!match.find()) return Code.PWD_COMB;
				}
				
				// 영대문자가 포함되어 있는가?
				if (!"0".equals(checkUpper)) {
					match = pAlphabetUp.matcher(input);
					if(!match.find()) return Code.PWD_COMB;
				}
				
				// 숫자가 포함되어 있는가?
				if (!"0".equals(checkNumber)) {
					match = pNumber.matcher(input);
					if(!match.find()) return Code.PWD_COMB;
				}
				
				// 특수문자가 포함되어 있는가?
				if (!"0".equals(checkSpecial)) {
					match = pSpecialChar.matcher(input);
					if(!match.find()) return Code.PWD_COMB;
				}
				
				// 3자리 이상 같은 문자 또는 숫자가 포함되어 있는가?
				if (!"N".equals(contEqual)) {
					match = pThreeChar.matcher(input);
					if(match.find()) return Code.PWD_CONT_EQUAL;
				}
				
				// 연속된 3자리 이상의 문자나 숫자가 포함되어 있는가?
				if (!"N".equals(contChar)) {
					String listThreeChar = "abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz|012|123|234|345|456|567|678|789|890";
					String[] arrThreeChar = listThreeChar.split("\\|");
					for (int i=0; i<arrThreeChar.length; i++) {
						if(input.toLowerCase().matches(".*" + arrThreeChar[i] + ".*")) {
							return Code.PWD_CONT_CHAR;
						}
					}
				}
				
				// 연속된 3자리 이상의 키보드 문자가 포함되어 있는가?
				if (!"N".equals(contKey)) {
					String listKeyboardThreeChar = "qwe|wer|ert|rty|tyu|yui|uio|iop|asd|sdf|dfg|fgh|ghj|hjk|jkl|zxc|xcv|cvb|vbn|bnm";
					String[] arrKeyboardThreeChar = listKeyboardThreeChar.split("\\|");
					for (int j=0; j<arrKeyboardThreeChar.length; j++) {
						if(input.toLowerCase().matches(".*" + arrKeyboardThreeChar[j] + ".*")) {
							return Code.PWD_CONT_KEY;
						}
					}
				}
			} 
			return Code.PWD_SUCCESS;
		} catch (Exception ex) {
			return Code.PWD_EXCE;
		}
	}
	
	/**
	 * 사용자 접속 IP 조회
	 * @param inputPw	유효성 확인할 암호
	 * @return 접속자 IP 정보  
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		logger.info("> X-FORWARDED-FOR : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info("> Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info(">  WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			logger.info("> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			logger.info("> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
			logger.info("> getRemoteAddr : "+ip);
		}
		logger.info("> Result : IP Address : "+ip);
		
		return ip;
	}
	
	/**
	 * 문자열을 숫자로 넘겨준다 
	 * @param input	srcDate : 기준이 되는 날짜 tagDate : 계산 대상 날짜	 
	 * @return 일자 차이 
	 */
	public static int getStringToInt(String input) {
		int result = 0; 
		if ( input != null && input.matches("[0-9.]+")) {
			try{
				result = Integer.valueOf(input);
			}
			catch (NumberFormatException ex){
				result = 0;
			}
		}
		
		return result;
	}
	
	/**
	 * 입력된 문자열을 핸드폰번호 형식으로 변환한다.
	 * @param input	핸드폰번호 반환할 데이터
	 * @return retstr 리턴 데이터
	 */
	public static String getPhone(String input) {
		String retStr = "";
		
		if (input == null || "".equals(input.trim())) {
			retStr = "";
		} else {
			if (input.length() == 8) {
				retStr = input.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
			} else if (input.length() == 12) {
				retStr = input.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
			}
			retStr = input.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
		}
		return retStr;
	}
	
	/**
	 * "_"뒤의 단어 찾아서 삭제 하고 제공된 문자 합친다
	 * @param input 	 원본문자열
	 * 		  appendStr  추가할 문자열
	 * @return retstr 	 리턴 데이터
	 */
	public static String findAndMakeString(String input, String appendStr) {
		String retStr = ""; 
		if(input.contains(appendStr)) {
			int pos = input.indexOf("_");
			String res = input.substring(0, pos);
			retStr = res + appendStr;
		}else {
			retStr = input + appendStr;
		}
		return retStr;
	}
	
	/**
	 * 문자열의 마지막 "," 제거
	 * @param input 	원본문자열
	 * @return retstr 	리턴 데이터
	 */
	public static String removeComma(String input) {
		String reslast = input; 
		if (input.endsWith(",")) {
			reslast = input.substring(0, input.length() - 1);  
		}
		return reslast;
	}
}
