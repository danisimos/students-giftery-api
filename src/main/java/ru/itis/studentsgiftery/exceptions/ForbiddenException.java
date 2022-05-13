package ru.itis.studentsgiftery.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException (String message){
        super(message);
    }
}
