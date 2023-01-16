package ir.alikhah.springsecurity.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFindException extends RuntimeException {
    public UserNotFindException(String message) {
        super(message);
    }
}
