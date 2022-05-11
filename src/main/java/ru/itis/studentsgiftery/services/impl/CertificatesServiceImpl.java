package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.studentsgiftery.dto.CertificateInstanceDto;
import ru.itis.studentsgiftery.dto.CertificateTemplateDto;
import ru.itis.studentsgiftery.dto.forms.CertificateTemplateForm;
import ru.itis.studentsgiftery.dto.mapper.CertificateMapper;
import ru.itis.studentsgiftery.exceptions.AccountNotFoundException;
import ru.itis.studentsgiftery.exceptions.BrandNotFoundException;
import ru.itis.studentsgiftery.exceptions.CertificateNotFoundException;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.Brand;
import ru.itis.studentsgiftery.models.CertificateInstance;
import ru.itis.studentsgiftery.models.CertificateTemplate;
import ru.itis.studentsgiftery.repositories.AccountsRepository;
import ru.itis.studentsgiftery.repositories.BrandsRepository;
import ru.itis.studentsgiftery.repositories.CertificateInstancesRepository;
import ru.itis.studentsgiftery.repositories.CertificateTemplatesRepository;
import ru.itis.studentsgiftery.security.details.AccountUserDetails;
import ru.itis.studentsgiftery.services.BalanceService;
import ru.itis.studentsgiftery.services.CertificatesService;
import ru.itis.studentsgiftery.util.EmailUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CertificatesServiceImpl implements CertificatesService {
    private final BrandsRepository brandsRepository;
    private final AccountsRepository accountsRepository;
    private final CertificateTemplatesRepository certificateTemplatesRepository;
    private final CertificateInstancesRepository certificateInstancesRepository;
    private final EmailUtil emailUtil;
    private final BalanceService balanceService;
    private final CertificateMapper certificateMapper;

    @Transactional
    @Override
    public CertificateTemplateDto addCertificateTemplateToBrand(Long brandId, CertificateTemplateForm certificateForm) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();
        Brand brand = brandsRepository.findById(brandId).orElseThrow((Supplier<RuntimeException>) ()
                -> new BrandNotFoundException("Brand not found")
        );

        if(!account.getOrganization().getId().equals(brand.getOrganization().getId())) {
            return null;
        }

        CertificateTemplate certificateTemplate = CertificateTemplate.builder()
                .amount(certificateForm.getAmount())
                .brand(brand)
                .description(certificateForm.getDescription())
                .state(CertificateTemplate.State.ACTIVE)
                .type(CertificateTemplate.Type.valueOf(certificateForm.getType().name()))
                .build();

        certificateTemplatesRepository.save(certificateTemplate);

        return certificateMapper.toCertificateTemplateDto(certificateTemplate);
    }

    @Transactional
    @Override
    public CertificateInstanceDto buyCertificate(Long certificateTemplateId) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();

        balanceService.purchaseOperation(account, certificateTemplateId);

        CertificateTemplate certificateTemplate = certificateTemplatesRepository.findById(certificateTemplateId)
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new CertificateNotFoundException("Certificate not found")
                );

        CertificateInstance certificateInstance = CertificateInstance.builder()
                .state(CertificateInstance.State.NOT_ACTIVATED)
                .code(UUID.randomUUID().toString())
                .account(account)
                .certificateTemplate(certificateTemplate)
                .build();

        account.getCertificateInstances().add(certificateInstance);
        certificateTemplate.getCertificateInstances().add(certificateInstance);

        accountsRepository.save(account);
        certificateTemplatesRepository.save(certificateTemplate);

        return certificateMapper.toCertificateInstanceDto(certificateInstancesRepository.save(certificateInstance));
    }

    @Transactional
    @Override
    public CertificateInstanceDto buyCertificateAsGift(Long certificateTemplateId, Long accountId) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();
        Account friendAccount = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);

        balanceService.purchaseOperation(account, certificateTemplateId);

        CertificateTemplate certificateTemplate = certificateTemplatesRepository.findById(certificateTemplateId)
                .orElseThrow(CertificateNotFoundException::new);

        CertificateInstance certificateInstance = CertificateInstance.builder()
                .state(CertificateInstance.State.NOT_ACTIVATED)
                .code(UUID.randomUUID().toString())
                .account(friendAccount)
                .certificateTemplate(certificateTemplate)
                .build();

        friendAccount.getCertificateInstances().add(certificateInstance);
        certificateTemplate.getCertificateInstances().add(certificateInstance);

        accountsRepository.save(friendAccount);
        certificateTemplatesRepository.save(certificateTemplate);

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("first_name", friendAccount.getFirstName());
        templateData.put("last_name", friendAccount.getLastName());
        templateData.put("email", account.getEmail());//if we don't want to show buyer email delete this line
        templateData.put("brand", certificateTemplate.getBrand());

        emailUtil.sendMail(friendAccount.getEmail(), "Someone just gave you a certificate", "giftNotificationMail.ftlh", templateData);

        return certificateMapper.toCertificateInstanceDto(certificateInstancesRepository.save(certificateInstance));
    }
}
