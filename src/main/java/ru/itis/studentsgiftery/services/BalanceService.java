package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.exceptions.LowBalanceException;

public interface BalanceService {
    Integer getAccountBalance(Long accountId);

    AccountDto addBalance(Long accountId, Integer amount);

    AccountDto purchaseOperation(Long accountId, Long certificateId) throws LowBalanceException;
}
