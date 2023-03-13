package net.javaguidess.organizationservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguidess.organizationservice.dto.OrganizationDto;
import net.javaguidess.organizationservice.response.ApiResponse;
import net.javaguidess.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrganizationDto>>> getOrganizations(){
        List<OrganizationDto> organizationsDto = organizationService.getOrganizations();
        return new ApiResponse<>(HttpStatus.OK,null,organizationsDto).sendResponse();
    }
    @GetMapping("/{organizationId}")
    public ResponseEntity<ApiResponse<OrganizationDto>> getOrganization(@PathVariable Long organizationId){
        OrganizationDto organizationDto  = organizationService.getOrganizationById(organizationId);
        return new ApiResponse<>(HttpStatus.OK,null,organizationDto).sendResponse();
    }

    @GetMapping("/code/{organizationCode}")
    public ResponseEntity<ApiResponse<OrganizationDto>> getOrganization(@PathVariable String organizationCode){
        OrganizationDto organizationDto  = organizationService.getOrganizationByCode(organizationCode);
        return new ApiResponse<>(HttpStatus.OK,null,organizationDto).sendResponse();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrganizationDto>> postOrganization(@Valid @RequestBody OrganizationDto organizationDto){
        OrganizationDto createdOrganizationDto =  organizationService.createOrganization(organizationDto);
        return new ApiResponse<>(HttpStatus.CREATED,"Organization created successfully",createdOrganizationDto).sendResponse();
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<ApiResponse<OrganizationDto>> putOrganization(@PathVariable Long organizationId, @Valid @RequestBody OrganizationDto organizationDto){
        OrganizationDto updatedOrganizationDto = organizationService.updateOrganization(organizationId,organizationDto);
        return new ApiResponse<>(HttpStatus.OK,"Organization updated successfully",updatedOrganizationDto).sendResponse();
    }
    @DeleteMapping("/{organizationId}")
    public ResponseEntity<ApiResponse<Object>> deleteOrganization(@PathVariable Long organizationId){
        organizationService.deleteOrganizationById(organizationId);
        return new ApiResponse<>(HttpStatus.OK,"Organization deleted successfully",null).sendResponse();
    }

}
