package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.studentsgiftery.dto.CertificateDto;
import ru.itis.studentsgiftery.dto.forms.CertificateForm;
import ru.itis.studentsgiftery.dto.mapper.CertificateMapper;
import ru.itis.studentsgiftery.exceptions.CertificateNotFoundException;
import ru.itis.studentsgiftery.models.Certificate;
import ru.itis.studentsgiftery.repositories.CertificatesRepository;
import ru.itis.studentsgiftery.services.CertificatesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificatesServiceImpl implements CertificatesService {

    private final CertificatesRepository certificatesRepository;
    private final CertificateMapper certificateMapper;

    @Override
    public CertificateDto saveCertificate(CertificateForm certificateForm) {
        Certificate newCertificate = Certificate.builder()
                .value(certificateForm.getValue())
                .state(Certificate.State.ACTIVE)
                .build();

        certificatesRepository.save(newCertificate);

        return certificateMapper.toCertificateDto(newCertificate);
    }

    @Override
    public CertificateDto getCertificate(Long id) {
        Certificate certificate = certificatesRepository.findById(id).orElseThrow(CertificateNotFoundException::new);
        return certificateMapper.toCertificateDto(certificate);
    }

    @Override
    public List<CertificateDto> getAllCertificates(Long brandId) {
        return certificateMapper.toCertificateDtoList(certificatesRepository.findAllByBrandId(brandId));
    }

    @Override
    public CertificateDto updateCertificate(Long id, CertificateForm newData) {
        Certificate certificate = certificatesRepository.findById(id).orElseThrow(CertificateNotFoundException::new);
        certificate.setValue(newData.getValue());

        certificatesRepository.save(certificate);

        return certificateMapper.toCertificateDto(certificate);
    }

    @Override
    public void deleteCertificate(Long id) {
        Certificate certificate = certificatesRepository.findById(id).orElseThrow(CertificateNotFoundException::new);
        certificate.setState(Certificate.State.DELETED);

        certificatesRepository.save(certificate);
    }
}
