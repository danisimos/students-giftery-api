package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.Mapper;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.models.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toBrand(BrandDto brandDto);

    BrandDto toBrandDto(Brand brand);
}
