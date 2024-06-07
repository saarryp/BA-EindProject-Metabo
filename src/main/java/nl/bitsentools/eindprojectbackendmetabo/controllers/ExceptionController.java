package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.exceptions.BadRequestException;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.UsernameNotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value= RecordNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object>RecordNotFound(RecordNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= BadRequestException.class)
    public ResponseEntity<Object>BadRequest(BadRequestException badRequestException){
        return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object>UsernameNotFoundException(UsernameNotFoundException usernameNotFoundException)
    {
        return new ResponseEntity<>(usernameNotFoundException.getMessage(), HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(value = PSQLException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object>PSQLexception(PSQLException psqlException){
        return new ResponseEntity<>(psqlException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object>handleBadCredentials(BadCredentialsException badCredentials) {
        return new ResponseEntity<>(badCredentials.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object>handleBadValidations(MethodArgumentNotValidException methodArgumentNotValidException){
        return new ResponseEntity<>(methodArgumentNotValidException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IOException.class)
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleProblem(IOException ioException) {
        return new ResponseEntity<>(ioException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
