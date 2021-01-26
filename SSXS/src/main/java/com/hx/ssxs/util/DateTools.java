package com.hx.ssxs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 类功能:日期操作公共类
 * 
 * @author Jerome Guo
 * @date 2021-01-26
 */
public class DateTools {
	/**
	 * 日志
	 */
	private static Log log = LogFactory.getLog(DateTools.class);
	
	/**
	 * 字符串转换成日期类型
	 * 
	 * @param str
	 * @param dateformat
	 * @return
	 */
	public static Date strToDate(String str, String dateformat) {
		SimpleDateFormat format = new SimpleDateFormat(dateformat);

		Date retDate = null;
		try {
			retDate = format.parse(str);
		} catch (ParseException e) {
			if (log.isErrorEnabled()) {
				log.error("strToDate发生异常,string=[" + str + "]dateformat=["
						+ dateformat + "]", e);
			}
		}

		return retDate;
	}

	/**
	 * 获取系统当前日期和时间
	 * 
	 * @return
	 */
	public static String getCurryDateTime() {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");
		return format.format(new Date());
	}

	/**
	 * 获取系统当前日期和时间
	 * 
	 * @return
	 */
	public static String getCurryLongDateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}

	/**
	 * 获取系统当前日期
	 * 
	 * @return yyyyMMdd
	 */
	public static String getCurryDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}

	/**
	 * 按照给定格式得到日期时间
	 * 
	 * @return
	 */
	public static String getDatetime(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return HHmmssSSS
	 */
	public static String getCurryLongTime() {
		SimpleDateFormat format = new SimpleDateFormat("HHmmssSSS");
		return format.format(new Date());
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getCurryTime() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(new Date());
	}
	
	/**
	 * 获取时间格式字符串
	 * 
	 * @return
	 */
	public static String getDateTime(long value) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");
		return format.format(new Date(value));
	}

	/**
	 * 获取时间格式字符串
	 * 
	 * @return
	 */
	public static long getDateTime(String value) {
		Date date = DateTools.strToDate(value, "yyyy-MM-dd HH:mm:ss.SSS");
		return date.getTime();
	}
	/**
	 * 时间转换
	 * */
  public static Date jsTurnDate(long js, String time) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date date = sdf.parse(time);
      long ti = date.getTime();
      long real = ti + js * 1000L + 28800000L;
      return new Date(real);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public static void main(String[] args) {
    Date date = jsTurnDate(137669L, "2009-01-01");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println(sdf.format(date));
  }
}
