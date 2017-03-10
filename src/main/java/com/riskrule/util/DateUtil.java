package com.riskrule.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	 private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		System.out.println();
	}
	/***
	 * 获得dateStr日期
	 * 相聚月初的天数
	 *
	 * */
	public static int monthDayCount(String dateStr) {

		Date d = null;
		try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    Date d1 = getMonthStart(d);
	    return daysBetween(d1 , d);
	}

	private static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }

    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }
    public static Date getBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
    /**
	 * 两个日期之间相距自然天数
	 * */
    public static int daysBetween(Date smdate,Date bdate)
    {

        long _t = getMillonTimesLong(smdate, bdate);
        long between_days=(_t)/(1000*3600*24);

       return Integer.parseInt(String.valueOf(between_days));
    }
	private static long getMillonTimesLong(Date smdate, Date bdate) {
		try {
			smdate=sdf.parse(sdf.format(smdate));
			 bdate=sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
			e.printStackTrace();
		}

        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long _t = time2 - time1;
		return _t;
	}

	/**
	 * 两个日期之间相距小时
	 * */
	public static double hoursBetween(Date smdate,Date bdate)
    {

       Long _t = getMillonTimesLong(smdate, bdate);
       Long houems = 1000*3600L;
       return (_t.doubleValue())/(houems.doubleValue());
    }

}
