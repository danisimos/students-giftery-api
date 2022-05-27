package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.Mapper;
import ru.itis.studentsgiftery.dto.OrganizationDto;
import ru.itis.studentsgiftery.models.Organization;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrganizationMapper {

    public abstract Organization toOrganization(OrganizationDto organizationDto);

    public abstract OrganizationDto toOrganizationDto(Organization organization);

    public abstract List<Organization> toOrganizationList(List<OrganizationDto> organizationDtos);

    public abstract List<OrganizationDto> toOrganizationDtoList(List<Organization> organizations);
}
