package com.walmart.reminderservice.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.walmart.reminderservice.common.ReminderStatus;
import com.walmart.reminderservice.exception.DateNotValidException;
import com.walmart.reminderservice.util.Utility;

public class ReminderVO extends ResourceSupport{
	
	@JsonInclude(Include.NON_NULL)
	private int id;
	
	@NotBlank(message = "Reminder name can't be null or empty")
	private String name;
	
	@NotBlank(message = "Reminder description can't be null or empty")
	private String description;
	
	@JsonProperty(value="due_date")
	@NotNull(message = "Due date should not be null")
	private Date dueDate;
	
	@NotNull(message = "Reminder status should not be null")
	private ReminderStatus status;
	
	public ReminderVO(){
		// Do nothing
	}
	
	public ReminderVO(int id, String name,String dueDate,String description,ReminderStatus status) throws DateNotValidException{
		this.id = id;
		this.name=name;
		this.status=status;
		this.dueDate = Utility.parseDate(dueDate);
		
	}
	
	public ReminderVO(int id,String name,String description, Date dueDate, ReminderStatus reminderStatus) {
		this.id = id;
		this.name = name;
		this.description=description;
		this.dueDate = dueDate;
		this.status = reminderStatus;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public Date getDueDateNonFormatted() {
		return dueDate;
	}
	
	public String getDueDate(){
		return new SimpleDateFormat("yyyy-mm-dd").format(dueDate);
	}
	
	public void setDueDate(String dueDate) throws DateNotValidException {
		this.dueDate = Utility.parseDate(dueDate);
	}
	public ReminderStatus getStatus() {
		return status;
	}
	public void setStatus(ReminderStatus status) {
		this.status = status;
	}

	@JsonProperty(value="id")
	public int getReminderId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
