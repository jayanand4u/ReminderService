package com.walmart.reminderservice;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.http.ContentType;
import com.walmart.reminderservice.common.ReminderStatus;
import com.walmart.reminderservice.exception.DateNotValidException;
import com.walmart.reminderservice.vo.ReminderVO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ReminderServiceApplicationTests {
	ReminderVO reminderVO;
	
	@LocalServerPort
	private int PORT;
	
	@Before
	public void setup() throws DateNotValidException{
		reminderVO = new ReminderVO();
		reminderVO.setName("My first reminder");
		reminderVO.setDescription("Remind me on the due date");
		reminderVO.setDueDate("2017-08-30");
		reminderVO.setStatus(ReminderStatus.NOTDONE);
	}
	
	@Test
	public void testReminderCreateStatusCode(){
		given().contentType(ContentType.JSON).body(reminderVO)
			.when().post(getBaseURL()).then().statusCode(200);
		
	}
	
	@Test
	public void testCreateReminder(){
		given().contentType(ContentType.JSON).body(reminderVO)
			.when().post(getBaseURL()).then()
			.body("id", notNullValue())
			.body("message", notNullValue());
		
	}
	

	private String getBaseURL() {
		return String.format("http://localhost:%d/v1/reminderservice/reminder", PORT);
	}
}
