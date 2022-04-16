package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.SignUpForm;

import java.util.List;

public interface AccountsService {
    AccountDto getAccount(Long id);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

}
