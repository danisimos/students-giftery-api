package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/api/students-giftery/brands/")
public class BrandsController {
    private final BrandsService brandsService;
    private final CertificatesService certificatesService;

    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandForm brandForm,
                                                Authentication authentication) {
        Account account = ((AccountUserDetails) authentication.getCredentials()).getAccount();

        BrandDto brandDto = brandsService.createBrand(brandForm, account);

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

    @PostMapping("/{brand-id}/certificates/")
    public ResponseEntity<CertificateTemplateDto> addCertificateTemplate(@PathVariable(name = "brand-id") Long brandId,
                                                                         @RequestBody CertificateTemplateForm certificateForm,
                                                                         Authentication authentication) {
        Account account = ((AccountUserDetails) authentication.getCredentials()).getAccount();

        CertificateTemplateDto certificateTemplateDto =
                certificatesService.addCertificateTemplateToBrand(brandId, certificateForm, account);

        if(certificateTemplateDto == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(certificateTemplateDto);
    }

}
