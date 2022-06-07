package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.studentsgiftery.dto.CertificateInstanceDto;
import ru.itis.studentsgiftery.dto.CertificateTemplateDto;
import ru.itis.studentsgiftery.dto.forms.CertificateTemplateForm;
import ru.itis.studentsgiftery.dto.mapper.CertificateMapper;
import ru.itis.studentsgiftery.exceptions.*;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.Brand;
import ru.itis.studentsgiftery.models.CertificateInstance;
import ru.itis.studentsgiftery.models.CertificateTemplate;
import ru.itis.studentsgiftery.repositories.AccountsRepository;
import ru.itis.studentsgiftery.repositories.BrandsRepository;
import ru.itis.studentsgiftery.repositories.CertificateInstancesRepository;
import ru.itis.studentsgiftery.repositories.CertificateTemplatesRepository;
import ru.itis.studentsgiftery.services.BalanceService;
import ru.itis.studentsgiftery.services.CertificatesService;
import ru.itis.studentsgiftery.services.SecurityService;
import ru.itis.studentsgiftery.util.EmailUtil;

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
    private final SecurityService securityService;

    @Transactional
    @Override
    public CertificateTemplateDto addCertificateTemplateToBrand(Long brandId, CertificateTemplateForm certificateForm) {
        Account account = securityService.getAuthorizedAccount();
        Brand brand = brandsRepository.findById(brandId).orElseThrow((Supplier<RuntimeException>) ()
                -> new BrandNotFoundException("Brand not found")
        );

        if(!account.getRole().equals(Account.Role.ORGANIZATION) || !account.getOrganization().getId().equals(brand.getOrganization().getId())) {
            throw new ForbiddenException("this user is not from that organization or not organization");
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
        Account account = securityService.getAuthorizedAccount();

        balanceService.purchaseOperation(account, certificateTemplateId);

        CertificateTemplate certificateTemplate = certificateTemplatesRepository.findById(certificateTemplateId)
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new CertificateNotFoundException("Certificate not found")
                );

        CertificateInstance certificateInstance = CertificateInstance.builder()
                .state(CertificateInstance.State.NOT_ACTIVATED)
                .code(UUID.randomUUID().toString())
                .amount(certificateTemplate.getAmount())
                .account(account)
                .certificateTemplate(certificateTemplate)
                .build();

        return certificateMapper.toCertificateInstanceDto(certificateInstancesRepository.save(certificateInstance));
    }

    @Transactional
    @Override
    public CertificateInstanceDto buyCertificateAsGift(Long certificateTemplateId, Long accountId) {
        Account account = securityService.getAuthorizedAccount();
        Account friendAccount = accountsRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("no such account"));

        balanceService.purchaseOperation(account, certificateTemplateId);

        CertificateTemplate certificateTemplate = certificateTemplatesRepository.findById(certificateTemplateId)
                .orElseThrow(() -> new CertificateNotFoundException("no such certificate"));


        CertificateInstance certificateInstance = CertificateInstance.builder()
                .state(CertificateInstance.State.NOT_ACTIVATED)
                .code(UUID.randomUUID().toString())
                .amount(certificateTemplate.getAmount())
                .account(friendAccount)
                .certificateTemplate(certificateTemplate)
                .build();


        emailUtil.sendGiftNoticeMail(friendAccount.getEmail(), "Someone just gave you a certificate",
                "giftNotificationMail.ftlh", account.getEmail(), friendAccount.getFirstName(),
                friendAccount.getLastName(), certificateTemplate.getBrand().getBrandName());

        return certificateMapper.toCertificateInstanceDto(certificateInstancesRepository.save(certificateInstance));
    }

    @Override
    public CertificateInstanceDto spendCertificate(Long certificateInstanceId, Long purchasePrice) {
        CertificateInstance certificateInstance = certificateInstancesRepository.findById(certificateInstanceId).orElseThrow(() -> new CertificateNotFoundException("CertificateInstance not found for this id"));
        if (certificateInstance.getAmount().equals(purchasePrice)) {
            certificateInstance.setAmount(0L);
            certificateInstance.setState(CertificateInstance.State.ACTIVATED);
            certificateInstancesRepository.save(certificateInstance);
            return certificateMapper.toCertificateInstanceDto(certificateInstance);
        } else if (certificateInstance.getAmount() > purchasePrice) {
            certificateInstance.setAmount(certificateInstance.getAmount() - purchasePrice);
            certificateInstancesRepository.save(certificateInstance);
            return certificateMapper.toCertificateInstanceDto(certificateInstance);
        } else {
            throw new LowBalanceException("Not enough money to make this purchase");
        }
    }

    @Override
    public CertificateTemplateDto deleteCertificateTemplate(Long certificateTemplateId) {
        CertificateTemplate certificateTemplate = certificateTemplatesRepository.findById(certificateTemplateId)
                .orElseThrow(()
                -> new CertificateNotFoundException("Certificate not found")
        );
        certificateTemplate.setState(CertificateTemplate.State.DELETED);

        certificateTemplatesRepository.save(certificateTemplate);

        return certificateMapper.toCertificateTemplateDto(certificateTemplate);
    }
}
