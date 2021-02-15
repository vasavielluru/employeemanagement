package com.mindtree.emp.exception.serviceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeServiceErrorHandler {
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({EmployeeNotFoundServiceException.class})
    public ResponseEntity<String> handleNotFoundException(EmployeeNotFoundServiceException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }
    
    @ExceptionHandler({EmployeeInputServiceException.class})
    public ResponseEntity<String> handleInputServiceException(EmployeeInputServiceException e) {
        return error(HttpStatus.BAD_REQUEST, e);
    }
    
    @ExceptionHandler({IntenalServerServiceException.class})
    public ResponseEntity<String> handleServerServiceException(IntenalServerServiceException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
    
	private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
