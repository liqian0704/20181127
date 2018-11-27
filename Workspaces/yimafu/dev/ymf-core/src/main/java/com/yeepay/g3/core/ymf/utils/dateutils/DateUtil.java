package com.yeepay.g3.core.ymf.utils.dateutils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {
	private static Logger logger = Logger.getLogger(DateUtil.class);
	
	//日期格式模板
	public static final String DATE_FMT_YYYY_MM_DD ="yyyy-MM-dd"; 
	
	public static final String DATE_FMT_MM_DD ="MM月dd日"; 
	
	public static final String DATE_FMT_DD ="dd日"; 
	
	public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT = "MM/dd/yyyy";

	public static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm";

	public static final String ORA_DATE_FORMAT = "yyyyMMdd";

	public static final String ORA_DATE_TIME_FORMAT = "yyyyMMddHHmm";

	public static final String ORA_DATE_TIMES_FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";

	public static final String ORA_DATES_FORMAT = "yyyy-MM-dd";
	
	public static final String ORA_DATES_FORMAT_ZH = "yyyy年MM月dd日";

	public static final String ORA_DATE_TIMES_FORMAT_ZH = "yyyy年MM月dd日 HH点mm分ss秒";
	
	//取得当前日期的前N天日期（要求传入返回日期格式）
	public static String getPreDate(int n,String fmttmp){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -n);
		Date d =  c.getTime();
		SimpleDateFormat fm = new SimpleDateFormat(fmttmp);//返回规定日期格式
		return fm.format(d);
	} 
	
	//取得给定日期格式化字符串
	public static String getFmtDate(Date date,String fmttmp){
		SimpleDateFormat fm = new SimpleDateFormat(fmttmp);//返回规定日期格式
		return fm.format(date);
	}
	
	//把字符串转换为日期
	public static Date getStrToDate(String str,String fmttmp){
		SimpleDateFormat sdf = new SimpleDateFormat(fmttmp); 
		Date date = null;
		try{
			date = sdf.parse(str); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获得昨天的日期
	 * 
	 * @return
	 */
	public static String getYesterday(String fmttmp) {

		Date date = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, -1);
		Date tomorrow = ca.getTime();
		return toStrDateFromUtilDateByFormat(tomorrow, fmttmp);
	}
	/**
	 * 获得今天的日期
	 * 
	 * @return
	 */
	public static String getToday(String fmttmp) {
		Date date = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, 0);
		Date nowDate = ca.getTime();
		return toStrDateFromUtilDateByFormat(nowDate, fmttmp);
	}
	
	/**
	 * return the current time format TIME_PATTERN_SESSION
	 * 
	 * @return the formatted string
	 */
	public static String getSessionTime() {

		return formatDate(new java.util.Date(System.currentTimeMillis()),
				TIME_PATTERN_SESSION);
	}
	/**
	 * format the date in given pattern
	 * 
	 * @return the formatted string
	 */
	public static String formatDate(java.util.Date d, String pattern) {

		SimpleDateFormat formatter = (SimpleDateFormat) SimpleDateFormat
				.getDateInstance();
		formatter.applyPattern(pattern);
		return formatter.format(d);
	}
	
    /**
     * <p>Convert String to Date .</p>
     * <p>将字符串"yyyy-MM-dd"转成Calendar类型.</p>
     * <p>For more details see <code>DateUtilBean</code>.</p>
     *
     * @param date ：参数格式为"yyyy-MM-dd"
     * @return 根据DATE_PATTERN规则 转成的日期
     * @see DateUtilBean#convertToDate
     */
    public static Calendar convertToDate(String date) {
        return DateUtilBean.getUtilBean().convertToDate(date);
    }
    
    /**
	 * 获得昨天的日期
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.DATE, -1);
		return ca.getTime();
	}
	
	/**
	 * util.Date型日期转化指定格式的字符串型日期
	 * 
	 * @param p_date
	 *            Date
	 * @param p_format
	 *            String 格式1:"yyyy-MM-dd" 格式2:"yyyy-MM-dd hh:mm:ss EE"
	 *            格式3:"yyyy年MM月dd日 hh:mm:ss EE" 说明: 年-月-日 时:分:秒 星期 注意MM/mm大小写
	 * @return String
	 * @Author: zhuqx
	 * @Date: 2006-10-31
	 */
	private static String toStrDateFromUtilDateByFormat(
			java.util.Date p_utilDate, String p_format) {

		String l_result = "";
		if (p_utilDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(p_format);
			l_result = sdf.format(p_utilDate);
		}
		return l_result;
	}
	
	/**
	 * 取得通用日期时间格式字符串
	 * @param Date
	 * @return String
	 */

	public static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
	/**
	 * 比较 日期dt1 + 天数 days 与 dt2的差 如果大于： 返回 1 如果等于： 返回 0 如果小于： 返回 -1
	 * 
	 * @param dt1 --
	 *            日期1(忽略 时分秒）
	 * @param days --
	 *            日期1需要加上的天数
	 * @param dt2 --
	 *            日期1(忽略 时分秒）
	 */
	public static int compare(Date dt1, int days, Date dt2) {
		// return getIntDate(getDate(dt1, days)) - getIntDate(dt2);
		int betweenDays = new Day(getDate(dt1, days)).daysBetween(new Day(dt2));
		return betweenDays;
		// if (dt1.after(dt2))
		// return betweenDays;
		// else
		// return -betweenDays;
	}
	/**
	 * 根据偏移天数，获取新的Date对象
	 * @param date
	 * @param days 正数表示增加的天数，负数表示减少的天数
	 * @return
	 */
	public static Date getDate(Date date, int days) {
		Day day = new Day(date);
		day.addDays(days);
		return day.getDate();
	}
	/**
	 * 
	* @Title: addMinuteToDate
	* @Description: 添加偏移分钟
	* @param @param date
	* @param @param minutes
	* @param @return    
	* @return Date    
	* @throws
	 */
	public static Date addMinuteToDate(Date date, int minutes) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MINUTE, minutes);
		return ca.getTime();
	}
	/**
	 * 取得昨天Date对象
	 * @param
	 * @return Date 
	 */
	public static Date getYesterdayDate() {   		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE , -1);
		return calendar.getTime();
	}

	/**
	 * 获取当天的00:00:00
	 * @param date
	 * @return
     */
	public static Date getMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当天的23:59:59
	 * @param date
	 * @return
     */
	public static Date getMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		return calendar.getTime();
	}

	/**
	 * 字符串日期格式按照日期模式转换成日期
	 * 
	 * @param sDate --
	 *            字符串的日期
	 * @param pattern --
	 *            日期格式 （比如：yyyy-MM-dd）
	 * @return
	 * @throws ParseException
	 */
	public static Date parseToDate(String sDate, String pattern)
			throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.parse(sDate);
	}

	/**
	 * 获得一天的0时0秒0分
	 * @param date date
	 * @return Date
     */
	public static Date getFirstOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获得一天的24时0秒0分
	 * @param date date
	 * @return Date
	 */
	public static Date getLastOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}


	
	public static Date parseToTodayDesignatedDate(Date date, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd " + time);
        Date end = null;
        try {
            end = sdf.parse(sdf1.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }
	
	/**
	 * 比较两个时间的先后。如果大于： 返回 1 如果等于： 返回 0 如果小于： 返回 -1
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static int compareDate(Date dt1,Date dt2){
        if (dt1.getTime() > dt2.getTime()) {
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            return -1;
        } else {//相等
            return 0;
        }
}
	
	public static void main(String args[]){
		int isToday = DateUtil.compare(DateUtil.getStrToDate("2016-01-01 00:00:00", "yyyy-MM-dd HH:mm:dd"), 0, new Date());
//		DateUtil.addMinuteToDate(new Date(), 10);
		System.out.println(DateUtil.addMinuteToDate(new Date(), 10));
		
	}
	
	
}
