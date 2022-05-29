package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.api.OrganizationsApi;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.dto.OrganizationJoinRequestDto;
import ru.itis.studentsgiftery.dto.forms.OrganizationForm;
import ru.itis.studentsgiftery.services.OrganizationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrganizationsController implements OrganizationsApi {
    private final OrganizationService organizationService;

    @Override
    public ResponseEntity<OrganizationJoinRequestDto> addOrganizationJoinRequest(Long organizationId) {
        OrganizationJoinRequestDto organizationJoinRequestDto = organizationService.addOrganizationJoinRequest(organizationId);

        if(organizationJoinRequestDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(organizationJoinRequestDto);
        }
    }

    @Override
    public ResponseEntity<OrganizationJoinRequestDto> confirmOrganizationJoinRequest(Long requestId) {
        OrganizationJoinRequestDto organizationJoinRequestDto = organizationService.confirmOrganizationJoinRequest(requestId);

        if(organizationJoinRequestDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(organizationJoinRequestDto);
        }
    }

    @Override
    public ResponseEntity<OrganizationDto> createOrganization(OrganizationForm organizationForm) {
        OrganizationDto organizationDto = organizationService.createOrganization(organizationForm);

        if(organizationDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(organizationDto);
        }
    }

    @Override
    public ResponseEntity<OrganizationJoinRequestDto> denyOrganizationJoinRequest(Long requestId) {
        OrganizationJoinRequestDto organizationJoinRequestDto = organizationService.denyOrganizationJoinRequest(requestId);

        if(organizationJoinRequestDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(organizationJoinRequestDto);
        }
    }

    @Override
    public ResponseEntity<List<OrganizationDto>> getOrganizations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizationService.getOrganizations());
    }

    @Override
    public ResponseEntity<AccountDto> unjoinFromOrganization() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizationService.unjoinFromOrganization());
    }

    @Override
    public ResponseEntity<OrganizationDto> deleteOrganization(Long organizationId) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.deleteOrganization(organizationId));
    }
}
