package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.dto.forms.OrganizationForm;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.security.details.AccountUserDetails;
import ru.itis.studentsgiftery.services.OrganizationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students-giftery/")
public class OrganizationsController {
    private final OrganizationService organizationService;

    @PostMapping("createOrganization")
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationForm organizationForm,
                                                              Authentication authentication) {
        Account account = ((AccountUserDetails) authentication.getCredentials()).getAccount();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizationService.createOrganization(organizationForm, account));
    }

    @GetMapping("organizations")
    public ResponseEntity<List<OrganizationDto>> getOrganizations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizationService.getOrganizations());
    }
}
