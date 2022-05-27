package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.AccountsApi;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.services.AccountsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountsController implements AccountsApi {
    private final AccountsService accountsService;

    @Override
    public ResponseEntity<List<AccountDto>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsService.getAccounts());
    }

    @Override
    public ResponseEntity<List<AccountDto>> getByOrganization(Long organizationId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsService.getByOrganization(organizationId));
    }
}
