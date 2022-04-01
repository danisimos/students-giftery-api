package ru.itis.studentsgiftery.dto;

import lombok.Data;

@Data
public class SignUpForm {
    private String firstName;
    private String lastname;
    private String email;
    private String password;
}
