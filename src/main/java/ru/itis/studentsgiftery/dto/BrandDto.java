package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    private Long id;
    private String brandName;
    private String description;
    private String avatarLink;

    private List<Long> listCertificateId;
}
