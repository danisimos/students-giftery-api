package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.BrandsApi;
import ru.itis.studentsgiftery.services.BrandsService;

@RequiredArgsConstructor
@RestController
public class BrandsController {
    private final BrandsService brandsService;
}
