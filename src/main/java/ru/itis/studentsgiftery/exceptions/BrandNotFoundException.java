package ru.itis.studentsgiftery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BrandNotFoundException extends StudentsNotFoundException {
    public BrandNotFoundException (String message){
        super(message);
    }
}
