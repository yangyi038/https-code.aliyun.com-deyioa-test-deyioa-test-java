package com.framework.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateTool {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dfhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 计算日期的计算月，i为负数表示上i个月，i为正数表示下i个月
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date nextMoth(Date date,int i){
		Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.MONTH, i);
        return calender.getTime();
	}
	/**
	* 日期转换成字符串
	* @param date 
	* @param df 
	         (yyyy-MM-dd\yyyy-MM-dd HH:mm:ss)
	* @return str
	*/
	public static String DateToStr(Date date,String df) {
		if (date==null){
			return "";
		}else{
			 try {
			SimpleDateFormat format = new SimpleDateFormat(df);
			String str = format.format(date);
			   return str;
			 } catch (Exception e) {
				    e.printStackTrace();
				    return "";
			}
		}
	   
	} 

	/**
	* 字符串转换成日期
	* @param str
	* @param df 
	         (yyyy-MM-dd\yyyy-MM-dd HH:mm:ss)
	* @return date
	*/
	public static Date StrToDate(String str,String df) {
	  if(str==null){
		  return null;
	  }else{
		  SimpleDateFormat format = new SimpleDateFormat(df);
		   Date date = null;
		   try {
		    date = format.parse(str);
		   } catch (ParseException e) {
		    e.printStackTrace();
		    return null;
		   }
		   return date;
	  }
	   
	}
	/**
	* 生成特定月的某一日期
	* @param str
	* @return date
	*/
	public static Date datedayl(Date date,int str) {
		String d=DateToStr(date,"yyyy-MM-dd");		
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   try {
		   if(str!=0){
			   date = format.parse(d.substring(0,8)+str);
		   }	    
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	/**
	 * 获取日期相日数
	 * @param now
	 * @param returnDate
	 * @return
	 */
	public static int daysBetween(Date now, Date returnDate){
	    Calendar cNow = Calendar.getInstance();
	    Calendar cReturnDate = Calendar.getInstance();
	    cNow.setTime(now);
	    cReturnDate.setTime(returnDate);
	    setTimeToMidnight(cNow);
	    setTimeToMidnight(cReturnDate);
	    long todayMs = cNow.getTimeInMillis();
	    long returnMs = cReturnDate.getTimeInMillis();
	    long intervalMs = todayMs - returnMs;
	    return millisecondsToDays(intervalMs);
	  }

	public static int millisecondsToDays(long intervalMs){
	    return (int) (intervalMs / (1000 * 86400));
	  }

	public static void setTimeToMidnight(Calendar calendar){
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	  }


	 /**
	　　* 获取日期相月数
	　　* @param 
	　　* @return 日期类型时间
	　　* @throws ParseException 
	　　*/
	public static int getDiffMonth(Date beginDate,Date endDate){
		Calendar calbegin = Calendar.getInstance();
		Calendar calend = Calendar.getInstance();
		calbegin.setTime(beginDate);
		calend.setTime(endDate);
		int m_begin = calbegin.get(Calendar.MONTH)+1; //获得合同开始日期月份
		int m_end = calend.get(Calendar.MONTH)+1;
		//获得合同结束日期月份
		int checkmonth = m_end-m_begin+(calend.get(Calendar.YEAR)-calbegin.get(Calendar.YEAR))*12;
		//获得合同结束日期于开始的相差月份
		return checkmonth;

	}
	/**
	 * 获取日期中的“日”
	 * @param date
	 * @return
	 */
	public static int getDay(Date date){
		Calendar calendar = GregorianCalendar.getInstance(); 
		calendar.setTime(date); 
		return calendar.get(Calendar.DATE);
	}
	/**
	 * 相差时间
	 * @param now
	 * @param date
	 * @return
	 */
	public static Map<String, Object> getDiffYmd(Date now,Date date){
		if(now==null||date==null){
			return null;
		}
		Map<String, Object> ret=new HashMap<String, Object>();
		 long l=now.getTime()-date.getTime();   
		 long day=l/(24*60*60*1000);   
		 long hour=(l/(60*60*1000)-day*24);   
		 long min=((l/(60*1000))-day*24*60-hour*60);   
		 long s=(l/1000-day*24*60*60-hour*60*60-min*60); 
		 ret.put("d", day);
		 ret.put("h", hour);
		 ret.put("m", min);
		 ret.put("s", s);
		 
		 return ret;
	}
	
	  
	
	
	
	/**
	 * 计算日期的计算天，i为负数表示上i天，i为正数表示下i天
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date printNextTime(Date date,int i) 
    { 
        Calendar cal = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        try 
        { 
            cal.setTime(date); 
            cal.add(cal.DATE, i); 
            return StrToDate(sdf.format(cal.getTime()),"yyyy-MM-dd");
        } 
        catch (Exception e) 
        { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        }
		return date; 
    } 
  /**
   * 判断d1-d2是否在d3-d4中间
   * 判断d3-d4是否在d1-d2中间
   * @param d1
   * @param d2
   * @param d3
   * @param d4
   * @return 是
   */
	 public static boolean  comTwoDate(Date d1,Date d2,Date d3,Date d4){
		if (d1.compareTo(d3) < 0 && d2.compareTo(d3) < 0) {
			return false;
		} else if (d1.compareTo(d4) > 0 && d2.compareTo(d4) > 0) {
			return false;
		} else if (d1.compareTo(d3) < 0 && d2.compareTo(d3) < 0) {
			return false;
		} 
		else if (d1 == d3 || d1 == d4 || d2 == d3 || d2 == d4){
			return false;
		}	else {
			return true;
		}
	}
	 /**
	     * 获取当前日期是星期几<br>
	     * 
	     * @param dt
	     * @return 当前日期是星期几
	     */
	    public static int getWeekOfDate(Date dt) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(dt);

	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)
	            w = 0;

	        return w;
	    }

	/**
	 * 获取年、月、日、时、分、秒格式时间
	 * 
	 * @return
	 */
	public static Timestamp getTimestamp() {
		Date date = new Date();
		Timestamp nousedate = new Timestamp(date.getTime());
		return nousedate;

	}

	/**
	 * 是否有效的日期
	 * 
	 * @param dateStr
	 * @param pattern  (yyyy-MM-dd\yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static boolean isValidDate(String dateStr, String pattern) {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(pattern);
		try {
			df.setLenient(false);
			df.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;

		}
	}

	 public static void main(String[] args){
	 }
	 /**
	  * Timestamp -> Date
	 * @return 

	  */
	 public static Date TimestampToDate (Timestamp ts){
			try {
				String str = dfhms.format(ts);
				return StrToDate(str,"yyyy-MM-dd HH:mm:ss");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	 }
	
}
