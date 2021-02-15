package com.mindtree.emp.exception.daoexception;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeDaoErrorHandler {
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({EmployeeNotFoundDaoException.class})
    public ResponseEntity<String> handleNotFoundException(EmployeeNotFoundDaoException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }
//	@ExceptionHandler({EmployeeNotFoundDaoException.class})
//    public ResponseEntity<?> handleNotFoundException(EmployeeNotFoundDaoException e) {
//        return errorWithAll(HttpStatus.NOT_FOUND, e);
//    }


	@ExceptionHandler({EmployeeInputDaoException.class})
    public ResponseEntity<String> handleInputServiceException(EmployeeInputDaoException e) {
        return error(HttpStatus.BAD_REQUEST, e);
    }
    
    @ExceptionHandler({IntenalServerDaoException.class})
    public ResponseEntity<String> handleServerServiceException(IntenalServerDaoException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
    
	private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
