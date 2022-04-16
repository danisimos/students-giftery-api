package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.SignUpForm;
import ru.itis.studentsgiftery.models.Account;

import java.util.Optional;

public interface SignUpService {
    AccountDto signUp(SignUpForm signUpForm);

    void checkConfirm(String confirmCode);

}
