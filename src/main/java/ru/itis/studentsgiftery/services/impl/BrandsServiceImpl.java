package ru.itis.studentsgiftery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.dto.BrandForm;
import ru.itis.studentsgiftery.dto.mapper.BrandMapper;
import ru.itis.studentsgiftery.exceptions.BrandNotFoundException;
import ru.itis.studentsgiftery.models.Brand;
import ru.itis.studentsgiftery.models.Certificate;
import ru.itis.studentsgiftery.repositories.BrandsRepository;
import ru.itis.studentsgiftery.services.BrandsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandsServiceImpl implements BrandsService {

    private final BrandsRepository brandsRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandDto saveBrand(BrandForm brandForm) {
        Brand newBrand = Brand.builder()
                .brandName(brandForm.getBrandName())
                .description(brandForm.getDescription())
                .avatarLink(brandForm.getAvatarLink())
                .state(Brand.State.ACTIVE)
                .certificateList(new ArrayList<Certificate>())
                .build();

        brandsRepository.save(newBrand);

        return brandMapper.toBrandDto(newBrand);
    }

    @Override
    public BrandDto getBrand(Long id) {
        Brand brand = brandsRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        return brandMapper.toBrandDto(brand);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandMapper.toBrandDtoList(brandsRepository.findAll());
    }

    @Override
    public BrandDto updateBrand(Long id, BrandForm newData) {
        Brand brand = brandsRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        brand.setBrandName(newData.getBrandName());
        brand.setDescription(newData.getDescription());
        brand.setAvatarLink(newData.getAvatarLink());

        brandsRepository.save(brand);

        return brandMapper.toBrandDto(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = brandsRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        brand.setState(Brand.State.DELETED);

        brandsRepository.save(brand);
    }
}
