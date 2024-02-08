package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.exceptions.BadRequestException;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
