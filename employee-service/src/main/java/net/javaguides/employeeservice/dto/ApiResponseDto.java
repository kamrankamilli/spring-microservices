package net.javaguides.employeeservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponseDto {
    private EmployeeDto employee;
    private DepartmentDto department;
    private OrganizationDto organization;
}
