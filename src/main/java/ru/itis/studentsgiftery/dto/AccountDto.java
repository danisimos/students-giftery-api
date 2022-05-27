package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.studentsgiftery.validation.annotations.NotSameNames;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotSameNames(names = {"firstName", "lastName"}, message = "{names} are same")
public class AccountDto {
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 20, message = "Максимальный размер имени - {max}")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(max = 20, message = "Максимальный размер Фамилии - {max}")
    private String lastName;

    @NotBlank(message = "Email не может быть пустым")
    @Email
    private String email;
    private Integer balance;
}
