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

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {
    private final AccountsRepository accountsRepository;
    private final CertificateTemplatesRepository certificateTemplatesRepository;
    private final AccountMapper accountMapper;

    @Override
    public Long getAccountBalance(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow((Supplier<RuntimeException>) ()
                -> new AccountNotFoundException("Account not found")
        );
        return account.getBalance();
    }

    @Override
    public AccountDto addBalance(Long accountId, Integer amount) {
        Account account = accountsRepository.findById(accountId).orElseThrow((Supplier<RuntimeException>) ()
                -> new AccountNotFoundException("Account not found")
        );
        account.setBalance(account.getBalance() + amount);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }

    @Override
    public AccountDto purchaseOperation(Account account, Long certificateId){
        CertificateTemplate certificate = certificateTemplatesRepository.findById(certificateId).orElseThrow((Supplier<RuntimeException>) ()
                -> new CertificateNotFoundException("Certificate not found")
        );

        if (account.getBalance() >= certificate.getAmount()){
            account.setBalance(account.getBalance() - certificate.getAmount());
        } else {
            throw new LowBalanceException("Low balance");
        }

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }
}
