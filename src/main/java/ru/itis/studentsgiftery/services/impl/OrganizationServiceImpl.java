package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.dto.forms.OrganizationForm;
import ru.itis.studentsgiftery.dto.mapper.OrganizationMapper;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.Organization;
import ru.itis.studentsgiftery.repositories.AccountsRepository;
import ru.itis.studentsgiftery.repositories.OrganizationsRepository;
import ru.itis.studentsgiftery.services.OrganizationService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationsRepository organizationsRepository;
    private final AccountsRepository accountsRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public OrganizationDto createOrganization(OrganizationForm organizationForm, Account account) {
        Organization organization = Organization.builder()
                .name(organizationForm.getName())
                .description(organizationForm.getDescription())
                .accounts(Collections.singletonList(account))
                .build();

        OrganizationDto organizationDto = organizationMapper.toOrganizationDto(organizationsRepository.save(organization));

        account.setOrganization(organization);
        account.setRole(Account.Role.ORGANIZATION);

        accountsRepository.save(account);

        return organizationDto;
    }

    @Override
    public List<OrganizationDto> getOrganizations() {
        return organizationMapper.toOrganizationDtoList(organizationsRepository.findAll());
    }
}
