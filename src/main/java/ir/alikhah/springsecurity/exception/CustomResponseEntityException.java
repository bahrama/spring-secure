package ir.alikhah.springsecurity.exception;

import ir.alikhah.springsecurity.exception.user.UserAlreadyExistsException;
import ir.alikhah.springsecurity.exception.user.UserNotFindException;
import ir.alikhah.springsecurity.exception.user.UserNotFindExceptionResponse;
import ir.alikhah.springsecurity.exception.user.UsernameAlreadyExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityException extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExists(UserAlreadyExistsException ex, WebRequest request){
        UsernameAlreadyExistsResponse exceptionResponse = new UsernameAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserNotFind(UserNotFindException ex, WebRequest request){
        UserNotFindExceptionResponse exceptionResponse = new UserNotFindExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
