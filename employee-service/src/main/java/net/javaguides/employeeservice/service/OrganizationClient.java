package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.OrganizationDto;
import net.javaguides.employeeservice.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "organization-service")
public interface OrganizationClient {
    @GetMapping("/api/v1/organizations/code/{organizationCode}")
    ApiResponse<OrganizationDto> getOrganization(@PathVariable String organizationCode);
}
