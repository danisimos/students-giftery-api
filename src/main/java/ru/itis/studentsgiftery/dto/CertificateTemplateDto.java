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

    public enum Type {
        PRODUCT, DISCOUNT, BONUS
    }

    private Long id;
    private Integer amount;
    private String description;
    private Long brandId;

    private State state;
    private Type type;
}
