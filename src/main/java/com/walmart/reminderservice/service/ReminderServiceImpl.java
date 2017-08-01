package com.walmart.reminderservice.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.reminderservice.common.ReminderStatus;
import com.walmart.reminderservice.db.entity.Reminder;
import com.walmart.reminderservice.db.repository.ReminderRepository;
import com.walmart.reminderservice.exception.NoRecordFoundException;
import com.walmart.reminderservice.vo.ReminderVO;

/**
 * Implements the business logic. 
 * Uses the JPA repository to perform CRUD operations on Reminder. 
 * @author J
 *
 */
@Service
public class ReminderServiceImpl implements ReminderService{
	
	@Autowired
	ReminderRepository reminderRepository;
	
	/**
	 * Creates reminder
	 */
	@Override
	public int createReminder(ReminderVO reminderVO) {
		Reminder reminder = new Reminder(reminderVO.getName(),reminderVO.getDescription(),reminderVO.getStatus(),reminderVO.getDueDateNonFormatted());
		reminderRepository.save(reminder);
		return reminder.getReminderId();
	}

	/**
	 * Updates reminder
	 */
	@Override
	public void updateReminder(int reminderId, ReminderVO reminderVO) {
		Reminder reminder = new Reminder(reminderVO.getName(),reminderVO.getDescription(),reminderVO.getStatus(),reminderVO.getDueDateNonFormatted());
		reminder.setReminderId(reminderId);
		reminderRepository.save(reminder);
	}

	/**
	 * Retrieves the reminders after applying the given filters.
	 */
	@Override
	public List<ReminderVO> retrieveReminders(ReminderStatus reminderStatus, Date dueDate) {
		Iterable<Reminder> reminders = null;
		if(reminderStatus == null && dueDate == null){
			reminders = reminderRepository.findAll();
		}
		if(reminderStatus != null && dueDate != null){
			reminders = reminderRepository.findByDueDateAndReminderStatus(new java.sql.Date(dueDate.getTime()), reminderStatus);
		}
		
		if(reminderStatus == null && dueDate != null){
			reminders = reminderRepository.findByDueDate(new java.sql.Date(dueDate.getTime()));
		}
		
		if(reminderStatus != null && dueDate == null){
			reminders = reminderRepository.findByReminderStatus(reminderStatus);
		}
		
		if(reminders == null){
			return null;
		}
		List<ReminderVO> reminderVOs = new LinkedList<>();
		reminders.forEach((reminder) ->{
			reminderVOs.add(new ReminderVO(reminder.getReminderId(), reminder.getReminderName(), reminder.getReminderDescription(),reminder.getDueDate(), reminder.getReminderStatus()));
		});
		return reminderVOs;
	}

	/**
	 * Retrieves reminder referenced by the reminderId.
	 * @throws NoRecordFoundException 
	 */
	@Override
	public ReminderVO retrieveReminders(int reminderId) throws NoRecordFoundException {
		Reminder reminder = reminderRepository.findOne(reminderId);
		if(reminder == null){
			throw new NoRecordFoundException("No record found. Please check the reminder id");
		}
		return new ReminderVO(reminder.getReminderId(), reminder.getReminderName(), reminder.getReminderDescription(),reminder.getDueDate(), reminder.getReminderStatus());
	}

}
