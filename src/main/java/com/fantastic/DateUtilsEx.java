package com.fantastic;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;


/**
 * 
 * 类描述：时间操作定义类
 * 
 * @author: 张代浩
 * @date： 日期：2012-12-8 时间：下午12:15:03
 * @version 1.0
 */
public class DateUtilsEx extends PropertyEditorSupport {

	public static final String[] WEEKDAYS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	public static final String yyyy = "yyyy";
	public static final String mm_dd = "MM-dd";
	public static final String dd = "dd";
	// 各种时间格式
	// date_sdf
	public static final String yyyyMM = "yyyyMM";
	public static final String yyyy_MM = "yyyy-MM";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String yy_MM_dd = "yy/MM/dd";
	public static final SimpleDateFormat yyyyMMddHHmmssfff = new SimpleDateFormat("yyyyMMddhhmmssSSS");
	// 各种时间格式
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMMddHH = "yyyyMMddHH";
	// 各种时间格式
	public static final String yyyy_MM_dd_ = "yyyy年MM月dd日";
	public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String yyyyMMddhhmmss = "yyyyMMddHHmmss";
	public static final String HH_mm = "HH:mm";
	public static final String HH_mm_ss = "HH:mm:ss";
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd_HH_mm_ = "yyyy年MM月dd日HH时mm分";
	public static final String HH_MM_ = "HH时mm分";

	// 以毫秒表示的时间
	public static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
	public static final long HOUR_IN_MILLIS = 3600 * 1000;
	public static final long MINUTE_IN_MILLIS = 60 * 1000;
	public static final long SECOND_IN_MILLIS = 1000;

	// 指定模式的时间格式
	private static SimpleDateFormat getSDFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	/**
	 * 当前日历，这里用中国时间表示
	 * 
	 * @return 以当地时区表示的系统当前日历
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 指定毫秒数表示的日历
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日历
	 */
	public static Calendar getCalendar(long millis) {
		Calendar cal = Calendar.getInstance();
		// --------------------cal.setTimeInMillis(millis);
		cal.setTime(new Date(millis));
		return cal;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getDate
	// 各种方式获取的Date
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 当前日期
	 * 
	 * @return 系统当前时间
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 指定毫秒数表示的日期
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日期
	 */
	public static Date getDate(long millis) {
		return new Date(millis);
	}

	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamptoStr(Timestamp time) {
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date2Str(date, yyyy_MM_dd);
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, yyyy_MM_dd);
		return new Timestamp(date.getTime());
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @param sdf
	 * @return
	 */
	public static Date str2Date(String str, String sdf) {
		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = _df(sdf).parse(str);
			return date;
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return null;
	}

	public static SimpleDateFormat _df(String str) {
		return new SimpleDateFormat(str);
	}

	public static Date str2DateTry(String str) {

		if (str == null) {
			return null;
		}

		int indexY = str.indexOf("-");
		int indexH = str.indexOf(":");
		// System.out.println(String.format("-:%d,::%d", indexY, indexH));
		if (str.length() == "yyyy_MM_dd_HH_mm_ss".length() && indexY == 4 && indexH == 13) {
			return str2Date(str, yyyy_MM_dd_HH_mm_ss);
		}

		if (str.length() == "yyyy_MM_dd_HH_mm_ss".length() - 1 && indexY == 4 && indexH == 12) {
			str = str.substring(0, "yyyy_MM_dd_".length()) + "0" + str.substring("yyyy_MM_dd_".length());
			return str2Date(str, yyyy_MM_dd_HH_mm_ss);
		}
		if (str.length() == "yyyy_MM_dd_HH_mm".length() && indexY == 4 && indexH == 13) {
			return str2Date(str, yyyy_MM_dd_HH_mm);
		}
		if (str.length() == "yyyy_MM_dd_HH_mm".length() - 1 && indexY == 4 && indexH == 12) {
			str = str.substring(0, "yyyy_MM_dd_".length()) + "0" + str.substring("yyyy_MM_dd_".length());
			return str2Date(str, yyyy_MM_dd_HH_mm);
		}
		if (str.length() == "yyyyMMddhhmmss".length() && indexY < 0 && indexH < 0) {
			return str2Date(str, yyyyMMddhhmmss);
		}

		if (str.length() == "yyyyMMddHH".length() && indexY < 0 && indexH < 0) {
			return str2Date(str, yyyyMMddHH);
		}

		if (str.length() == "yyyyMMdd".length() && indexY < 0 && indexH < 0) {
			return str2Date(str, yyyyMMdd);
		}

		if (str.length() == "yyyyMM".length() && indexY < 0 && indexH < 0) {
			return str2Date(str + "01", "yyyyMMdd");
		}

		if (str.length() == "yyyy".length() && indexY < 0 && indexH < 0) {
			return str2Date(str, yyyy);
		}

		if (str.length() == "yyyy_MM_dd".length() && indexY == 4 && indexH < 0) {
			return str2Date(str, yyyy_MM_dd);
		}

		if (str.length() == "HH_mm_ss".length() && indexY < 0 && indexH > 0) {
			return str2Date(str, HH_mm_ss);
		}

		if (str.length() == "HH_mm".length() && indexY < 0 && indexH > 0) {
			return str2Date(str, HH_mm);
		}
		String[] formats = new String[] { yyyy_MM_dd_, mm_dd, yyyy, HH_mm };
		for (String format : formats) {
			Date date = str2Date(str, format);
			if (date != null) {
				return date;
			}
		}
		return null;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(String date_sdf) {
		Date date = new Date();
		// if (null == date) {
		// return null;
		// }
		return _df(date_sdf).format(date);
	}

	public static Date date2fdate(String date_sdf) {
		return DateUtilsEx.str2Date(DateUtilsEx.date2Str(date_sdf), date_sdf);
	}

	public static Date date2fdate(Date date, String date_sdf) {
		return DateUtilsEx.str2Date(DateUtilsEx.date2Str(date, date_sdf), date_sdf);
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateformat(String date, String format) {
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date _date = null;
		try {
			_date = sformat.parse(date);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return sformat.format(_date);
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, String date_sdf) {
		if (null == date) {
			return null;
		}
		return _df(date_sdf).format(date);
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String getDate(String format) {
		Date date = new Date();
		if (format == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 指定毫秒数的时间戳
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数的时间戳
	 */
	public static Timestamp getTimestamp(long millis) {
		return new Timestamp(millis);
	}

	/**
	 * 以字符形式表示的时间戳
	 * 
	 * @param time
	 *            毫秒数
	 * @return 以字符形式表示的时间戳
	 */
	public static Timestamp getTimestamp(String time) {
		return new Timestamp(Long.parseLong(time));
	}

	/**
	 * 系统当前的时间戳
	 * 
	 * @return 系统当前的时间戳
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 指定日期的时间戳
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的时间戳
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 指定日历的时间戳
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的时间戳
	 */
	public static Timestamp getCalendarTimestamp(Calendar cal) {
		// ---------------------return new Timestamp(cal.getTimeInMillis());
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp gettimestamp() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
		return buydate;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getMillis
	// 各种方式获取的Millis
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 系统时间的毫秒数
	 * 
	 * @return 系统时间的毫秒数
	 */
	public static long getMillis() {
		return new Date().getTime();
	}

	/**
	 * 指定日历的毫秒数
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的毫秒数
	 */
	public static long getMillis(Calendar cal) {
		// --------------------return cal.getTimeInMillis();
		return cal.getTime().getTime();
	}

	/**
	 * 指定日期的毫秒数
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的毫秒数
	 */
	public static long getMillis(Date date) {
		return date.getTime();
	}

	/**
	 * 指定时间戳的毫秒数
	 * 
	 * @param ts
	 *            指定时间戳
	 * @return 指定时间戳的毫秒数
	 */
	public static long getMillis(Timestamp ts) {
		return ts.getTime();
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatDate
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日
	 * 
	 * @return 默认日期按“年-月-日“格式显示
	 */
	public static String formatDate() {
		return _df(yyyy_MM_dd).format(getCalendar().getTime());
	}

	/**
	 * 获取时间字符串
	 */
	public static String getDateString(String formatstr){
		return _df(formatstr).format(getCalendar().getTime());
	}
	public static String getDataString(String formatstr) {
		return _df(formatstr).format(getCalendar().getTime());
	}

	public static String getDateString(SimpleDateFormat formatstr) {
		return formatstr.format(getCalendar().getTime());
	}
	public static String getDataString(SimpleDateFormat formatstr) {
		return formatstr.format(getCalendar().getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Calendar cal) {
		return _df(yyyy_MM_dd).format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Date date) {
		return _df(yyyy_MM_dd).format(date);
	}
	
	public static String formatChinaDate(Date date) {
		return _df(yyyy_MM_dd_HH_mm_).format(date);
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“年-月-日“格式显示
	 */
	public static String formatDate(long millis) {
		return _df(yyyy_MM_dd).format(new Date(millis));
	}

	/**
	 * 默认日期按指定格式显示
	 * 
	 * @param pattern
	 *            指定的格式
	 * @return 默认日期按指定格式显示
	 */
	public static String formatDate(String pattern) {
		return getSDFormat(pattern).format(getCalendar().getTime());
	}

	/**
	 * 指定日期按指定格式显示
	 * 
	 * @param cal
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Calendar cal, String pattern) {
		return getSDFormat(pattern).format(cal.getTime());
	}

	/**
	 * 指定日期按指定格式显示
	 * 
	 * @param date
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Date date, String pattern) {
		return getSDFormat(pattern).format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日 时：分
	 * 
	 * @return 默认日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime() {
		return _df(yyyy_MM_dd_HH_mm).format(getCalendar().getTime());
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(long millis) {
		return _df(yyyy_MM_dd_HH_mm).format(new Date(millis));
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(Calendar cal) {
		return _df(yyyy_MM_dd_HH_mm).format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(Date date) {
		return _df(yyyy_MM_dd_HH_mm).format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatShortTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：时：分
	 * 
	 * @return 默认日期按“时：分“格式显示
	 */
	public static String formatShortTime() {
		return _df(HH_mm_ss).format(getCalendar().getTime());
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：时：分
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“时：分“格式显示
	 */
	public static String formatShortTime(long millis) {
		return _df(HH_mm_ss).format(new Date(millis));
	}

	/**
	 * 指定日期的默认显示，具体格式：时：分
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Calendar cal) {
		return _df(HH_mm_ss).format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：时：分
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Date date) {
		return _df(HH_mm_ss).format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// parseDate
	// parseCalendar
	// parseTimestamp
	// 将字符串按照一定的格式转化为日期或时间
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Date parseDate(String src, String pattern) throws ParseException {
		return getSDFormat(pattern).parse(src);

	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Calendar parseCalendar(String src, String pattern) throws ParseException {

		Date date = parseDate(src, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String formatAddDate(String src, String pattern, int amount) throws ParseException {
		Calendar cal;
		cal = parseCalendar(src, pattern);
		cal.add(Calendar.DATE, amount);
		return formatDate(cal);
	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的时间戳
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Timestamp parseTimestamp(String src, String pattern) throws ParseException {
		Date date = parseDate(src, pattern);
		return new Timestamp(date.getTime());
	}

	// ////////////////////////////////////////////////////////////////////////////
	// dateDiff
	// 计算两个日期之间的差值
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * 
	 * @param flag
	 *            计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param calSrc
	 *            减数
	 * @param calDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return (calSrc.get(Calendar.YEAR) - calDes.get(Calendar.YEAR));
		}

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h') {
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm') {
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's') {
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}

	/**
	 * String类型 转换为Date, 如果参数长度为10 转换格式”yyyy-MM-dd“ 如果参数长度为19 转换格式”yyyy-MM-dd HH:mm:ss“ * @param text String类型的时间值
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(_df(DateUtilsEx.yyyy_MM_dd).parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(_df(DateUtilsEx.yyyy_MM_dd_HH_mm_ss).parse(text));
				} else {
					throw new IllegalArgumentException("Could not parse date, date format is error ");
				}
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		} else {
			setValue(null);
		}
	}

	public static int getYear() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		return calendar.get(Calendar.YEAR);
	}

	public static boolean afterEqual(Date d1, Date d2) {
		return d1.getTime() >= d2.getTime();
	}

	public static Date getTodayTimeByTimeDate(Date time, String defaultTime) {
		if (time == null) {
			return DateUtilsEx.getTodayTimeByTimeStr("", defaultTime);
		}
		String strTime = DateUtilsEx.date2Str(time, DateUtilsEx.HH_mm_ss);
		return DateUtilsEx.getTodayTimeByTimeStr(strTime, defaultTime);
	}

	public static Date getDateTimeByTimeStr(Date dateInit, Date time, String defaultTime) {
		if (time == null) {
			return DateUtilsEx.getDateTimeByTimeStr(dateInit, "", defaultTime);
		}
		String strTime = DateUtilsEx.date2Str(time, DateUtilsEx.HH_mm_ss);
		return getDateTimeByTimeStr(dateInit, strTime, defaultTime);
	}

	public static Date getDateTimeByTimeStr(Date dateInit, String time, String defaultTime) {
		String strToday = DateUtilsEx.date2Str(dateInit, DateUtilsEx.yyyy_MM_dd);
		String strTime = strToday + " " + time;
		Date date = DateUtilsEx.str2Date(strTime, DateUtilsEx.yyyy_MM_dd_HH_mm);
		if (date == null) {
			date = DateUtilsEx.str2Date(strToday + " " + defaultTime, DateUtilsEx.yyyy_MM_dd_HH_mm);
		}
		return date;
	}

	public static Date getTodayTimeByTimeStr(String time, String defaultTime) {
		String strToday = DateUtilsEx.date2Str(DateUtilsEx.yyyy_MM_dd);
		String strTime = strToday + " " + time;
		Date date = DateUtilsEx.str2Date(strTime, DateUtilsEx.yyyy_MM_dd_HH_mm);
		if (date == null) {
			date = DateUtilsEx.str2Date(strToday + " " + defaultTime, DateUtilsEx.yyyy_MM_dd_HH_mm);
		}
		return date;
	}

	public static Date getDateByDateAndHHmmssStr(Date date, String time) {
		if (date == null) {
			return null;
		}
		String strToday = DateUtilsEx.date2Str(date, DateUtilsEx.yyyy_MM_dd);
		String strTime = strToday + " " + time;
		return DateUtilsEx.str2Date(strTime, DateUtilsEx.yyyy_MM_dd_HH_mm_ss);
	}

	public static boolean inRange(Date d, Date rang1, Date rang2) {
		return d.getTime() >= rang1.getTime() && d.getTime() <= rang2.getTime();
	}

	public static Date[] getBetweetDays(Date d) {
		if (d == null) {
			return new Date[] { null, null };
		}
		Date today = null, tommorow = null;
		today = DateUtilsEx.str2Date(DateUtilsEx.date2Str(d, DateUtilsEx.yyyy_MM_dd) + " 00:00:00", DateUtilsEx.yyyy_MM_dd_HH_mm_ss);
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DAY_OF_MONTH, 1);
		tommorow = c.getTime();
		return new Date[] { today, tommorow };
	}

	public static int dayForWeek(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static String dayForWeekEx(Date date) {
		int dayofWeek = dayForWeek(date);
		if (dayofWeek == 1) {
			dayofWeek = 7;
		} else {
			dayofWeek = dayofWeek - 1;
		}
		return dayofWeek + "";
	}

	public static String getWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return WEEKDAYS[w];
	}

	public static Date getDateForDayBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
		// String dateOfyyyymmdd = DateUtilsEx.date2Str(date, DateUtilsEx.yyyy_MM_dd) +" 00:00:00";
		// return DateUtilsEx.str2Date(dateOfyyyymmdd, DateUtilsEx.yyyy_MM_dd_HH_mm_ss);
	}

	public static Date getDateForDayEnd(Date date) {
		// String dateOfyyyymmdd = DateUtilsEx.date2Str(date, DateUtilsEx.yyyy_MM_dd) +" 23:59:59";
		// return DateUtilsEx.str2Date(dateOfyyyymmdd, DateUtilsEx.yyyy_MM_dd_HH_mm_ss);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 得到某年某周的第一天
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		week = week - 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);

		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		week = week - 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 取得当前日期所在周的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Sunday
		return calendar.getTime();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6); // Saturday
		return calendar.getTime();
	}

	/**
	 * 取得当前日期所在周的前一周最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfLastWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR) - 1);
	}

	/**
	 * 返回指定日期的月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的上个月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfLastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的季的第一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的季的第一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 1 - 1;
		} else if (quarter == 2) {
			month = 4 - 1;
		} else if (quarter == 3) {
			month = 7 - 1;
		} else if (quarter == 4) {
			month = 10 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getFirstDayOfMonth(year, month);
	}

	/**
	 * 返回指定日期的季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 3 - 1;
		} else if (quarter == 2) {
			month = 6 - 1;
		} else if (quarter == 3) {
			month = 9 - 1;
		} else if (quarter == 4) {
			month = 12 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}

	public static Date getFirstDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getLastDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的上一季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfLastQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 返回指定年季的上一季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 12 - 1;
		} else if (quarter == 2) {
			month = 3 - 1;
		} else if (quarter == 3) {
			month = 6 - 1;
		} else if (quarter == 4) {
			month = 9 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 * 返回指定日期的季度
	 *
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}

	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	public static long nowMillis() {
		return System.currentTimeMillis();
	}

	public static long nowNano() {
		return System.nanoTime();
	}

	public static String formatDateAndTime(long millis) {
		return formatDateAndTime(new Date(millis));
	}

	public static String formatDateMinute(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return f.format(date);
	}

	public static String formatDateMinute(long millis) {
		return formatDateMinute(new Date(millis));
	}

	public static String formatDateLocale(Date date) {
		String ww = String.format(Locale.ENGLISH, "%ta", date);
		String MM = String.format(Locale.ENGLISH, "%tb", date);
		String dd = String.format(Locale.ENGLISH, "%te", date);
		String hh = String.format(Locale.ENGLISH, "%tl", date);
		String mm = String.format(Locale.ENGLISH, "%tM", date);
		String ap = String.format(Locale.ENGLISH, "%tp", date);
		return ww + " " + MM + " " + dd + " " + hh + ":" + mm + ap;
	}

	public static String getMonth(long millis) {
		Date date = new Date(millis);
		return String.format(Locale.ENGLISH, "%tB", date);
	}

	public static String getDay(long millis) {
		Date date = new Date(millis);
		return String.format(Locale.ENGLISH, "%te", date);
	}

	public static String getWeekday(long millis) {
		Date date = new Date(millis);
		return String.format(Locale.ENGLISH, "%tA", date);
	}

	public static String getTime(long millis) {
		Date date = new Date(millis);
		String hh = String.format(Locale.ENGLISH, "%tl", date);
		String mm = String.format(Locale.ENGLISH, "%tM", date);
		String ap = String.format(Locale.ENGLISH, "%tp", date);
		return hh + ":" + mm + " " + ap;
	}

	public static String formatDateLocale(long millis) {
		return formatDateLocale(new Date(millis));
	}

	public static String formatDateAndTime(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return f.format(date);
	}

	public static String formatDateAndSecond(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(date);
	}

	// public static String formatDate(long millis) {
	// return formatDate(new Date(millis));
	// }
	//
	// public static String formatDate(Date date) {
	// SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	// return f.format(date);
	// }

	public static String formatDateCh(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy骞碝M鏈坉d鏃�");
		return f.format(date);
	}

	public static String formatDateAndTimeCh(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy骞碝M鏈坉d鏃� HH鐐筸m鍒唖s绉�");
		return f.format(date);
	}

	public static String formatDateCh(long millis) {
		return formatDateCh(new Date(millis));
	}

	public static String formatDateAndTimeCh(long millis) {
		return formatDateAndTimeCh(new Date(millis));
	}

	// get tody 0 timestamp
	public static long getTimesmorning() {
		return getTimestamp(0);
	}

	public static long getTimestamp(int hour) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	 /**
	*  获取日期相月数
	* @param 
	* @return 日期类型时间
	* @throws ParseException 
	*/
	public static int getDiffMonth(String beginDate, String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dbeginDate = null;
		Date dendDate = null;
		try {
			dbeginDate = formatter.parse(beginDate);
			dendDate = formatter.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getDiffMonth(dbeginDate, dendDate);
	}

	public static int getDiffMonth(Date beginDate, Date endDate) {
		Calendar calbegin = Calendar.getInstance();
		Calendar calend = Calendar.getInstance();
		calbegin.setTime(beginDate);
		calend.setTime(endDate);
		int m_begin = calbegin.get(Calendar.MONTH) + 1;
		int m_end = calend.get(Calendar.MONTH) + 1;
		int checkmonth = m_end - m_begin + (calend.get(Calendar.YEAR) - calbegin.get(Calendar.YEAR)) * 12;
		return checkmonth;
	}
	
	public static String[] getDaysInMonth(Date date){
		if(date==null)
			return null;
		List<String> days = new ArrayList<String>();
		
		Date startdate = getFirstDayOfMonth(date);
		Date enddate = getLastDayOfMonth(date);
		
		while(afterEqual(enddate ,startdate)){
			String day = getDay(startdate.getTime());
			days.add(day);
			startdate = DateUtils.addDays(startdate, 1);
		}
		
		return (String[])days.toArray(new String[days.size()]);
	
	}

	public static int daysBetween(Date sdate, Date edate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdate = sdf.parse(sdf.format(sdate));
			edate = sdf.parse(sdf.format(edate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(edate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days))+1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
	
	
	public static void main(String... strings) {

//		String _date = "2015-08-01 8:30";
//		Date sdate = DateUtilsEx.str2DateTry(_date);
//		System.out.println(DateUtilsEx.date2Str(sdate, DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
//		Date startdate = DateUtilsEx.getFirstDayOfMonth(sdate);
//		startdate = DateUtilsEx.getDateForDayBegin(startdate);
//		Date enddate = DateUtilsEx.getLastDayOfMonth(sdate);
//		enddate = DateUtilsEx.getDateForDayEnd(enddate);
//		System.out.println(DateUtilsEx.date2Str(startdate, DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
//		System.out.println(DateUtilsEx.date2Str(enddate, DateUtilsEx.yyyy_MM_dd_HH_mm_ss));
//
//		// System.out.println(getTodayTimeByTimeStr("1", "8:00:00"));
//		Calendar c = Calendar.getInstance();
//		Date date = c.getTime();
//
//		Date firstDateOfWeek = getFirstDayOfWeek(date);
//		Date lastDateOfWeek = getLastDayOfWeek(date);
//
//		Calendar firstC = Calendar.getInstance();
//		firstC.setTime(firstDateOfWeek);
//		firstC.add(Calendar.DATE, 1);
//
//		Calendar lastC = Calendar.getInstance();
//		lastC.setTime(lastDateOfWeek);
//		lastC.add(Calendar.DATE, 1);
//
//		int bdays = lastC.get(Calendar.DAY_OF_MONTH) - firstC.get(Calendar.DAY_OF_MONTH);
//
//		System.out.println("前后相隔  天数:" + bdays);
//
//		System.out.println(formatDate(firstC.getTime()));
//		System.out.println(formatDate(lastC.getTime()));
//
//		String weekName = getWeekOfDate(date);
//		System.out.println(weekName);
//
//		Date date1 = DateUtilsEx.str2DateTry("2015-01-01 10:10:10");
//		System.out.println(DateUtilsEx.date2Str(date1, DateUtilsEx.yyyy_MM_dd_HH_mm_ss));

//		System.out.println(DateUtilsEx.getDiffMonth(new Date(), DateUtils.addDays(new Date(), 700)));
		Date d1 = DateUtilsEx.str2DateTry("2016-05-03");
		System.out.println(daysBetween(d1,new Date()));
		
		
		
	}

}