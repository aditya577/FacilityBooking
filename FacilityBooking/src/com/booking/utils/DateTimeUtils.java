package com.booking.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {

	public static boolean isSlotBooked(Date bookedStartTime, Date bookedEndTime, Date newBookingStartTime,
			Date newBookingEndTime) {
		DateFormat f = new SimpleDateFormat("kk:mm:ss");
		if ((f.format(bookedStartTime).compareTo(f.format(newBookingStartTime)) <= 0)
				&& (f.format(bookedEndTime).compareTo(f.format(newBookingStartTime)) > 0)) {
			return true;
		}
		if ((f.format(bookedStartTime).compareTo(f.format(newBookingEndTime)) < 0)
				&& (f.format(bookedEndTime).compareTo(f.format(newBookingEndTime)) >= 0)) {
			return true;
		}
		return false;
	}

	public static int getHoursDifference(Date date1, Date date2) {
		final int MILLI_TO_HOUR = 1000 * 60 * 60;
		return (int) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
	}

	public static Date convertDateTimeToDate(String date, String time) {
		String string = date + " " + time;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd kk:mm", Locale.ENGLISH);
		Date finalDate = null;
		try {
			finalDate = f.parse(string);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return finalDate;
	}
}
