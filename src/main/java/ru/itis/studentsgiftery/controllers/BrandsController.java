package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.BrandsApi;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.dto.forms.BrandForm;
import ru.itis.studentsgiftery.dto.forms.CertificateTemplateForm;
import ru.itis.studentsgiftery.services.BrandsService;
import ru.itis.studentsgiftery.services.CertificatesService;

@RequiredArgsConstructor
@RestController
public class BrandsController implements BrandsApi {
    private final BrandsService brandsService;
    private final CertificatesService certificatesService;

    @Override
    public ResponseEntity<BrandDto> createBrand(BrandForm brandForm) {
        BrandDto brandDto = brandsService.createBrand(brandForm);

        if(brandDto == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(brandDto);
        }
    }

    @Override
    public ResponseEntity<BrandDto> deleteBrand(Long brandId) {
        return ResponseEntity.status(HttpStatus.OK).body(brandsService.deleteBrand(brandId));
    }
}
