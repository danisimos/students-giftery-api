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
public class BrandDto {
    private String brandName;
    private String description;
    private Integer avatarId;

    public static List<CertificateDto> certificates;
}
