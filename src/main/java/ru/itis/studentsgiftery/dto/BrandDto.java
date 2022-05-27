package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    private Long id;
    @NotBlank
    private String brandName;
    @NotBlank
    private String description;
    private String avatarLink;

    private List<Long> listCertificateId;
}
