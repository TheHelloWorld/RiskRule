/**
 *
 */
package com.riskrule.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 这里用一句话描述这个类的作用
 * @see: CommonUtil 此处填写需要参考的类
 * @version 2015年10月16日 下午6:00:38
 * @author xiaohui.wei
 */
public class CommonUtil implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdfFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @Description 将object类型转化为String
	 * @param ruleDetail
	 * @param type
	 * @return
	 * @see
	 */

	public static String getTransformString(Map<String, Object> ruleDetail, String type) {
		String typeStr = null;
		if (null != ruleDetail.get(type)) {
			typeStr = (String) ruleDetail.get(type);
		}
		return typeStr;
	}

	/**
	 * @Description 将String转化成double
	 * @param str
	 * @return
	 * @see
	 */
	public static double getTransformStringToDouble(String str) {
		if (null != str && !"".equals(str)) {
			return Double.valueOf(str);
		}
		return 0;
	}

	/**
	 * @Description 将String转化成Date 格式为yyyy-MM-dd HH:mm:ss
	 * @param str
	 * @return Date
	 * @see
	 */
	public static Date getTransformStringToDate(String str) {

		try {
			if (null != str && !"".equals(str)) {
				return sdfFull.parse(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description 比较两个String类型的时间相差多少秒
	 * @param str1
	 * @param str2
	 * @return
	 * @see
	 */
	public static int getTimeDifferentString(String str1, String str2) {

		try {
			if (null != str1 && null != str2) {
				Date date1 = sdfFull.parse(str1);
				Date date2 = sdfFull.parse(str2);
				int time = CommonUtil.getTimeDifferentDate(date1, date2);
				return time;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * @Description 比较两个Date类型的时间相差多少秒
	 * @param date1
	 * @param date2
	 * @return
	 * @see
	 */
	public static int getTimeDifferentDate(Date date1, Date date2) {
		if (null != date1 && null != date2) {
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			int time3 = (int) ((time1 - time2) / 1000);
			int time4 = Math.abs(time3);
			return time4;
		}
		return 0;
	}
}
