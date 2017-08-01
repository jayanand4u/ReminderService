package com.walmart.reminderservice.common;

import com.walmart.reminderservice.exception.StatusNotValidException;

public enum ReminderStatus {
	DONE("DONE"),
	NOTDONE("NOTDONE");
	String value;
	ReminderStatus(String value){
		this.value=value;
	}
	
	 public static ReminderStatus fromString(String status) throws StatusNotValidException {
		    if(status.equalsIgnoreCase("DONE")){
		    	return DONE;
		    }
		    if(status.equalsIgnoreCase("NOTDONE")){
		    	return NOTDONE;
		    }
		    throw new StatusNotValidException("Status code should be either DONE or NOTDONE");
	 }
}