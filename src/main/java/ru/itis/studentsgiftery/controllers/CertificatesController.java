package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.studentsgiftery.services.CertificatesService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/certificates")
public class CertificatesController {
    private final CertificatesService certificatesService;
}
