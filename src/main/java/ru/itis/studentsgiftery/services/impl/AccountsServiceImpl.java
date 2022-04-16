package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.CertificateDto;
import ru.itis.studentsgiftery.dto.SignUpForm;
import ru.itis.studentsgiftery.dto.mapper.AccountMapper;
import ru.itis.studentsgiftery.exceptions.AccountNotFoundException;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.Certificate;
import ru.itis.studentsgiftery.repositories.AccountsRepository;
import ru.itis.studentsgiftery.services.AccountsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto getAccount(Long id) {
        Account account = accountsRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        return accountMapper.toAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountMapper.toAccountDtoList(accountsRepository.findAll());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountsRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        account.setState(Account.State.DELETED);

        accountsRepository.save(account);
    }
}
