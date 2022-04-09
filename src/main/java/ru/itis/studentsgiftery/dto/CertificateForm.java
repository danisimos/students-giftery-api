package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateForm {
    private Integer value;
    public Long brandId;
}
