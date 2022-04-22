package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.studentsgiftery.dto.CertificateTemplateDto;
import ru.itis.studentsgiftery.dto.forms.CertificateTemplateForm;
import ru.itis.studentsgiftery.dto.mapper.CertificateMapper;
import ru.itis.studentsgiftery.exceptions.BrandNotFoundException;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.Brand;
import ru.itis.studentsgiftery.models.CertificateTemplate;
import ru.itis.studentsgiftery.repositories.BrandsRepository;
import ru.itis.studentsgiftery.repositories.CertificateTemplatesRepository;
import ru.itis.studentsgiftery.services.CertificatesService;

@Service
@RequiredArgsConstructor
public class CertificatesServiceImpl implements CertificatesService {
    private final BrandsRepository brandsRepository;

    private final CertificateTemplatesRepository certificateTemplatesRepository;
    private final CertificateMapper certificateMapper;

    @Override
    public CertificateTemplateDto addCertificateTemplateToBrand(Long brandId, CertificateTemplateForm certificateForm, Account account) {
        Brand brand = brandsRepository.findById(brandId).orElseThrow(BrandNotFoundException::new);

        if(!account.getOrganization().getId().equals(brand.getOrganization().getId())) {
            return null;
        }

        CertificateTemplate certificateTemplate = CertificateTemplate.builder()
                .amount(certificateForm.getAmount())
                .brand(brand)
                .state(CertificateTemplate.State.ACTIVE)
                .build();

        certificateTemplatesRepository.save(certificateTemplate);

        return certificateMapper.toCertificateTemplateDto(certificateTemplate);
    }
}
