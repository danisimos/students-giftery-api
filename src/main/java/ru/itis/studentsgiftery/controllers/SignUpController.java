package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.SignUpForm;
import ru.itis.studentsgiftery.services.SignUpService;

@RequiredArgsConstructor
@RestController
public class SignUpController {

    private final SignUpService signUpService;

    //TODO: add swagger and remember user using security
    @PostMapping("/signUp")
    public ResponseEntity<AccountDto> signUp(SignUpForm signUpForm) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(signUpService.signUp(signUpForm));
    }

    @GetMapping("/confirm/{confirmCode}")
    public ResponseEntity<?> checkConfirmCode(@PathVariable("confirmCode") String confirmCode) {
        signUpService.checkConfirm(confirmCode);
        //TODO: Add swagger and ...
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

