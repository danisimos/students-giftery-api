package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.Mapper;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.models.Brand;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BrandMapper {
    public abstract Brand toBrand(BrandDto brandDto);

    public abstract BrandDto toBrandDto(Brand brand);

    public abstract List<BrandDto> toBrandDtoList(List<Brand> brands);
}
