package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.forms.SignUpForm;
import ru.itis.studentsgiftery.dto.mapper.AccountMapper;
import ru.itis.studentsgiftery.exceptions.AccountNotFoundException;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.repositories.AccountsRepository;
import ru.itis.studentsgiftery.services.SignUpService;
import ru.itis.studentsgiftery.util.EmailUtil;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailUtil emailUtil;

    @Override
    public AccountDto signUp(SignUpForm signUpForm) {
        Account newAccount = Account.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .confirmCode(UUID.randomUUID().toString())
                .state(Account.State.NOT_CONFIRMED)
                .role(Account.Role.USER)
                .build();

        accountsRepository.save(newAccount);

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("first_name", newAccount.getFirstName());
        templateData.put("last_name", newAccount.getLastName());
        templateData.put("confirm_code", newAccount.getConfirmCode());
        templateData.put("email", newAccount.getEmail());

        emailUtil.sendMail(newAccount.getEmail(), "Для завершения регистрации нажмите кнопку в письме", "confirmMail.ftlh", templateData);

        return accountMapper.toAccountDto(newAccount);
    }

    @Override
    public void checkConfirm(String confirmCode) {
        Account account = accountsRepository.findAccountByConfirmCode(confirmCode).orElseThrow(AccountNotFoundException::new);
        if (account.getState().equals(Account.State.NOT_CONFIRMED)) {
            account.setState(Account.State.CONFIRMED);
            accountsRepository.save(account);
        }
    }
}

