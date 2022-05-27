package ru.itis.studentsgiftery.exceptions;

public class AccountNotFoundException extends StudentsNotFoundException {

    public AccountNotFoundException (String message){
        super(message);
    }

}
