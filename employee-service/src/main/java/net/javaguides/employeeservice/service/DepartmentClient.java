package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="department-service")
public interface DepartmentClient {
    @GetMapping("/api/v1/departments/code/{departmentCode}")
    ApiResponse<DepartmentDto> getDepartment(@PathVariable String departmentCode);
}

