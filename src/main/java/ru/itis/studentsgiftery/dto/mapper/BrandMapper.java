package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.dto.BrandForm;
import ru.itis.studentsgiftery.models.Brand;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BrandMapper {

    public abstract Brand toBrand(BrandDto brandDto);

    public abstract BrandDto toBrandDto(Brand brand);

    public abstract List<BrandDto> toBrandDtoList(List<Brand> brands);

    @AfterMapping
    protected void set(Brand brand, @MappingTarget BrandDto brandDto){
        brandDto.setListCertificateId(brand.getCertificateList().stream().map(s-> s.getId()).collect(Collectors.toList()));

    }
}
