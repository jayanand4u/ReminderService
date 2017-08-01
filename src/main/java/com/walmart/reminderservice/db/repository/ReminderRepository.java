package com.walmart.reminderservice.db.repository;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;

import com.walmart.reminderservice.common.ReminderStatus;
import com.walmart.reminderservice.db.entity.Reminder;

public interface ReminderRepository extends CrudRepository<Reminder,Integer>{
	Iterable<Reminder> findByDueDate(Date dueDate);
	Iterable<Reminder> findByReminderStatus(ReminderStatus reminderStatus);
	Iterable<Reminder> findByDueDateAndReminderStatus(Date dueDate,ReminderStatus reminderStatus);
}
