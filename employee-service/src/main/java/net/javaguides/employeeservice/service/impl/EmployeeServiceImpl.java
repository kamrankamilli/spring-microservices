package net.javaguides.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.employeeservice.dto.ApiResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.dto.OrganizationDto;
import net.javaguides.employeeservice.exception.ResourceNotFoundException;
import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.model.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.response.ApiResponse;
import net.javaguides.employeeservice.service.DepartmentClient;
import net.javaguides.employeeservice.service.EmployeeService;
import net.javaguides.employeeservice.service.OrganizationClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentClient departmentClient;
    private OrganizationClient organizationClient;

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper.MAPPER::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
//    @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "serviceDownFallback")
    public ApiResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        ApiResponse<DepartmentDto> departmentResponse = departmentClient.getDepartment(employee.getDepartmentCode());
        ApiResponse<OrganizationDto> organizationResponse = organizationClient.getOrganization(employee.getOrganizationCode());
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        return new ApiResponseDto(employeeDto, departmentResponse.getData(), organizationResponse.getData());
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee createdEmployee = employeeRepository.saveAndFlush(employee);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(createdEmployee);
    }

    @Override
    public EmployeeDto updateEmployeeDto(Long employeeId, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setDepartmentCode(employeeDto.getDepartmentCode());
        existingEmployee.setOrganizationCode(employeeDto.getOrganizationCode());
        Employee updatedEmployee = employeeRepository.saveAndFlush(existingEmployee);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
