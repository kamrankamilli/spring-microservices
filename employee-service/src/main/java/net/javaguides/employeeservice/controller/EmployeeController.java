package net.javaguides.employeeservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.ApiResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.response.ApiResponse;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDto>>> getEmployees(){
        List<EmployeeDto> employees = employeeService.getEmployees();
        return new ApiResponse<>(HttpStatus.OK, null, employees).sendResponse();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<ApiResponseDto>> getEmployee(@PathVariable Long employeeId){
        ApiResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ApiResponse<>(HttpStatus.OK, null, apiResponseDto).sendResponse();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDto>> postEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return new ApiResponse<>(HttpStatus.CREATED, "Employee created successfully", createdEmployee).sendResponse();
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<EmployeeDto>> putEmployee(@PathVariable Long employeeId,@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmployee = employeeService.updateEmployeeDto(employeeId,employeeDto);
        return new ApiResponse<>(HttpStatus.OK, "Employee updated successfully", updatedEmployee).sendResponse();
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<EmployeeDto>> deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return new ApiResponse<EmployeeDto>(HttpStatus.OK,"Employee deleted successfully",null).sendResponse();
    }

}
