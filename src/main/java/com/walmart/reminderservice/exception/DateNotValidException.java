package com.walmart.reminderservice.exception;

public class DateNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DateNotValidException(String msg){
		super(msg);
	}
}
