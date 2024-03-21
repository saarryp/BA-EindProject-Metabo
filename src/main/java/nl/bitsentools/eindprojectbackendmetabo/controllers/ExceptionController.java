package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.exceptions.BadRequestException;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.IllegalArgumentException;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.UsernameNotFoundException;
import org.apache.catalina.User;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

    //je kan hier meer statussen handmatig aanvullen bijv bad request, dat er 403 er niet bij mag
    //die met dan ook in de exceptionmap aanmaken
    @ExceptionHandler(value= RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object>RecordNotFound(RecordNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= BadRequestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object>BadRequest(BadRequestException badRequestException){
        return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object>IllegalArgument(IllegalArgumentException illegalArgumentException)
    {
    return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object>UsernameNotFoundException(UsernameNotFoundException usernameNotFoundException)
    {
        return new ResponseEntity<>(usernameNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = PSQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object>PSQLexception(PSQLException psqlException){
        return new ResponseEntity<>(psqlException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object>handleBadCredentials(BadCredentialsException badCredentials) {
        return new ResponseEntity<>(badCredentials.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object>handleBadValidations(MethodArgumentNotValidException methodArgumentNotValidException){
        return new ResponseEntity<>(methodArgumentNotValidException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
