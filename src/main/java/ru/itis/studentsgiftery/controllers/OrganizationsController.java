package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.dto.OrganizationJoinRequestDto;
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

        OrganizationDto organizationDto = organizationService.createOrganization(organizationForm, account);

        if(organizationDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(organizationDto);
        }

    }

    @GetMapping("organizations")
    public ResponseEntity<List<OrganizationDto>> getOrganizations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizationService.getOrganizations());
    }

    @PostMapping("organizations/join")
    public ResponseEntity<OrganizationJoinRequestDto> addOrganizationJoinRequest(@RequestParam(name = "organizationId") Long organizationId,
                                                                                 Authentication authentication) {
        Account account = ((AccountUserDetails) authentication.getCredentials()).getAccount();

        OrganizationJoinRequestDto organizationJoinRequestDto = organizationService.addOrganizationJoinRequest(organizationId, account);

        if(organizationJoinRequestDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(organizationJoinRequestDto);
        }
    }

    @PostMapping("organizations/denyJoin")
    public ResponseEntity<OrganizationJoinRequestDto> denyOrganizationJoinRequest(@RequestParam(name = "requestId") Long requestId,
                                                                                     Authentication authentication) {
        Account account = ((AccountUserDetails) authentication.getCredentials()).getAccount();

        OrganizationJoinRequestDto organizationJoinRequestDto = organizationService.denyOrganizationJoinRequest(requestId, account);

        if(organizationJoinRequestDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(organizationJoinRequestDto);
        }
    }

    @PostMapping("organizations/confirmJoin")
    public ResponseEntity<OrganizationJoinRequestDto> confirmOrganizationJoinRequest(@RequestParam(name = "requestId") Long requestId,
                                                                                 Authentication authentication) {
        Account account = ((AccountUserDetails) authentication.getCredentials()).getAccount();


        OrganizationJoinRequestDto organizationJoinRequestDto = organizationService.confirmOrganizationJoinRequest(requestId, account);

        if(organizationJoinRequestDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(organizationJoinRequestDto);
        }
    }
}
