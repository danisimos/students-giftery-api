package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String firstName;
    private String lastName;
    private String email;

    public static List<CertificateDto> certificates;
}
