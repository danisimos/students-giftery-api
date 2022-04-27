package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itis.api.BrandsApi;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.dto.CertificateTemplateDto;
import ru.itis.studentsgiftery.dto.forms.BrandForm;
import ru.itis.studentsgiftery.dto.forms.CertificateTemplateForm;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.security.details.AccountUserDetails;
import ru.itis.studentsgiftery.services.BrandsService;
import ru.itis.studentsgiftery.services.CertificatesService;

@RequiredArgsConstructor
@RestController
public class BrandsController implements BrandsApi {
    private final BrandsService brandsService;
    private final CertificatesService certificatesService;

    @Override
    public ResponseEntity<CertificateTemplateDto> addCertificateTemplate(Long brandId, CertificateTemplateForm certificateForm) {
        CertificateTemplateDto certificateTemplateDto =
                certificatesService.addCertificateTemplateToBrand(brandId, certificateForm);

        if(certificateTemplateDto == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(certificateTemplateDto);
    }

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
}
