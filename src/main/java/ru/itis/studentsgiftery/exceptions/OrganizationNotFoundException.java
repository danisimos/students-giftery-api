package ru.itis.studentsgiftery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrganizationNotFoundException extends StudentsNotFoundException{
    public  OrganizationNotFoundException (String message){
        super(message);
    }
}
