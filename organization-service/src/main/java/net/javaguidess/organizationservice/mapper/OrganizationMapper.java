package net.javaguidess.organizationservice.mapper;

import net.javaguidess.organizationservice.dto.OrganizationDto;
import net.javaguidess.organizationservice.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper MAPPER = Mappers.getMapper(OrganizationMapper.class);

    OrganizationDto mapToOrganizationDto(Organization organization);

    Organization mapToOrganization(OrganizationDto organizationDto);
}
