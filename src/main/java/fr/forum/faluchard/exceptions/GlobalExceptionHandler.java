package fr.forum.faluchard.exceptions;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import fr.forum.faluchard.errors.ErrorResponse;
import fr.forum.faluchard.errors.HttpErrorCode;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<ErrorResponse> handleUsernameTaken(UsernameTakenException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpErrorCode.USERNAME_TAKEN.ordinal());
        response.setMessage(ex.getMessage());
        response.setDetails(Map.of("username", ex.getUsername()));

        return ResponseEntity.badRequest().body(response);
    }

}
