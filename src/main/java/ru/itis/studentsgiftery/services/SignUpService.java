package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.forms.SignUpForm;

public interface SignUpService {
    AccountDto signUp(SignUpForm signUpForm);

    void checkConfirm(String confirmCode);

}
