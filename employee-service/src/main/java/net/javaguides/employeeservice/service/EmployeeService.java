package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.ApiResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getEmployees();
    ApiResponseDto getEmployeeById(Long employeeId);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployeeDto(Long employeeId,EmployeeDto employeeDto);
    void deleteEmployeeById(Long employeeId);
}
