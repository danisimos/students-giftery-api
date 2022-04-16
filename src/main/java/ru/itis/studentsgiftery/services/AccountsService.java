package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.AccountDto;

import java.util.List;

public interface AccountsService {
    AccountDto getAccount(Long id);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    List<AccountDto> getByOrganization(Long organizationId);

    List<AccountDto> getAccounts();
}
