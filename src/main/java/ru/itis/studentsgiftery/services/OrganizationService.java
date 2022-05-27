package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.dto.OrganizationJoinRequestDto;
import ru.itis.studentsgiftery.dto.forms.OrganizationForm;

import java.util.List;

public interface OrganizationService {
    OrganizationDto createOrganization(OrganizationForm organizationForm);

    List<OrganizationDto> getOrganizations();

    OrganizationJoinRequestDto addOrganizationJoinRequest(Long organizationId);

    OrganizationJoinRequestDto confirmOrganizationJoinRequest(Long requestId);

    OrganizationJoinRequestDto denyOrganizationJoinRequest(Long requestId);

    AccountDto unjoinFromOrganization();

    OrganizationDto deleteOrganization(Long organizationId);
}
