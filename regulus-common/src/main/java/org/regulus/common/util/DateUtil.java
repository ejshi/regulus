package org.regulus.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期处理工具类
 * @author ThinkPad
 *
 */
public class DateUtil {

	public static String YEAR = "year";
	public static String MONTH = "month";
	public static String DAY = "day";
	public static String HOUR = "hour";
	public static String MINUTE = "minute";
	public static String SECOND = "second";

	public static int INTERVAL_JUST = 0;
	public static int INTERVAL_10M = 1;
	public static int INTERVAL_30M = 2;
	public static int INTERVAL_1H = 3;
	public static int INTERVAL_2H = 4;
	public static int INTERVAL_3H = 5;
	public static int INTERVAL_5H = 6;
	public static int INTERVAL_1D = 7;

	public static final SimpleDateFormat DEFAULT_YEAR_FORMAT = new SimpleDateFormat("yyyy");
	public static final SimpleDateFormat DEFAULT_YEAR_MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new  SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DEFAULT_YEAR_MONTH_FORMAT_CN = new SimpleDateFormat("yyyy年MM月");
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT_CN = new  SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DEFAULT_MINUTE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat DEFAULT_DATETIME_SLASH_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final SimpleDateFormat DEFAULT_DEFAULT_MINUTE_PATTERN_STR = new SimpleDateFormat("yyyyMMddHHmm");
	public static final SimpleDateFormat yyyyMMdd_HHmmss_PATTERN_STR = new SimpleDateFormat("yyyyMMdd HHmmss");
	
	public static final String DEFAULTPATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
	public static String COMMON_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String yyyyMMdd_HHmmss_PATTERN = "yyyyMMdd HHmmss";
	
	public static boolean isValidDate(String str,DateFormat dateFormat) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			dateFormat.setLenient(false);
			dateFormat.parse(str);
			} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        } 
        return convertSuccess;
	}
	
	public static Map<String, Object> getIntervalOfDates(long second) {
		Map<String, Object> map = new HashMap<String, Object>();
		long minute = second / 60;
		long hour = minute / 60;
		long day = hour / 24;
		if (day > 0) {
			map.put("type", "day");
			map.put("interval", day);
		} else if (hour > 0) {
			map.put("type", "hour");
			map.put("interval", hour);
		} else {
			map.put("type", "minute");
			map.put("interval", minute);
		}
		return map;
	}

	public static boolean compareDateIsLarger(Date beginDate, Date endDate) {
		if (beginDate == null) {
			return true;
		}
		if (endDate == null) {
			return false;
		}
		long endTime = endDate.getTime();
		long beginTime = beginDate.getTime();
		return endTime > beginTime;
	}

	public static long getIntervalOfTwoDates(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null)
			return 0;
		long endTime = endDate.getTime();
		long beginTime = beginDate.getTime();
		long intervalMilliSecond = endTime > beginTime ? endTime - beginTime : 0;
		long intervalSecond = intervalMilliSecond / 1000;
		return intervalSecond;
	}
	/**
	 * 几小时前，几分钟前，几秒前
	 * @param date
	 * @return
	 */
	public static String convert(Date date) {
		String ret = "";
		long intervalMilliSecond = new Date().getTime() - date.getTime();
		if (intervalMilliSecond  >= 86400000) {
			ret = deSerialize(date, "MM月dd日");
		}else if (intervalMilliSecond  >= 3600000) {
			ret = intervalMilliSecond / 3600000 + "小时前";
		}else if (intervalMilliSecond  >= 60000) {
			ret = intervalMilliSecond / 60000 + "分钟前";
		}else {
			ret = intervalMilliSecond / 1000 + "秒前";
		} 
		return ret;
	}
	
	/**
	 * 将秒计数方式转化为HH:mm:ss格式
	 * @param seconds 总秒数
	 */
	public static String convert(Long seconds) {
		if(seconds==null){
			seconds = 0L;
		}
		int hour = (int)(seconds/3600);
		int minute = (int)((seconds-hour*3600)/60);
		int second = (int)((seconds-hour*3600-minute*60));
		
		String strHour = hour < 10 ? "0" + hour : "" + hour;
		String strMinute = minute < 10 ? "0" + minute : "" + minute;
		String strSecond = second < 10 ? "0" + second : "" + second;
		
		return strHour + ":" + strMinute + ":" + strSecond;
	}

	public static Date getDateBeforeHours(Date comparedDate, int cursor, String unit) {
		if (unit.equalsIgnoreCase("hour")) {
			long interval = new Long(cursor);
			long millisecond = comparedDate.getTime() - interval * 3600 * 1000;
			return new Date(millisecond);
		} else if (unit.equalsIgnoreCase("day")) {
			long interval = new Long(cursor);
			long millisecond = comparedDate.getTime() - interval * 3600 * 1000 * 24;
			return new Date(millisecond);
		} else {
			long millisecond = comparedDate.getTime() - cursor * 1000;
			return new Date(millisecond);
		}
	}

	public static Date getBefore(Date comparedDate, int cursor, String unit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(comparedDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		if (unit.equalsIgnoreCase(SECOND)) {
			second = second - cursor;
		} else if (unit.equalsIgnoreCase(MINUTE)) {
			minute = minute - cursor;
		} else if (unit.equalsIgnoreCase(HOUR)) {
			hour = hour - cursor;
		} else if (unit.equalsIgnoreCase(DAY)) {
			date = date - cursor;
		} else if (unit.equalsIgnoreCase(MONTH)) {
			month = month - cursor;
		} else if (unit.equalsIgnoreCase(YEAR)) {
			year = year - cursor;
		}
		calendar.set(year, month, date, hour, minute, second);
		return calendar.getTime();
	}

	public static Date getAfter(Date comparedDate, int cursor, String unit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(comparedDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		if (unit.equalsIgnoreCase(SECOND)) {
			second += cursor;
		} else if (unit.equalsIgnoreCase(MINUTE)) {
			minute += cursor;
		} else if (unit.equalsIgnoreCase(HOUR)) {
			hour += cursor;
		} else if (unit.equalsIgnoreCase(DAY)) {
			date += cursor;
		} else if (unit.equalsIgnoreCase(MONTH)) {
			month += cursor;
		} else if (unit.equalsIgnoreCase(YEAR)) {
			year += cursor;
		}
		calendar.set(year, month, date, hour, minute, second);
		return calendar.getTime();
	}

	public static String deSerialize(Date date, String pattern) {
		if (date == null)
			return "";
		String defaultPattern = DEFAULT_MINUTE_PATTERN;
		if (pattern == null)
			pattern = defaultPattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	public static String deSerialize(Date date) {
		if (date == null)
			return "";
		return DEFAULT_MINUTE_FORMAT.format(date);
	}
	public static Date serialize(String dateStr, String pattern) throws ParseException {
		String defaultPattern = DEFAULT_MINUTE_PATTERN;
		if (pattern == null)
			pattern = defaultPattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(dateStr);
	}
	
	/**
	 * 
	  * 标准化输入的结束日期，在原有日期的基础上添加23:59:59
	  * 例如原始时间是2012-1-21 标准化为2012-1-21 23:59:59
	  *
	  * @modify: gangwang  Jan 23, 2013 4:17:11 PM
	  * @param stringDate
	  * @return    
	  * @return Date
	 */
	public static Date serializeEndDateTime(String stringDate){
		Date date = null;
		try {
			date = serialize(stringDate, DEFAULTPATTERN);
			date = getAfter(date, 1, DAY);
			date = getBefore(date, 1, SECOND);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 
	  * 标准化输入的结束日期，在原有日期的基础上添加23:59:59
	  * 例如原始时间是2012-1-21 标准化为2012-1-21 23:59:59
	  *
	  * @autor: yjY  2015-9-24 下午8:06:20
	  * @param stringDate
	  * @return Date
	 */
	public static Date endDateTime(Date date){
		if(date==null){
			return null;
		}else{
			return DateUtil.serializeEndDateTime(DateUtil.format(date, DateUtil.DEFAULT_DATE_FORMAT));
		}
	}
	/**
	 * 
	 * 标准化输入的结束日期，在原有日期的基础上添加00:00:00
	 * 例如原始时间是2012-1-21 标准化为2012-1-21 00:00:00
	 *
	 * @autor: yjY  2015-9-24 下午8:06:20
	 * @param stringDate
	 * @return Date
	 */
	public static Date startDateTime(Date date){
		Date result = null;
		if(date==null){
			return result;
		}else{
			try {
				result = DateUtil.serialize(DateUtil.format(date, DateUtil.DEFAULT_DATE_FORMAT), DateUtil.DEFAULT_DATE_FORMAT.toPattern());
			} catch (ParseException e) {
				return null;
			}
			return result;
		}
	}

	public static int getIntervalTypeOfTwoDates(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null)
			return INTERVAL_JUST;
		long endTime = endDate.getTime();
		long beginTime = beginDate.getTime();
		long intervalMilliSecond = endTime > beginTime ? endTime - beginTime : 0;
		long intervalSecond = intervalMilliSecond / 1000;
		if (intervalSecond < 600) {
			return INTERVAL_JUST;
		} else if (intervalSecond > 600 && intervalSecond < 1800) {
			return INTERVAL_10M;
		} else if (intervalSecond > 1800 && intervalSecond < 3600) {
			return INTERVAL_30M;
		} else if (intervalSecond > 3600 && intervalSecond < 7200) {
			return INTERVAL_1H;
		} else if (intervalSecond > 7200 && intervalSecond < 10800) {
			return INTERVAL_2H;
		} else if (intervalSecond > 10800 && intervalSecond < 18000) {
			return INTERVAL_3H;
		} else if (intervalSecond > 18000 && intervalSecond < 3600 * 24) {
			return INTERVAL_5H;
		} else if (intervalSecond > 3600 * 24) {
			return INTERVAL_1D;
		}
		return INTERVAL_JUST;
	}
	/**
	 * 获取当前时间的年月 yyyy-MM
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date getYearAndMonth(Date date){
		try {
			if(date==null){
				return null;
			}
			String year_month = DEFAULT_YEAR_MONTH_FORMAT.format(date);
			return StringUtils.isBlank(year_month) ? null : DEFAULT_YEAR_MONTH_FORMAT.parse(year_month);
		} catch (ParseException e) {
		}
		return null;
	}
	/**
	 * 获取当前时间的年月 yyyy
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date getYear(Date date){
		try {
			if(date==null){
				return null;
			}
			String year = DEFAULT_YEAR_FORMAT.format(date);
			return StringUtils.isBlank(year) ? null : DEFAULT_YEAR_FORMAT.parse(year);
		} catch (ParseException e) {
		}
		return null;
	}
	/**
	 * 获得date日期该天的开始时间 例如：2011-04-27 00:00:00是2011-04-27的开始时间
	 */
	public static Date getBeginTimeForDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 获得date日期该天的结束时间 例如：2011-04-28 00:00:00是2011-04-27的结束时间
	 */
	public static Date getEndTimeForDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 获得指定date.getTime()到现在的年数
	 * @param ms
	 * @return
	 */
	public static long getIntervalOfYears(long ms) {
		long interval = new Date().getTime() - ms;
		long years = interval / (1000l * 60 * 60 * 24 * 365);
		long reminder = interval % (1000l * 60 * 60 * 24 * 365);
		return reminder > 0l ? years + 1 : years;
	}
	
	public static Date parseYear(String yearStr){
		return parse(yearStr, DEFAULT_YEAR_FORMAT);
	}
	
	public static Date parseDate(String dateStr){
		return parse(dateStr, DEFAULT_DATE_FORMAT);
	}
	public static Date parseDatetime(String datetimeStr){
		return parse(datetimeStr, DEFAULT_DATETIME_FORMAT);
	}
	public static Date parseMonth(String dateStr){
		return parse(dateStr, DEFAULT_YEAR_MONTH_FORMAT);
	}
	public static Date parseSlashDatetime(String datetimeStr){
		return parse(datetimeStr, DEFAULT_DATETIME_SLASH_FORMAT);
	}
	
	public static Date parseMinute(String datetimeStr){
		return parse(datetimeStr, DEFAULT_MINUTE_FORMAT);
	}
	
	public static Date parse(String str, String formatStr){
		if(StringUtils.isBlank(str) || StringUtils.isBlank(formatStr)){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return parse(str, format);
	}
	
	/**
	 * 字符串转Date
	 */
	public static Date parse(String str, DateFormat format){
		if(StringUtils.isBlank(str)){
			return null;
		}
		try {
			return format.parse(str);
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static String formatDate(Date date){
		return format(date, DEFAULT_DATE_FORMAT);
	}
	
	public static String formatYear(Date date){
		return format(date, DEFAULT_YEAR_FORMAT);
	}
	
	public static String formatDatetime(Date date){
		return format(date, DEFAULT_DATETIME_FORMAT);
	}
	public static String formatMinute(Date date){
		return format(date, DEFAULT_MINUTE_FORMAT);
	}
	
	/**
	 * Date转字符串
	 */
	public static String format(Date date, DateFormat format){
		if(date == null || format == null){
			return "";
		}
		return format.format(date);
	}

	/**
	 * 将HH:mm:ss格式转化为秒计数方式
	 */
	public static Integer convertLengthToSeconds(String length) {
		if (StringUtils.isBlank(length)) {
			return 0;
		}
		String[] times = length.trim().split(":");
		if(times.length < 3) //格式有误
			return 0;
		try{
			return Integer.valueOf(times[0].trim()) * 3600 + Integer.valueOf(times[1].trim()) * 60 + Double.valueOf(times[2].trim()).intValue();
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	

	/**
	 * 获取时间差值，不足分钟数，显示秒数；不足小时数，显示分钟；不足一天显示小时数；大于1天显示天数，向上取整
	 * @param startTime
	 * @param nowTime
	 * @param interval
	 * @return
	 */
	public static String leftoverTime(Date startTime,Date nowTime,int interval){
		//开始时间添加间隔时间
		Calendar cal = Calendar.getInstance();  
        cal.setTime(startTime);  
        cal.add(Calendar.DAY_OF_MONTH, interval);
        //比较两个时间的时间间隔
        long startLong = cal.getTime().getTime();
        long nowLong = nowTime.getTime();
        if(nowLong>=startLong){//超期
        	return "超期";
        }
        //获取间隔的秒数
        int subInterval = (int) ((startLong-nowLong)/1000);
        //计算剩余几天
        if(subInterval%(24*60*60)==0){
        	return subInterval/(24*60*60)+"天";
        }
        int day = subInterval/(24*60*60);
        if(day>0){//大于1天
        	return (day+1)+"天";
        }
        //不足1天，计算小时数
        if(subInterval%(60*60)==0){
        	return subInterval/(60*60)+"小时";
        }
        int hour = subInterval/(60*60);
        if(hour>0){//大于1小时
        	return (hour+1)+"小时";
        }
        //不足一小时，计算分钟数
        if(subInterval%60==0){
        	return subInterval/60+"分钟";
        }
        int minute = subInterval/60;
        if(minute>0){//大于1分钟
        	return (minute+1)+"分钟";
        }
		return subInterval+"秒";
	}
	/**
	 * 获取去年的当前时间
	 * @param nowTime
	 * @return
	 */
	public static Date getLastDateTime(Date nowTime){
		Calendar cal = Calendar.getInstance();  
        cal.setTime(nowTime); 
        cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}
	/**
	 * 获取两个时间段之间连续的月份
	 * @param minDate
	 * @param maxDate
	 * @return
	 */
	public static List<String> getMonthBetween(Date minDate, Date maxDate){
		ArrayList<String> result = new ArrayList<String>();

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(minDate);
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(maxDate);
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			 result.add(DEFAULT_YEAR_MONTH_FORMAT.format(curr.getTime()));
			 curr.add(Calendar.MONTH, 1);
		}
		return result;
	}
	/**
	 * 获取两个时间段之间连续的年份
	 * @param minDate
	 * @param maxDate
	 * @return
	 */
	public static List<String> getYearBetween(Date minDate, Date maxDate){
		ArrayList<String> result = new ArrayList<String>();

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(minDate);
		min.set(min.get(Calendar.YEAR), 0, 1);
		max.setTime(maxDate);
		max.set(max.get(Calendar.YEAR), 0, 1);

		Calendar curr = min;
		while (curr.compareTo(max) <=0) {
			 result.add(DEFAULT_YEAR_FORMAT.format(curr.getTime()));
			 curr.add(Calendar.YEAR, 1);
		}
		return result;
	}
	/**
	 * 获取两个时间段之间连续的日期
	 * @param minDate
	 * @param maxDate
	 * @return
	 */
	public static List<String> getDayBetween(Date minDate, Date maxDate){
		ArrayList<String> result = new ArrayList<String>();

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(minDate);
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DAY_OF_MONTH));
		max.setTime(maxDate);
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DAY_OF_MONTH));
		Calendar curr = min;
		while (curr.compareTo(max) <=0) {
			 result.add(DEFAULT_DATE_FORMAT.format(curr.getTime()));
			 curr.add(Calendar.DAY_OF_MONTH, 1);
		}
		return result;
	}
	public static void main(String[] args) throws ParseException {
//		Date mindate = DEFAULT_DATE_FORMAT.parse("2016-10-14");
//		Date maxdate = DEFAULT_DATE_FORMAT.parse("2017-11-01");
//		List<String> instr =getMonthBetween(mindate,maxdate);
//		for(String str : instr){
//			System.out.println("剩余：" + str);
//		}
		System.out.println(parseYear("2017-10"));
		System.out.println(formatDate(parseYear("2017-10")));
	}
}
