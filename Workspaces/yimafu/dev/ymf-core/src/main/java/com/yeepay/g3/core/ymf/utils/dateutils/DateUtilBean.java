package com.yeepay.g3.core.ymf.utils.dateutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.util.StringUtils;

/**
 * <p/> 提供日期相关运算方法的实现类
 * <p> Date: Apr 6, 2010 Time: 5:09:11 PM
 *
 * @author Felix.ZHU
 * @author hez
 * @since v0.3.0
 */
public class DateUtilBean {
// ------------------------------ FIELDS ------------------------------

    // 时间格式 yyyy-MM-dd 支持到天
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final Logger logger = Logger.getLogger(DateUtilBean.class);
    private static final DateUtilBean instance = new DateUtilBean();
    // 时间格式  yyyy-MM-dd HH:mm:ss 支持到秒
    private static final SimpleDateFormat FORMAT_DATETIME
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat FORMAT_DATE
            = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    // 时间格式 yyyy-MM支持到月
    private static final SimpleDateFormat FORMAT_MONTH
            = new SimpleDateFormat("yyyy-MM");
    // 时间计算格式
    private static final int MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    private static final ThreadLocal<DateUtilBean> BEAN_HOLDER
            = new ThreadLocal<DateUtilBean>();

// -------------------------- STATIC METHODS --------------------------
    // IMPORTANT: each of this bean holds several helper SimpleDateFormats which are NOT thread-safe,
    // thus we make the bean thread local style

    public static DateUtilBean getUtilBean() {
        if (BEAN_HOLDER.get() != null) {
            return BEAN_HOLDER.get();
        }
        DateUtilBean bean = new DateUtilBean();
        BEAN_HOLDER.set(bean);
        return bean;
    }

// --------------------------- CONSTRUCTORS ---------------------------

    private DateUtilBean() {

    }

// -------------------------- OTHER METHODS --------------------------

    /**
     * <p>Convert String to Date .</p>
     * <p>将字符串"yyyy-MM-dd"转成Calendar类型.</p>
     *
     * @param date ："yyyy-MM-dd"
     * @return 根据DATE_PATTERN规则 转成的日期
     */
    public Calendar convertToDate(String date) {
        String message = "Converting from day to Date fails!Please offer parameter like: 'yyyy-MM-dd' ! ";
        return assembleCalendar(date, FORMAT_DATE, message);
    }

    /**
     * <p>assemble Calendar .</p>
     * <p> 根据传入规则将待转换日期转换成Calendar .</p>
     *
     * @param dateTime ：待转换的日期字符串
     * @param format   ：转换的规则
     * @param message  ：转换出错后的的信息
     * @return 转换后的日期
     * @throws ApplicationException 如果传入的参数不符合规则pattern要求，则抛出ApplicationException
     */
    public Calendar assembleCalendar(String dateTime, SimpleDateFormat format, String message) {
        if (!StringUtils.hasText(dateTime)) {
            return null;
        }
        Date d=null;
        try {
            d = format.parse(dateTime);
        } catch (ParseException e) {
            logger.error(message);
            
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar;
    }

    /**
     * <p>Convert String to DateTime .</p>
     * <p> 将 "yyyy-MM-dd HH:mm:ss"转成Calendar .</p>
     *
     * @param dateTime ： "yyyy-MM-dd HH:mm:ss"
     * @return 根据DATETIME_PATTERN规则 转成的日期
     */
    public Calendar convertToDateTime(String dateTime) {
        String message = "Converting from milliseconds to Date fails!Please offer parameter like: 'yyyy-MM-dd HH:mm:ss' ";
        return assembleCalendar(dateTime, FORMAT_DATETIME, message);
    }

    /**
     * <p> Convert String to Month .</p>
     * <p>将字符串"yyyy-MM"转成Calendar类型</p>
     *
     * @param month "yyyy-MM"
     * @return 根据MONTH_PATTERN规则 转成的日期
     */
    public Calendar convertToMonth(String month) {
        String message = "Converting from month to Date fails! Please offer parameter like: 'yyyy-MM'! ";
        return assembleCalendar(month, FORMAT_MONTH, message);
    }

    /**
     * <p> Convert DateTime to String  .</p>
     * <p>将Date类型转成字符串"yyyy-MM-dd HH:mm:ss" .</p>
     *
     * @param date : 待转换的日期
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public String convertToString(Date date) {
        return FORMAT_DATETIME.format(date);
    }

    /**
     * <p> Convert DateTime to String  .</p>
     * <p>将Calendar类型转成字符串"yyyy-MM-dd HH:mm:ss" .</p>
     *
     * @param date ：待转换的日期
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public String convertToString(Calendar date) {
        return convertToString(date.getTime());
    }

    /**
     * getCurrentMonLastDay()
     * <p> 计算当月最后一天,返回那一天 。</p>
     * <p/>
     * <pre>
     * 范例如下：
     * 本月是4月，则：
     * ICoalDateUtils.getCurrentMonLastDay() = "2010-04-30"
     * </pre>
     *
     * @return 返回当月最后一天。
     */
    public Calendar getCurrentMonLastDay() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);//设为当月的1号
        lastDate.add(Calendar.MONTH, 1);//加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);//减去一天变为当月的最后一天
        return lastDate;
    }

    /**
     * <p>取得一周中的星期几.</p>
     *
     * @param date 准备检验的日期
     * @return 具体日期对应的数字
     *         2=星期一、3=星期二、4=星期三、5=星期四、6=星期五、7=星期六、1=星期天
     */
    public int getDayOfWeek(Calendar date) {
        // 范围 1~7
        return date.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * <p>取得两个时间之间相差的天数,参数类型为Date </p>
     *
     * @param startDate 开始的时间
     * @param endDate   结束的时间
     * @return startDate 与  endDate 之间相差的天数
     */
    public int getDaysBetween(Date startDate, Date endDate) {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startDate);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endDate);
        return getDaysBetween(calendarStart, calendarEnd);
    }

    /**
     * <p>取得两个时间之间相差的天数,参数类型为Calendar.</p>
     *
     * @param startDate 开始的时间
     * @param endDate   结束的时间
     * @return startDate 与  endDate 之间相差的天数
     */
    public int getDaysBetween(Calendar startDate, Calendar endDate) {
        long totalDate;
        long startTime = startDate.getTimeInMillis();
        long endTime = endDate.getTimeInMillis();
        totalDate = Math.abs((endTime - startTime)) / (MILLIS_IN_A_DAY);
        return (int) totalDate;
    }

    /**
     * getPreviousMonFirstDay()
     * <p>获取当月的第一天</p>
     * <p/>
     * <pre>
     * 范例如下：
     * 本月是4月，则：
     * ICoalDateUtils.getFirstDayOfMonth() = "2010-04-01"
     * </pre>
     *
     * @return 当月的第一天
     */
    public Calendar getFirstDayOfMonth() {
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(Calendar.DATE, 1);// 设为当月的第一天
        return firstDate;
    }

    /**
     * getNextMonthEnd()
     * <p>获取下个月最后一天.</p>
     * <p/>
     * <pre>
     * 范例如下：
     * 本月是4月，则：
     * ICoalDateUtils.getNextMonthEnd() = "2010-05-31"
     * </pre>
     *
     * @return 下个月的最后一天
     */
    public Calendar getNextMonthEnd() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, 1);//加一个月
        lastDate.set(Calendar.DATE, 1);//把日期设为当月第一天
        lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也即是当月最后一天
        return lastDate;
    }

    /**
     * getNextMonthFirst()
     * <p>获取下个月的第一天</p>
     * <p/>
     * <pre>
     * 范例如下：
     * 本月是4月，则：
     * ICoalDateUtils.getNextMonthFirst() = "2010-05-01"
     * </pre>
     *
     * @return 下个月的第一天
     */
    public Calendar getNextMonthFirst() {
        Calendar firstDate = Calendar.getInstance();
        firstDate.add(Calendar.MONTH, 1);//加一个月
        firstDate.set(Calendar.DATE, 1);//获取当月的第一天
        return firstDate;
    }

    /**
     * getPreviousMonFirstDay()
     * <p>获取当前月份上一个月的第一天.</p>
     * <p/>
     * <pre>
     * 范例如下：
     * 本月是4月，则：
     * ICoalDateUtils.getPreviousMonFirstDay() = "2010-03-01"
     * </pre>
     *
     * @return 当前月份上一个月的第一天。
     */
    public Calendar getPreviousMonFirstDay() {
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(Calendar.DATE, 1);//设为当前月份的一号
        firstDate.add(Calendar.MONTH, -1);//减一个月，变为上月的1号
        return firstDate;
    }

    /**
     * getPreviousMonLastDay()
     * <p>获取当前月份上个月的最后一天</p>
     * <p/>
     * <pre>
     * 范例如下：
     * 本月是4月，则：
     * ICoalDateUtils.getPreviousMonLastDay() = "2010-03-31"
     * </pre>
     *
     * @return 当前月份上个月的最后一天
     */
    public Calendar getPreviousMonLastDay() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月份的一号
        lastDate.add(Calendar.DATE, -1);// 减去一天变为上月的最后一天
        return lastDate;
    }

    /**
     * getToday()
     * <p>获得当天的时间 .</p>
     * <p/>
     * <pre>
     * 范例如下：
     * Date date = ICoalDateUtils.getToday() = "2010-4-2";
     * </pre>
     *
     * @return 当天的时间
     */
    public Calendar getToday() {
        return Calendar.getInstance();
    }

    /**
     * isAfter(start,end)
     * <p>比较日期start是否在日期end之后.</p>
     * <pre>
     * 范例如下：
     * <p/>
     * ICoalDateUtils.isAfter("2010-4-5","2010-4-6") = false
     * ICoalDateUtils.isAfter("2010-4-6","2010-4-6") = false
     * ICoalDateUtils.isAfter("2010-4-7","2010-4-6") = true
     * </pre>
     *
     * @param start 时间1
     * @param end   时间2
     * @return 如果日期start在日期end之后返回true，否则返回false.
     */
    public boolean isAfter(Calendar start, Calendar end) {
        return dateCompare(start, end) == 1;
    }

    /**
     * dateCompare(date1,date2)
     * <p>比较两个日期的大小.</p>
     * <pre>
     * 范例如下：
     * <p/>
     * ICoalDateUtils.dateCompare("2010-4-5","2010-4-6") = -1
     * ICoalDateUtils.dateCompare("2010-4-6","2010-4-6") = 0
     * ICoalDateUtils.dateCompare("2010-4-7","2010-4-6") = 1
     * </pre>
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 如果date1等于date2 则返回 0, date1在 date2之前则返回-1, date1在date2之后返回1
     */
    public int dateCompare(Calendar date1, Calendar date2) {
        return date1.compareTo(date2);
    }

    /**
     * isBefore(start,end)
     * <p>比较日期start是否在日期end之前.</p>
     * <pre>
     * 范例如下：
     * <p/>
     * ICoalDateUtils.isBefore("2010-4-5","2010-4-6") = true
     * ICoalDateUtils.isBefore("2010-4-6","2010-4-6") = false
     * ICoalDateUtils.isBefore("2010-4-7","2010-4-6") = false
     * </pre>
     *
     * @param start 时间1
     * @param end   时间2
     * @return 如果日期start在日期end之前返回true,否则返回false.
     */
    public boolean isBefore(Calendar start, Calendar end) {
        return dateCompare(start, end) == -1;
    }

    /**
     * isEqual(start,end)
     * <p>比较日期start是否与日期end相等.</p>
     * <pre>
     * 范例如下：
     * <p/>
     * ICoalDateUtils.isAfter("2010-4-5","2010-4-6") = false
     * ICoalDateUtils.isAfter("2010-4-6","2010-4-6") = true
     * ICoalDateUtils.isAfter("2010-4-7","2010-4-6") = false
     * </pre>
     *
     * @param start 时间1
     * @param end   时间2
     * @return 如果日期star与日期end相等返回true，否则返回false.
     */
    public boolean isEqual(Calendar start, Calendar end) {
        return dateCompare(start, end) == 0;
    }

    /**
     * isLeapYear(year)
     * <p>是否闰年.</p>
     * <p/>
     * <pre>
     * 范例如下：
     * year 格式2010,2011
     * ICoalDateUtils.isLeapYear(2010) = false
     * ICoalDateUtils.isLeapYear(2000) = true
     * </pre>
     *
     * @param year year
     * @return 是闰年则返回true 否则返回false
     */
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
