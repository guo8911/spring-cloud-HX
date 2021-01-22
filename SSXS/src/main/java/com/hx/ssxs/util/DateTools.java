package com.hx.ssxs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
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
