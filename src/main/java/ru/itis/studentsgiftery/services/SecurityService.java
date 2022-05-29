package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.models.Account;

public interface SecurityService {
    Account getAuthorizedAccount();
}
