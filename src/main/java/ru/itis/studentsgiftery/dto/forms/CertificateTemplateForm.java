package ru.itis.studentsgiftery.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateTemplateForm {
    public enum Type {
        PRODUCT, DISCOUNT, BONUS
    }

    private Long amount;
    private String description;
    private Type type;
}
