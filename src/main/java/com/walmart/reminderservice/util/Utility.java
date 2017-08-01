package com.walmart.reminderservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.walmart.reminderservice.exception.DateNotValidException;

public class Utility {
	/**
	 * Parses the given date in the ISO-8601 date format. 
	 * May use the UTC date format like yyyy-mm-ddThh:mm:ssZ
	 * 
	 * @param dueDate
	 * @return
	 * @throws DateNotValidException
	 */
	public static Date parseDate(String dueDate) throws DateNotValidException {
		try {
			Date parsedDate = new SimpleDateFormat("yyyy-mm-dd").parse(dueDate);
			// TO DO : validation to check if the due date is invalid (may not be a past date).
			return parsedDate;
		} catch (ParseException e) {
			throw new DateNotValidException("Due date is invalid. It should be in yyyy-mm-dd format");
		}
	}
}
