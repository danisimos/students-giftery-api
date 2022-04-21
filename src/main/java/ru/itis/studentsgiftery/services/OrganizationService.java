package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.dto.OrganizationJoinRequestDto;
import ru.itis.studentsgiftery.dto.forms.OrganizationForm;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.security.details.AccountUserDetails;

import java.util.List;

public interface OrganizationService {
    OrganizationDto createOrganization(OrganizationForm organizationForm, Account account);

    List<OrganizationDto> getOrganizations();

    OrganizationJoinRequestDto addOrganizationJoinRequest(Long organizationId, Account account);

    OrganizationJoinRequestDto confirmOrganizationJoinRequest(Long requestId, Account account);

    OrganizationJoinRequestDto denyOrganizationJoinRequest(Long requestId, Account account);

    AccountDto unjoinFromOrganization(Account account);
}
