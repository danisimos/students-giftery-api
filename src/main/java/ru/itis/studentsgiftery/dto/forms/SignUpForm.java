package ru.itis.studentsgiftery.dto.forms;

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
    private String lastName;
    private String email;
    private String password;
}
