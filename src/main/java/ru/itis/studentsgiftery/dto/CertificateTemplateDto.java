package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.studentsgiftery.models.Brand;
import ru.itis.studentsgiftery.models.CertificateInstance;
import ru.itis.studentsgiftery.models.CertificateTemplate;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateTemplateDto {
    public enum State {
        ACTIVE, DELETED
    }

    private Long id;
    private Integer amount;
    private Long brandId;

    private State state;
}
