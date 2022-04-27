package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.forms.SignUpForm;
import ru.itis.studentsgiftery.services.SignUpService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students-giftery/")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("signUp")
    public ResponseEntity<AccountDto> signUp(@RequestBody SignUpForm signUpForm) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(signUpService.signUp(signUpForm));
    }

    @GetMapping("confirm/{confirmCode}")
    public ResponseEntity<?> checkConfirmCode(@PathVariable("confirmCode") String confirmCode) {
        signUpService.checkConfirm(confirmCode);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

