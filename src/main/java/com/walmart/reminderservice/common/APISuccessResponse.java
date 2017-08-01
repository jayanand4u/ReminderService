package com.walmart.reminderservice.common;

/**
 * Model class for returning the success response for create/update API call. 
 * @author J
 *
 */
public class APISuccessResponse {
	String message;
	int id;
	
	public APISuccessResponse(String message, int id) {
		super();
		this.message = message;
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
