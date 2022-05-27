package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.dto.forms.BrandForm;

import java.util.List;

public interface BrandsService {
    BrandDto getBrand(Long id);

    List<BrandDto> getAllBrands();

    BrandDto updateBrand(Long id, BrandForm newData);

    BrandDto createBrand(BrandForm brandForm);

    BrandDto deleteBrand(Long brandId);
}
