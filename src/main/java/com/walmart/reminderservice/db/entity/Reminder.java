package com.walmart.reminderservice.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.walmart.reminderservice.common.ReminderStatus;

@Entity
@Table(name = "REMINDER") 
public class Reminder extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="REMINDER_SEQ")
	@SequenceGenerator(name="REMINDER_SEQ", sequenceName="REMINDER_SEQ")
	@Column(name = "REMINDER_ID")
	Integer reminderId;

	String reminderName;
	
	String reminderDescription;
	
	ReminderStatus reminderStatus;
	
	Date dueDate;

	public Reminder(){
		// Do nothing
	}
	
	public Reminder(String reminderName,String reminderDescription, ReminderStatus reminderStatus,Date dueDate){
		this.reminderName=reminderName;
		this.reminderDescription=reminderDescription;
		this.reminderStatus=reminderStatus;
		this.dueDate=dueDate;
	}
	
	public Integer getReminderId() {
		return reminderId;
	}

	public void setReminderId(int reminderId) {
		this.reminderId = reminderId;
	}

	public String getReminderName() {
		return reminderName;
	}

	public void setReminderName(String reminderName) {
		this.reminderName = reminderName;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public ReminderStatus getReminderStatus() {
		return reminderStatus;
	}

	public void setReminderStatus(ReminderStatus reminderStatus) {
		this.reminderStatus = reminderStatus;
	}

	public String getReminderDescription() {
		return reminderDescription;
	}

	public void setReminderDescription(String reminderDescription) {
		this.reminderDescription = reminderDescription;
	}
	
	
}
