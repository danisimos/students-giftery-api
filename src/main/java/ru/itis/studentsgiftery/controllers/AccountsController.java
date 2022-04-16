package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.services.AccountsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students-giftery/")
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping("accounts/getByOrganization")
    public ResponseEntity<List<AccountDto>> getByOrganization(@RequestParam(name = "organizationId") Long organizationId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsService.getByOrganization(organizationId));
    }

    @GetMapping("accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsService.getAccounts());
    }
}
