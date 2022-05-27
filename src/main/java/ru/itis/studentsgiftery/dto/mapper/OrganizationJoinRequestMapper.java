package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.studentsgiftery.dto.OrganizationJoinRequestDto;
import ru.itis.studentsgiftery.models.OrganizationJoinRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrganizationJoinRequestMapper {
    public abstract OrganizationJoinRequest toOrganizationJoinRequest(OrganizationJoinRequestDto organizationJoinRequestDto);

    public abstract OrganizationJoinRequestDto toOrganizationJoinRequestDto(OrganizationJoinRequest organizationJoinRequest);

    public abstract List<OrganizationJoinRequest> toOrganizationJoinRequestList(List<OrganizationJoinRequestDto> organizationJoinRequestDtos);

    public abstract List<OrganizationJoinRequestDto> toOrganizationJoinRequestDtoList(List<OrganizationJoinRequest> organizationJoinRequests);

    @AfterMapping
    protected void afterOrganizationJoinRequestDtoMapping(OrganizationJoinRequest organizationJoinRequest,
                                                          @MappingTarget OrganizationJoinRequestDto organizationJoinRequestDto) {
        organizationJoinRequestDto.setOrganizationId(organizationJoinRequest.getOrganization().getId());
        organizationJoinRequestDto.setAccountId(organizationJoinRequest.getAccount().getId());
    }
}
