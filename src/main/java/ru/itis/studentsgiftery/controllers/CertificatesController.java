package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.studentsgiftery.dto.CertificateInstanceDto;
import ru.itis.studentsgiftery.services.CertificatesService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students-giftery")
public class CertificatesController {
    private final CertificatesService certificatesService;

    @PostMapping("/certificates/{certificate-id}/buy/")
    public ResponseEntity<CertificateInstanceDto> buyCertificate(@PathVariable(name = "certificate-id") Long certificateTemplateId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(certificatesService.buyCertificate(certificateTemplateId));
    }

    //TODO: add to swagger
    @PostMapping("/certificates/{certificate-id}/buyAsGift/{accountId}")
    public ResponseEntity<CertificateInstanceDto> buyCertificateAsGift(@PathVariable(name = "certificate-id") Long certificateTemplateId,
                                                                       @PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(certificatesService.buyCertificateAsGift(certificateTemplateId, accountId));
    }
}
