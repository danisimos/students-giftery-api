package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandForm {
    private String brandName;
    private String description;
    private String avatarLink;
}
