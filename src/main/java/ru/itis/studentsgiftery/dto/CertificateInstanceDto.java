package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.CertificateInstance;
import ru.itis.studentsgiftery.models.CertificateTemplate;

import javax.persistence.*;

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

    private State state;
}
