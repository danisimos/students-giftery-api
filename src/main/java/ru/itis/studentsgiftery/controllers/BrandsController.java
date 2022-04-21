package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.BrandsApi;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.dto.forms.BrandForm;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.security.details.AccountUserDetails;
import ru.itis.studentsgiftery.services.BrandsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students-giftery/brands/")
public class BrandsController {
    private final BrandsService brandsService;

    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandForm brandForm,
                                                Authentication authentication) {
        Account account = ((AccountUserDetails) authentication.getCredentials()).getAccount();

        BrandDto brandDto = brandsService.createBrand(brandForm, account);

        if(brandDto == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(brandDto);
        }
    }
}
