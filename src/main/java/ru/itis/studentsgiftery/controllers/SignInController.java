package ru.itis.studentsgiftery.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.SignInApi;
import ru.itis.studentsgiftery.dto.forms.SignInForm;

@RestController
public class SignInController implements SignInApi {
    @Override
    public ResponseEntity<String> signIn(SignInForm body) {
        return ResponseEntity.ok("Success");
    }
}
