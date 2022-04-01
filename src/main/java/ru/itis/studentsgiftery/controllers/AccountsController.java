package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.studentsgiftery.services.AccountsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AccountsController {
    private final AccountsService usersService;
}
