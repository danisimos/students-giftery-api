package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
