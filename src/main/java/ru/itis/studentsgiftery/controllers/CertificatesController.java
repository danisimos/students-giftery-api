package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.CertificatesApi;
import ru.itis.studentsgiftery.dto.CertificateInstanceDto;
import ru.itis.studentsgiftery.dto.CertificateTemplateDto;
import ru.itis.studentsgiftery.dto.forms.CertificateTemplateForm;
import ru.itis.studentsgiftery.services.CertificatesService;

@RequiredArgsConstructor
@RestController
public class CertificatesController implements CertificatesApi {
    private final CertificatesService certificatesService;

    @Override
    public ResponseEntity<CertificateInstanceDto> buyCertificate(Long certificateId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(certificatesService.buyCertificate(certificateId));
    }

    @Override
    public ResponseEntity<CertificateInstanceDto> buyCertificateAsGift(Long certificateId, Long accountId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(certificatesService.buyCertificateAsGift(certificateId, accountId));
    }

    @Override
    public ResponseEntity<CertificateInstanceDto> spendCertificate(Long certificateId, Long purchasePrice) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(certificatesService.spendCertificate(certificateId, purchasePrice));
    }

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
    public ResponseEntity<CertificateTemplateDto> deleteCertificateTemplate(Long brandId, Long certificateTemplateId) {
        return ResponseEntity.status(HttpStatus.OK).body(certificatesService.deleteCertificateTemplate(certificateTemplateId));
    }
}
