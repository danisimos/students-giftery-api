package ru.itis.studentsgiftery.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

public class AccountNotFoundException extends StudentsNotFoundException {

    public AccountNotFoundException (String message){
        super(message);
    }

}
