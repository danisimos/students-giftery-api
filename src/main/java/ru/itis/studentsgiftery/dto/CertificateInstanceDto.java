package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateInstanceDto {
    public enum State {
        ACTIVATED, NOT_ACTIVATED
    }

    private Long id;
    private String code;
    private Long accountId;
    private Long certificateTemplateId;
    private Integer amount;

    private State state;
}
