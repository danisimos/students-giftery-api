package ru.itis.studentsgiftery.services;

import org.springframework.http.ResponseEntity;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.dto.forms.BrandForm;
import ru.itis.studentsgiftery.models.Account;

import java.util.List;

public interface BrandsService {
    BrandDto getBrand(Long id);

    List<BrandDto> getAllBrands();

    BrandDto updateBrand(Long id, BrandForm newData);

    void deleteBrand(Long id);

    BrandDto createBrand(BrandForm brandForm);
}
