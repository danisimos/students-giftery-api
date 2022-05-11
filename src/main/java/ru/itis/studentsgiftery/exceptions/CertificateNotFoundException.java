package ru.itis.studentsgiftery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CertificateNotFoundException extends StudentsNotFoundException {
    public CertificateNotFoundException (String message){
        super(message);
    }
}
