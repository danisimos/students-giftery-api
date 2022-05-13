package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.CertificatesApi;
import ru.itis.studentsgiftery.dto.CertificateInstanceDto;
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
}
