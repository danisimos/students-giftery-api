package ru.itis.studentsgiftery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.studentsgiftery.services.BrandsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandsController {
    private final BrandsService brandsService;
}
