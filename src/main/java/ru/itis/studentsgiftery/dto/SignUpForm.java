package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    private String firstName;
    private String lastname;
    private String email;
    private String password;
}
