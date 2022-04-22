package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.CertificateTemplateDto;
import ru.itis.studentsgiftery.dto.forms.CertificateTemplateForm;
import ru.itis.studentsgiftery.models.Account;

public interface CertificatesService {
    CertificateTemplateDto addCertificateTemplateToBrand(Long brandId, CertificateTemplateForm certificateForm, Account account);
}
