package com.walmart.reminderservice.exception.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.walmart.reminderservice.common.APIErrorResponse;
import com.walmart.reminderservice.exception.DateNotValidException;
import com.walmart.reminderservice.exception.NoRecordFoundException;
import com.walmart.reminderservice.exception.StatusNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Value("${error.global.msg}") 
	private String globalErrorMsg;
	
	@Value("${error.parser.msg}") 
	private String parserErrorMsg;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIErrorResponse> handleInvalidMethodArgument(HttpServletResponse response, MethodArgumentNotValidException e) throws IOException {
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(getValidationErrorMessage(e));
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<APIErrorResponse> handleParserException(HttpServletResponse response, HttpMessageNotReadableException e) throws IOException {
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.BAD_REQUEST);	
	}
	
	
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<APIErrorResponse> handleHttpMethodNotSupported(HttpServletResponse response, Exception e) throws IOException {
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		error.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		error.setMessage("Method not allowed");
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler({StatusNotValidException.class,DateNotValidException.class,NoRecordFoundException.class})
	public ResponseEntity<APIErrorResponse> handleFilterValidationException(HttpServletResponse response, Exception e) throws IOException {
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIErrorResponse> handleException(HttpServletResponse response, Exception e) throws IOException {
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(globalErrorMsg);
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
/**
 * This method handles validation error.
 * @param validationException
 * @return String validation error message
 */
	private String getValidationErrorMessage(MethodArgumentNotValidException validationException) {
		List<String> list = new ArrayList<String>();
		StringBuffer errorMessage = new StringBuffer();
		String separator = "";
		try {
			List<ObjectError> errors = validationException.getBindingResult().getAllErrors();
			if(errors != null){
				for (ObjectError objectError : errors) {
					list.add(objectError.getDefaultMessage());
					errorMessage.append(separator);
					errorMessage.append(objectError.getDefaultMessage());
					separator= "; ";
				}
			}
		} catch (Exception e) {
			// log the error
		}
		return errorMessage.toString();
	}
	
	
}
