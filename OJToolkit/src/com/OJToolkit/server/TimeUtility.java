package com.OJToolkit.server;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtility {
	public static Long getTimeinLinux(String date) {
		if(date.equals(""))return 0L ;
		Date d = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			try{
				DateFormat dateFormat2 = new SimpleDateFormat("dd MMM YYYY HH:mm:ss");
				d = dateFormat2.parse(date);
			}catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
			
		}
		System.out.println(d);
		Long r = d.getTime();
		return r;
	}

	public static String getTimeAsString(Long date) {
		Date d = new Date(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = dateFormat.format(d.getTime());
		return s;
	}

	public static String FormatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return dateFormat.format(date.getTime());
	}

	public static String FormatDate(String date) {
		String s = "" ;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    s = dateFormat.format(date);
		} catch (Exception e) {
			try{
				DateFormat dateFormat2 = new SimpleDateFormat("dd MMM YYYY HH:mm:ss");
				s = dateFormat2.format(date);
			}catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return s ;
	}
}
