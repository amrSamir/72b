package com.OJToolkit.server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gwt.i18n.client.DateTimeFormat;

public class TimeUtility {
	public static Long getTimeinLinux(String date) {
		Date d = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Long r = d.getTime();
		return r;
	}

	public static String getTimeAsString(Long date) {
		Date d = new Date(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String s = dateFormat.format(d.getTime());
		return s;
	}

	public static String FormatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date.getTime());
	}

	public static String FormatDate(String date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
}
