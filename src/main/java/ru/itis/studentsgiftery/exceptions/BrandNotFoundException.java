package ru.itis.studentsgiftery.exceptions;

public class BrandNotFoundException extends StudentsNotFoundException {
    public BrandNotFoundException (String message){
        super(message);
    }
}
