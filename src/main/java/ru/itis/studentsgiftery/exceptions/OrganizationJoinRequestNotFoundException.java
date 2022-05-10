package ru.itis.studentsgiftery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrganizationJoinRequestNotFoundException extends StudentsNotFoundException{
    public OrganizationJoinRequestNotFoundException (String message){
        super(message);
    }
}
