package ru.itis.studentsgiftery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentsNotFoundException extends RuntimeException{
     public StudentsNotFoundException(String message) {
          super(message);
     }
}
