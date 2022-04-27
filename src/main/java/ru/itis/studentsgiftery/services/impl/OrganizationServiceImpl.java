package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.dto.OrganizationJoinRequestDto;
import ru.itis.studentsgiftery.dto.forms.OrganizationForm;
import ru.itis.studentsgiftery.dto.mapper.AccountMapper;
import ru.itis.studentsgiftery.dto.mapper.OrganizationJoinRequestMapper;
import ru.itis.studentsgiftery.dto.mapper.OrganizationMapper;
import ru.itis.studentsgiftery.exceptions.OrganizationNotFoundException;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.Organization;
import ru.itis.studentsgiftery.models.OrganizationJoinRequest;
import ru.itis.studentsgiftery.repositories.AccountsRepository;
import ru.itis.studentsgiftery.repositories.OrganizationJoinRequestRepository;
import ru.itis.studentsgiftery.repositories.OrganizationsRepository;
import ru.itis.studentsgiftery.security.details.AccountUserDetails;
import ru.itis.studentsgiftery.services.OrganizationService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationsRepository organizationsRepository;
    private final AccountsRepository accountsRepository;
    private final OrganizationJoinRequestRepository organizationJoinRequestRepository;
    private final AccountMapper accountMapper;
    private final OrganizationMapper organizationMapper;
    private final OrganizationJoinRequestMapper organizationJoinRequestMapper;

    @Override
    public OrganizationDto createOrganization(OrganizationForm organizationForm) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();
        if(account.getRole().equals(Account.Role.ORGANIZATION)) return null;

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

    @Override
    public OrganizationJoinRequestDto addOrganizationJoinRequest(Long organizationId) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();
        Organization organization = organizationsRepository.findById(organizationId).orElseThrow(OrganizationNotFoundException::new);

        if(account.getRole().equals(Account.Role.ORGANIZATION)
                || account.getOrganization().getId().equals(organizationId)) return null;

        OrganizationJoinRequest joinRequest = OrganizationJoinRequest.builder()
                .organization(organization)
                .account(account)
                .state(OrganizationJoinRequest.State.SENT)
                .build();

        OrganizationJoinRequestDto joinRequestDto = organizationJoinRequestMapper
                .toOrganizationJoinRequestDto(organizationJoinRequestRepository.save(joinRequest));

        if(organization.getJoinRequests() == null) {
            organization.setJoinRequests(Collections.singletonList(joinRequest));
        } else {
            organization.getJoinRequests().add(joinRequest);
        }

        if(account.getJoinRequests() == null) {
            account.setJoinRequests(Collections.singletonList(joinRequest));
        } else {
            System.out.println(account);
            System.out.println(account.getJoinRequests());
            account.getJoinRequests().add(joinRequest);
        }

        organizationsRepository.save(organization);
        accountsRepository.save(account);

        return joinRequestDto;
    }

    @Override
    public OrganizationJoinRequestDto confirmOrganizationJoinRequest(Long requestId) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();
        OrganizationJoinRequest joinRequest = organizationJoinRequestRepository.findById(requestId)
                .orElseThrow(OrganizationNotFoundException::new);

        if(account.getRole().equals(Account.Role.ORGANIZATION)
                && account.getOrganization().getId().equals(joinRequest.getOrganization().getId())
                && joinRequest.getState().equals(OrganizationJoinRequest.State.SENT)) {
            Account joinRequestAccount = joinRequest.getAccount();
            Organization organization = joinRequest.getOrganization();

            joinRequestAccount.setOrganization(organization);
            joinRequestAccount.setRole(Account.Role.ORGANIZATION);

            organization.getAccounts().add(joinRequestAccount);

            joinRequest.setState(OrganizationJoinRequest.State.CONFIRMED);

            organizationJoinRequestRepository.save(joinRequest);
            accountsRepository.save(joinRequestAccount);
            organizationsRepository.save(organization);

            return organizationJoinRequestMapper.toOrganizationJoinRequestDto(joinRequest);
        } else {
            return null;
        }
    }

    @Override
    public OrganizationJoinRequestDto denyOrganizationJoinRequest(Long requestId) {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();
        OrganizationJoinRequest joinRequest = organizationJoinRequestRepository.findById(requestId)
                .orElseThrow(OrganizationNotFoundException::new);

        if(account.getRole().equals(Account.Role.ORGANIZATION)
                && account.getOrganization().getId().equals(joinRequest.getOrganization().getId())
                && joinRequest.getState().equals(OrganizationJoinRequest.State.SENT)) {
            joinRequest.setState(OrganizationJoinRequest.State.DENIED);

            organizationJoinRequestRepository.save(joinRequest);

            return organizationJoinRequestMapper.toOrganizationJoinRequestDto(joinRequest);
        } else {
            return null;
        }
    }

    @Override
    public AccountDto unjoinFromOrganization() {
        Account account = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();
        account.setOrganization(null);
        account.setRole(Account.Role.USER);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }
}
