package com.walmart.reminderservice.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.reminderservice.common.APISuccessResponse;
import com.walmart.reminderservice.common.ReminderStatus;
import com.walmart.reminderservice.exception.DateNotValidException;
import com.walmart.reminderservice.exception.NoRecordFoundException;
import com.walmart.reminderservice.exception.StatusNotValidException;
import com.walmart.reminderservice.service.ReminderService;
import com.walmart.reminderservice.util.Utility;
import com.walmart.reminderservice.vo.ReminderVO;

/**
 * This is a REST controller that exposes API to perform CRUD operation on Reminder.
 * @author J
 *
 */

@RestController
@RequestMapping("/v1/reminderservice/reminders")
public class ReminderController {
	
	@Autowired
	ReminderService reminderService;
	
	/**
	 * Validates the attributes of the Reminder. If validation is successful then creates new Reminder.
	 * @param reminderVO holds the Reminder attributes.
	 * @return the reminder id of the newly created Reminder along with the success message.
	 */
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<APISuccessResponse> createReminder(@RequestBody(required = true) @Valid ReminderVO reminderVO){
		int reminderId = reminderService.createReminder(reminderVO);
		return new ResponseEntity<>(new APISuccessResponse("Reminder created successfully",reminderId),HttpStatus.OK);
	}
	
	/**
	 * Validates the attributes of the Reminder. If validation is successful then updates the Reminder referenced by the id in the request.
	 * @param reminderId the reminder id.
	 * @param reminderVO holds the new attribute values of the Reminder.
	 * @return the reminder id of the updated Reminder along with the success message.
	 */
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	public ResponseEntity<APISuccessResponse> updateReminder(@PathVariable(name="id", required=true) int reminderId, @RequestBody(required = true) @Valid ReminderVO reminderVO){
		reminderService.updateReminder(reminderId,reminderVO);
		return new ResponseEntity<>(new APISuccessResponse("Reminder updated successfully",reminderId),HttpStatus.OK);
	}
	
	/**
	 * Retrieves and returns the Reminders after applying the filters in the request (if any).
	 * Returns all Reminders if the status and dueDate filters are not in the request.
	 * @param status status filter
	 * @param dueDate due date filter
	 * @return reminders filtered by the filters in the request (if any). 
	 * @throws StatusNotValidException
	 * @throws DateNotValidException
	 */
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReminderVO>> retrieveReminders(@QueryParam("status") String status, @QueryParam("dueDate") String dueDate) throws StatusNotValidException, DateNotValidException{
		List<ReminderVO> reminderVOs = reminderService.retrieveReminders((status == null) ? null:ReminderStatus.fromString(status), (dueDate == null) ? null: Utility.parseDate(dueDate));
		reminderVOs.forEach((reminderVO)->{
			try {
				reminderVO.add(linkTo(methodOn(ReminderController.class).retrieveReminder(reminderVO.getReminderId())).withSelfRel());
			} catch (Exception e) {
				// log the error message.
			}
		});
		return new ResponseEntity<>(reminderVOs,HttpStatus.OK);
		
	}

	/**
	 * Returns Reminder referenced by the id in the request. 
	 * @param id the reminder id
	 * @return reminder
	 * @throws NoRecordFoundException 
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReminderVO> retrieveReminder(@PathVariable(name="id", required = true) int id) throws NoRecordFoundException {
		ReminderVO reminderVO = reminderService.retrieveReminders(id);
		return new ResponseEntity<>(reminderVO,HttpStatus.OK);
	}
	
}
