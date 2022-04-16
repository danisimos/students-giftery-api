package ru.itis.studentsgiftery.services;

import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.dto.BrandForm;

import java.util.List;

public interface BrandsService {
    BrandDto saveBrand(BrandForm brandForm);

    BrandDto getBrand(Long id);

    List<BrandDto> getAllBrands();

    BrandDto updateBrand(Long id, BrandForm newData);

    void deleteBrand(Long id);

}
