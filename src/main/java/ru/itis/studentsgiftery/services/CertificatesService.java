package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.CertificateDto;
import ru.itis.studentsgiftery.dto.CertificateForm;

import java.util.List;

public interface CertificatesService {
    CertificateDto saveCertificate(CertificateForm certificateForm);

    CertificateDto getCertificate(Long id);

    List<CertificateDto> getAllCertificates(Long brandId);

    CertificateDto updateCertificate(Long id, CertificateForm newData);

    void deleteCertificate(Long id);
}
