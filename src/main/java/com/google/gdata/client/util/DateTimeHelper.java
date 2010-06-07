package com.google.gdata.client.util;

import org.gwttime.time.DateTime;
import org.gwttime.time.DateTimeFieldType;

import com.google.gdata.client.youtube.YouTubeMessages;
import com.google.gwt.core.client.GWT;

public class DateTimeHelper {

	private static YouTubeMessages messages = GWT.create(YouTubeMessages.class);

	public static String toString(DateTime dateTime) {
		DateTime now = new DateTime();
		DateTime diff = now.minus(dateTime.getMillis());
		
		int year = diff.get(DateTimeFieldType.year()) - 1970;
		
		if (year > 0) {
			return messages.dateTimeFormat(year + " " + (year == 1 ? messages.year() : messages.years()) + " ", messages.ago());
		}

		int month = diff.get(DateTimeFieldType.monthOfYear());

		if (month > 0) {
			return messages.dateTimeFormat(month + " " + (month == 1 ? messages.month() : messages.months()) + " ", messages.ago());
		}

		int day = diff.get(DateTimeFieldType.dayOfMonth());

		if (day > 0) {
			return messages.dateTimeFormat(day + " " + (day == 1 ? messages.day() : messages.days()) + " ", messages.ago());
		}

		int hour = diff.get(DateTimeFieldType.hourOfDay());

		if (hour > 0) {
			return messages.dateTimeFormat(hour + " " + (hour == 1 ? messages.hour() : messages.hours()) + " ", messages.ago());
		}

		int minute = diff.get(DateTimeFieldType.minuteOfHour());

		if (minute > 0) {
			return messages.dateTimeFormat(minute + " " + (minute == 1 ? messages.minute() : messages.minutes()) + " ", messages.ago());
		}

		int second = diff.get(DateTimeFieldType.secondOfMinute());

		if (second > 0) {
			return messages.dateTimeFormat(second + " " + (second == 1 ? messages.second() : messages.seconds()) + " ", messages.ago());
		}
		
		return messages.dateTimeFormat(0 + " " + messages.seconds() + " ", messages.ago());
	}
}
