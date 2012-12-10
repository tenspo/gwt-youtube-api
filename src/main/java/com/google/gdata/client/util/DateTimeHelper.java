package com.google.gdata.client.util;

import java.util.Date;

import com.google.gdata.client.youtube.ui.YouTubeMessages;
import com.google.gwt.core.client.GWT;

public class DateTimeHelper {

	private static YouTubeMessages messages = GWT.create(YouTubeMessages.class);

	public static String toString(Date date) {

		long diff = (new Date().getTime() - date.getTime()) / 1000;

		long second = diff;
		long minute = (diff = diff / 60);
		long hour = (diff = diff / 60);
		long day = (diff = diff / 24);
		long month = day / 30;
		long year = month / 12;
		
		if (year > 0) {
			return messages.dateTimeFormat(year + " " + (year == 1 ? messages.year() : messages.years()) + " ", messages.ago());
		}

		if (month > 0) {
			return messages.dateTimeFormat(month + " " + (month == 1 ? messages.month() : messages.months()) + " ", messages.ago());
		}

		if (day > 0) {
			return messages.dateTimeFormat(day + " " + (day == 1 ? messages.day() : messages.days()) + " ", messages.ago());
		}

		if (hour > 0) {
			return messages.dateTimeFormat(hour + " " + (hour == 1 ? messages.hour() : messages.hours()) + " ", messages.ago());
		}

		if (minute > 0) {
			return messages.dateTimeFormat(minute + " " + (minute == 1 ? messages.minute() : messages.minutes()) + " ", messages.ago());
		}

		if (second > 0) {
			return messages.dateTimeFormat(second + " " + (second == 1 ? messages.second() : messages.seconds()) + " ", messages.ago());
		}

		return messages.dateTimeFormat(0 + " " + messages.seconds() + " ", messages.ago());
	}
}
