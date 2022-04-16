package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.dto.forms.OrganizationForm;
import ru.itis.studentsgiftery.models.Account;

import java.util.List;

public interface OrganizationService {
    OrganizationDto createOrganization(OrganizationForm organizationForm, Account account);

    List<OrganizationDto> getOrganizations();
}
