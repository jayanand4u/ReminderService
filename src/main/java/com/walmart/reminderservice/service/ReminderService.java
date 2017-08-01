package com.walmart.reminderservice.service;

import java.util.Date;
import java.util.List;

import com.walmart.reminderservice.common.ReminderStatus;
import com.walmart.reminderservice.exception.NoRecordFoundException;
import com.walmart.reminderservice.vo.ReminderVO;

public interface ReminderService {

	int createReminder(ReminderVO reminderVO);

	void updateReminder(int reminderId, ReminderVO reminderVO);

	List<ReminderVO> retrieveReminders(ReminderStatus reminderStatus, Date date);

	ReminderVO retrieveReminders(int reminderId) throws NoRecordFoundException;

}
