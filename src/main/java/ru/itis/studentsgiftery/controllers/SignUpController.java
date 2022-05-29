package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.SignUpApi;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.forms.SignUpForm;
import ru.itis.studentsgiftery.services.SignUpService;

@RequiredArgsConstructor
@RestController
public class SignUpController implements SignUpApi {
    private final SignUpService signUpService;

    @Override
    public ResponseEntity<String> checkConfirmCode(String confirmCode) {
        signUpService.checkConfirm(confirmCode);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
    }

    @Override
    public ResponseEntity<AccountDto> signUp(SignUpForm signUpForm) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(signUpService.signUp(signUpForm));
    }
}

