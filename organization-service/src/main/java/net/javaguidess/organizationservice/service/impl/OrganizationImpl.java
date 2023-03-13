package net.javaguidess.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguidess.organizationservice.dto.OrganizationDto;
import net.javaguidess.organizationservice.exception.ResourceNotFoundException;
import net.javaguidess.organizationservice.mapper.OrganizationMapper;
import net.javaguidess.organizationservice.model.Organization;
import net.javaguidess.organizationservice.repository.OrganizationRepository;
import net.javaguidess.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OrganizationImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;

    @Override
    public List<OrganizationDto> getOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map(OrganizationMapper.MAPPER::mapToOrganizationDto).collect(Collectors.toList());
    }

    @Override
    public OrganizationDto getOrganizationById(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new ResourceNotFoundException("Organization", "id", organizationId));
        return OrganizationMapper.MAPPER.mapToOrganizationDto(organization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByCode(organizationCode).orElseThrow(() -> new ResourceNotFoundException("Organization", "code", organizationCode));
        return OrganizationMapper.MAPPER.mapToOrganizationDto(organization);
    }

    @Override
    public OrganizationDto createOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.MAPPER.mapToOrganization(organizationDto);
        Organization createdOrganization = organizationRepository.saveAndFlush(organization);
        return OrganizationMapper.MAPPER.mapToOrganizationDto(createdOrganization);
    }

    @Override
    public OrganizationDto updateOrganization(Long organizationId, OrganizationDto organizationDto) {
        Organization existingOrganization = organizationRepository.findById(organizationId).orElseThrow(()->new ResourceNotFoundException("Organization","id",organizationId));
        existingOrganization.setName(organizationDto.getName());
        existingOrganization.setDescription(organizationDto.getDescription());
        existingOrganization.setCode(organizationDto.getCode());
        return OrganizationMapper.MAPPER.mapToOrganizationDto(existingOrganization);
    }

    @Override
    public void deleteOrganizationById(Long organizationId) {
        organizationRepository.findById(organizationId).orElseThrow(()->new ResourceNotFoundException("Organization","id",organizationId));
        organizationRepository.deleteById(organizationId);
    }
}
