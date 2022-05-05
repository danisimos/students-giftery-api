package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.exceptions.LowBalanceException;
import ru.itis.studentsgiftery.models.Account;

public interface BalanceService {
    Integer getAccountBalance(Long accountId);

    AccountDto addBalance(Long accountId, Integer amount);

    AccountDto purchaseOperation(Account account, Long certificateId);
}
