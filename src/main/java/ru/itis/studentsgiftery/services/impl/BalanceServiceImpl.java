package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.mapper.AccountMapper;
import ru.itis.studentsgiftery.exceptions.AccountNotFoundException;
import ru.itis.studentsgiftery.exceptions.CertificateNotFoundException;
import ru.itis.studentsgiftery.exceptions.LowBalanceException;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.CertificateTemplate;
import ru.itis.studentsgiftery.repositories.AccountsRepository;
import ru.itis.studentsgiftery.repositories.CertificateTemplatesRepository;
import ru.itis.studentsgiftery.services.BalanceService;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {
    private final AccountsRepository accountsRepository;
    private final CertificateTemplatesRepository certificateTemplatesRepository;
    private final AccountMapper accountMapper;

    @Override
    public Integer getAccountBalance(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        return account.getBalance();
    }

    @Override
    public AccountDto addBalance(Long accountId, Integer amount) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        account.setBalance(account.getBalance() + amount);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }

    @Override
    public AccountDto purchaseOperation(Long accountId, Long certificateId){
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        CertificateTemplate certificate = certificateTemplatesRepository.findById(certificateId).orElseThrow(CertificateNotFoundException::new);

        if (account.getBalance() >= certificate.getAmount()){
            account.setBalance(account.getBalance() - certificate.getAmount());
        } else {
            throw new LowBalanceException();
        }

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }
}
