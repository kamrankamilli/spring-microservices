package net.javaguidess.organizationservice.service;

import net.javaguidess.organizationservice.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDto> getOrganizations();
    OrganizationDto getOrganizationById(Long organizationId);
    OrganizationDto getOrganizationByCode(String organizationCode);
    OrganizationDto createOrganization(OrganizationDto organizationDto);
    OrganizationDto updateOrganization(Long organizationId, OrganizationDto organizationDto);
    void deleteOrganizationById(Long organizationId);

}
